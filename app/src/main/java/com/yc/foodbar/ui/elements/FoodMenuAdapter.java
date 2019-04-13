package com.yc.foodbar.ui.elements;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.Category;
import com.yc.foodbar.remote.pojo.Item;

import java.util.HashMap;
import java.util.List;

/**
 * Created by stugal on 4/13/2019.
 */

public class FoodMenuAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Category> foodMenu;

    public FoodMenuAdapter(Context context, List<Category> foodMenu) {
        this.context = context;
        this.foodMenu = foodMenu;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return foodMenu.get(listPosition).getItems().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Item item = (Item) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.fooditem_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.foodItem);
        expandedListTextView.setText(item.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.foodMenu.get(listPosition).getItems().size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.foodMenu.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.foodMenu.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Category cat = (Category) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.foodcategory_list_item, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.categoryTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(cat.getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}