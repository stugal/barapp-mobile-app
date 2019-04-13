package com.yc.foodbar.services;

import com.yc.foodbar.remote.pojo.Item;
import com.yc.foodbar.remote.pojo.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.order;

/**
 * Created by stugal on 4/13/2019.
 */

public class SessionService extends AbstractFoodBarService {


    private int tableId;
    private int vendorId;
    private int userId = 1;
    private boolean sessionActive;
    List<Item> items = new ArrayList<>();
    private Order order;

    private static SessionService instance;
    public static SessionService INSTANCE() {
        if (instance == null) {
            instance = new SessionService();
        }

        return instance;
    }

    private SessionService(){}

    public void addToOrder(Item item) {
        if (order == null) {
            order = new Order(this.vendorId, this.tableId, this.userId, this.items);
            items.add(item);
        } else {
            order.getItems().add(item);
        }
    }

    public void clearOrder() {
        this.order = null;
        this.items.clear();
    }

    public Order getOrder() {
        return this.order;
    }

    public List<Item> getOrderedItems() {
        return this.items;
    }

    public void activateSession(int vendorId, int tableId) {
        this.vendorId = vendorId;
        this.tableId = tableId;
        this.sessionActive = true;
    }

    public void deactivateSession() {
        this.sessionActive = false;
        this.vendorId = -1;
        this.tableId = -1;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }
}
