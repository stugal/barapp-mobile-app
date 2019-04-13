package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stugal on 4/12/2019.
 */

public class FoodMenu implements Serializable {

    private List<Category> menu;


    @SerializedName("name")
    private String vendorName;

    @SerializedName("image_path")
    private String vendorImagePath;

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorImagePath() {
        return vendorImagePath;
    }

    public void setVendorImagePath(String vendorImagePath) {
        this.vendorImagePath = vendorImagePath;
    }

    public List<Category> getMenu() {
        return menu;
    }

    public void setMenu(List<Category> menu) {
        this.menu = menu;
    }
}
