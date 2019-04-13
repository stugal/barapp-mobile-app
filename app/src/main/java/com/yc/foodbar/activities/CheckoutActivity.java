package com.yc.foodbar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.R;
import com.yc.foodbar.ui.elements.CheckoutAdapter;

public class CheckoutActivity extends AbstractFoodBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        CheckoutAdapter adapter = new CheckoutAdapter(this, getSessionService().getOrderedItems());

        ListView listView = (ListView) findViewById(R.id.checkoutItemList);
        listView.setAdapter(adapter);
    }
}
