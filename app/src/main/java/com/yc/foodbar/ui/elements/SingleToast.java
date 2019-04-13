package com.yc.foodbar.ui.elements;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by stugal on 9/29/2017.
 */

public class SingleToast {
    private static Toast mToast;

    public static void show(Context context, String text, int duration) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(context, text, duration);
        mToast.show();
    }
}
