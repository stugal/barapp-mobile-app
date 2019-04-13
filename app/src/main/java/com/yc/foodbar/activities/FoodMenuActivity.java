package com.yc.foodbar.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.MainActivity;
import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.ui.elements.FoodMenuAdapter;
import com.yc.foodbar.ui.elements.SingleToast;
import com.yc.foodbar.utils.AppConstants;

public class FoodMenuActivity extends AbstractFoodBarActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        FoodMenu f = (FoodMenu) getIntent().getExtras().get(AppConstants.EXTRA_FOOD_MENU);

        expandableListView = (ExpandableListView) findViewById(R.id.foodMenuExpandable);
        expandableListAdapter = new FoodMenuAdapter(this, f.getMenu());
        expandableListView.setAdapter(expandableListAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.food_menu_toolbar_items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkout:
                Intent intent = new Intent(this, CheckoutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
