package com.yc.foodbar.ui.elements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yc.foodbar.R;
import com.yc.foodbar.remote.pojo.Item;
import com.yc.foodbar.remote.pojo.Order;

import java.util.List;

import static android.R.attr.order;

/**
 * Created by stugal on 4/13/2019.
 */

public class CheckoutAdapter extends ArrayAdapter<Item> {
    public CheckoutAdapter(@NonNull Context context, @NonNull List<Item> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.checkout_list_item, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.checkoutItemName);
        TextView price = (TextView) convertView.findViewById(R.id.checkoutItemPrice);

        name.setText(item.getName());
        price.setText("$" + item.getPrice());

        ImageView addItem = (ImageView) convertView.findViewById(R.id.checkoutDel);
        addItem.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                remove(item);
            }
        });

        return convertView;
    }

}
