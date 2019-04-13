package com.yc.foodbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.yc.foodbar.qr.CaptureActivityPortait;
import com.yc.foodbar.remote.pojo.Category;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.remote.pojo.Order;
import com.yc.foodbar.remote.pojo.OrderResult;
import com.yc.foodbar.tasks.FoodMenuRetrievalTask;
import com.yc.foodbar.tasks.OrderPlacementTask;
import com.yc.foodbar.ui.elements.SingleToast;

import java.util.Arrays;

import static android.R.attr.data;

public class MainActivity extends AbstractFoodBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openQrScan(View view) {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan Table QR Code :)");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setOrientationLocked(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(CaptureActivityPortait.class);
        integrator.initiateScan();
    }

    public void test(View view) {
        new FoodMenuRetrievalTask(this) {
            @Override
            protected void onPostExecute(FoodMenu menu) {
                for (Category c : menu.getMenu()) {
                    Log.e("CAT: ", c.toString());
                }
            }
        }.execute();

        new OrderPlacementTask(this, new Order(1, 1, 1, Arrays.asList(1,2,3))) {

            @Override
            protected void onPostExecute(OrderResult result) {
                Log.e("RES", result.toString());
            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // qr scan result
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (result != null) {
                if (result.getContents() == null) {
                    SingleToast.show(this, "Scan canceled", Toast.LENGTH_LONG);
                } else if (result.getContents().startsWith("payfastr_proto_prod_id:")) {
                    SingleToast.show(this, result.getContents(), Toast.LENGTH_LONG);
                } else {
                    String qrData = result.getContents();

                }
            }
            return;
        }
    }
}
