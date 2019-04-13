package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by stugal on 4/13/2019.
 */

public class Order implements Serializable {

    @SerializedName("vendor_id")
    private int vendorId;

    @SerializedName("table_number")
    private int tableNumber;

    @SerializedName("user_id")
    private int userId;

    private List<Integer> items;

    public Order(int vendorId, int tableNumber, int userId, List<Integer> items) {
        this.vendorId = vendorId;
        this.tableNumber = tableNumber;
        this.userId = userId;
        this.items = items;
    }

    public Order() {
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
