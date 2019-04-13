package com.yc.foodbar.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.MainActivity;
import com.yc.foodbar.R;
import com.yc.foodbar.logging.LogTags;
import com.yc.foodbar.services.AbstractFoodBarService;
import com.yc.foodbar.ui.elements.SingleToast;
import com.yc.foodbar.utils.AppConstants;
import com.yc.foodbar.utils.TagUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import retrofit2.Call;

import static com.yc.foodbar.utils.AppConstants.MIME_TEXT_PLAIN;

public class NfcScanActivity extends AbstractFoodBarActivity {

    /**
     * NFC adapter and tag
     */
    private NfcAdapter mNfcAdapter;
    private Tag productTag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_scan);

        // instantiate nfc
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (mNfcAdapter == null) {
            // Stop here, we definitely need NFC
            SingleToast.show(this, "This device doesn't support NFC.", Toast.LENGTH_LONG);
            //finish();
            //return;

        } else {
            // check if nfc enabled
            if (!mNfcAdapter.isEnabled()) {
                SingleToast.show(this, "NFC is disabled.", Toast.LENGTH_LONG);
                finish();
                return;
            }
            handleNFC(getIntent());
        }
    }

    /**
     * NFC related code below
     */

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * It's important, that the activity is in the foreground (resumed). Otherwise
         * an IllegalStateException is thrown.
         */
        setupForegroundDispatch(this, mNfcAdapter);
    }

    @Override
    protected void onPause() {
        /**
         * Call this before onPause, otherwise an IllegalArgumentException is thrown as well.
         */
        stopForegroundDispatch(this, mNfcAdapter);

        super.onPause();
    }

    /**
     * @param activity The corresponding {@link Activity} requesting the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void setupForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        final Intent intent = new Intent(activity.getApplicationContext(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        final PendingIntent pendingIntent = PendingIntent.getActivity(activity.getApplicationContext(), 0, intent, 0);

        IntentFilter[] filters = new IntentFilter[1];
        String[][] techList = new String[][]{};

        // Notice that this is the same filter as in our manifest.
        filters[0] = new IntentFilter();
        filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filters[0].addCategory(Intent.CATEGORY_DEFAULT);
        try {
            filters[0].addDataType(MIME_TEXT_PLAIN);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("Check your mime type.");
        }
        if (adapter != null) {
            adapter.enableForegroundDispatch(activity, pendingIntent, filters, techList);
        }
    }

    /**
     * @param activity The corresponding BaseActivity requesting to stop the foreground dispatch.
     * @param adapter The {@link NfcAdapter} used for the foreground dispatch.
     */
    public static void stopForegroundDispatch(final Activity activity, NfcAdapter adapter) {
        if (adapter != null) {
            adapter.disableForegroundDispatch(activity);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        /**
         * gets called when a new inten gets associated, instead of creating
         *   new instance of this activity the current is presented
         */
        //super.onNewIntent(intent);

        handleNFC(intent);

    }


    private void handleNFC(Intent intent) {
        if (getSessionService().isSessionActive()) {
            return;
        }
            String action = intent.getAction();

            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {

                String type = intent.getType();
                if (MIME_TEXT_PLAIN.equals(type)) {

                    this.productTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                    new TagReadingTask().execute(this.productTag);

                } else {
                    Log.d(LogTags.NFC_ERROR, "Wrong mime type: " + type);
                }
            } else if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {

                // In case we would still use the Tech Discovered Intent
                Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
                String[] techList = tag.getTechList();
                String searchedTech = Ndef.class.getName();

                for (String tech : techList) {
                    if (searchedTech.equals(tech)) {
                        new TagReadingTask().execute(tag);
                        break;
                    }
                }
            }
        }

        private class TagReadingTask extends AsyncTask<Tag, Void, String> {

            @Override
            protected String doInBackground(Tag... params) {
                Tag tag = params[0];

                try {
                    Thread.sleep(900);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Ndef ndef = Ndef.get(tag);
                if (ndef == null) {
                    // NDEF is not supported by this Tag.
                    return null;
                }

                NdefMessage ndefMessage = ndef.getCachedNdefMessage();

                NdefRecord[] records = ndefMessage.getRecords();
                for (NdefRecord ndefRecord : records) {
                    if (ndefRecord.getTnf() == NdefRecord.TNF_WELL_KNOWN && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                        try {
                            return TagUtils.readText(ndefRecord);
                        } catch (UnsupportedEncodingException e) {
                            Log.e(LogTags.NFC_ERROR, "Unsupported Encoding", e);
                        }
                    }
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                NfcScanActivity.this.processVendorTableData(result);
            }
        }
    }
