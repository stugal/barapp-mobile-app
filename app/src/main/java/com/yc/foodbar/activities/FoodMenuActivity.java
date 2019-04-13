package com.yc.foodbar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.ui.elements.FoodMenuAdapter;
import com.yc.foodbar.utils.AppConstants;

public class FoodMenuActivity extends AppCompatActivity {

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
    }
}
