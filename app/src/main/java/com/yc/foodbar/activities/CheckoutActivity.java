package com.yc.foodbar.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.MainActivity;
import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.Item;
import com.yc.foodbar.remote.pojo.OrderResult;
import com.yc.foodbar.tasks.OrderPlacementTask;
import com.yc.foodbar.ui.elements.CheckoutAdapter;
import com.yc.foodbar.ui.elements.SingleToast;

import java.math.BigDecimal;

public class CheckoutActivity extends AbstractFoodBarActivity {

    private CheckoutAdapter adapter;

    private TextView specialInstructionsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        this.adapter = new CheckoutAdapter(this, getSessionService().getOrderedItems());

        ListView listView = (ListView) findViewById(R.id.checkoutItemList);
        listView.setAdapter(adapter);

        this.specialInstructionsTxt = (TextView) findViewById(R.id.specialInstructions);

        updateTotal();
    }

    public void cancel(View view) {
        finish();
    }

    public void order(View view) {
        getSessionService().getOrder().setSpecialInstructions(this.specialInstructionsTxt.getText().toString());
        new OrderPlacementTask(this, getSessionService().getOrder()) {
            protected void onPostExecute(OrderResult result) {
               /*SingleToast.show(CheckoutActivity.this,
                       "Your order number is: " + result.getOrderNumber() + " and it will be with you within " + result.getExpectedWaitTime() + " min :)" , Toast.LENGTH_LONG);*/

                AlertDialog alertDialog = new AlertDialog.Builder(CheckoutActivity.this).create();
                alertDialog.setTitle("Order Received :)");
                alertDialog.setMessage("Your order number is: " + result.getOrderNumber() + " and it will be with you within " + result.getExpectedWaitTime() + " min :)");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                CheckoutActivity.this.adapter.clear();
                                CheckoutActivity.this.getSessionService().clearOrder();
                                finish();
                            }
                        });
                alertDialog.show();
            }
        }.execute();
    }

    public void updateTotal() {
        TextView totalOrder = (TextView) findViewById(R.id.totalOrderAmount);
        float total = 0;
        for (Item item : getSessionService().getOrderedItems()) {
            total += item.getPrice();
        }
        total = round(total, 2);
        totalOrder.setText("$" + total);
    }

    public static float round(float number, int decimalPlace) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
