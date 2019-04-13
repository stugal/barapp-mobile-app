package com.yc.foodbar.utils;

import android.nfc.NdefRecord;
import android.util.Log;

import com.yc.foodbar.logging.LogTags;

import java.io.UnsupportedEncodingException;

public class TagUtils {

    private TagUtils(){}

    public static int parseTagId(String tagStr) {
        if (tagStr == null || tagStr.isEmpty()) {
            return AppConstants.INVALID_TAG_ID;
        }
        String strId = tagStr.split(":")[1];
        try {
            int tagId = Integer.parseInt(strId);
            return tagId;
        } catch (NumberFormatException e) {
            Log.e(LogTags.TAG_ERROR, "Error during tag id parsing.", e);
            return AppConstants.INVALID_TAG_ID;
        }
    }

    public static NdefRecord createRecord(String text) throws UnsupportedEncodingException {

        //create the message in according with the standard
        String lang = "en";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;

        byte[] payload = new byte[1 + langLength + textLength];
        payload[0] = (byte) langLength;

        // copy langbytes and textbytes into payload
        System.arraycopy(langBytes, 0, payload, 1, langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);

        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payload);
        return recordNFC;
    }

    public static String readText(NdefRecord record) throws UnsupportedEncodingException {
        /*
         * See NFC forum specification for "Text Record Type Definition" at 3.2.1
         *
         * http://www.nfc-forum.org/specs/
         *
         * bit_7 defines encoding
         * bit_6 reserved for future use, must be 0
         * bit_5..0 length of IANA language code
         */

        byte[] payload = record.getPayload();

        // Get the Text Encoding
        String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";

        // Get the Language Code
        int languageCodeLength = payload[0] & 0063;

        // String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
        // e.g. "en"

        // Get the Text
        return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
    }
}
