package com.yc.foodbar.tasks;

import android.os.AsyncTask;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.remote.api.FoodMenuEx;
import com.yc.foodbar.remote.pojo.Category;

import java.io.IOException;
import java.util.List;

/**
 * Created by stugal on 4/12/2019.
 */

public class FoodMenuRetrievalTask extends AsyncTask<Object, Void, List<Category>> {

    private AbstractFoodBarActivity activity;

    public FoodMenuRetrievalTask(AbstractFoodBarActivity activity) {
        this.activity = activity;
    }

    @Override
    protected List<Category> doInBackground(Object... objects) {

        FoodMenuEx service = activity.getRemotingService().createServiceEndpoint(FoodMenuEx.class);
        List<Category> menu = null;
        try {
             menu = service.getFoodMenu().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menu;
    }
}
