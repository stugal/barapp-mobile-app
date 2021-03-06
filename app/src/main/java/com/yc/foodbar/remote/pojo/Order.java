package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static com.yc.foodbar.R.id.specialInstructions;

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

    @SerializedName("special_instructions")
    private String specialInstructions;

    private List<Item> items;

    public Order(int vendorId, int tableNumber, int userId, String specialInstructions, List<Item> items) {
        this.vendorId = vendorId;
        this.tableNumber = tableNumber;
        this.userId = userId;
        this.items = items;
        this.specialInstructions = specialInstructions;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
