package com.yc.foodbar.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.yc.foodbar.AbstractFoodBarActivity;
import com.yc.foodbar.MainActivity;
import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.tasks.DownloadImageTask;
import com.yc.foodbar.ui.elements.FoodMenuAdapter;
import com.yc.foodbar.ui.elements.SingleToast;
import com.yc.foodbar.utils.AppConstants;

import static com.yc.foodbar.R.id.imageView;

public class FoodMenuActivity extends AbstractFoodBarActivity {

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private FoodMenu f;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        this.f = (FoodMenu) getIntent().getExtras().get(AppConstants.EXTRA_FOOD_MENU);

        expandableListView = (ExpandableListView) findViewById(R.id.foodMenuExpandable);
        expandableListAdapter = new FoodMenuAdapter(this, f.getMenu());

        expandableListView.setAdapter(expandableListAdapter);
        this.toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(f.getVendorName());

        ImageView bgImg = (ImageView) findViewById(R.id.foodMenuBgImg);
        Picasso.with(this).load(this.f.getVendorImagePath()).resize(200, 0).into(bgImg);

        // expand group
        expandableListView.expandGroup(0);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ImageView bgImg = (ImageView) findViewById(R.id.foodMenuBgImg);
        Picasso.with(this).load(this.f.getVendorImagePath()).resize(200, 0).into(bgImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.food_menu_toolbar_items, menu);

        //ImageView imageView = (ImageView) toolbar.findViewById(R.id.vendorLogo);
        //new DownloadImageTask(imageView).execute(this.f.getVendorImagePath());
        //Picasso.with(this).load(f.getVendorImagePath()).into(imageView);
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
