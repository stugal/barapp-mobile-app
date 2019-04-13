package com.yc.foodbar.tasks;

import android.os.AsyncTask;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.remote.api.FoodMenuEx;
import com.yc.foodbar.remote.api.OrderEx;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.remote.pojo.Order;
import com.yc.foodbar.remote.pojo.OrderResult;

import java.io.IOException;

/**
 * Created by stugal on 4/13/2019.
 */

public class OrderPlacementTask extends AsyncTask<Object, Void, OrderResult> {

    private AbstractFoodBarActivity activity;

    private Order order;

    public OrderPlacementTask(AbstractFoodBarActivity activity, Order order) {
        this.activity = activity;
        this.order = order;
    }

    @Override
    protected OrderResult doInBackground(Object... objects) {

        OrderEx service = activity.getRemotingService().createServiceEndpoint(OrderEx.class);
        OrderResult result = null;
        try {
            result = service.placeOrder(this.order).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
