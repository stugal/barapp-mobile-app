package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stugal on 4/12/2019.
 */

public class FoodMenu {

    private List<Category> menu;

    public List<Category> getMenu() {
        return menu;
    }

    public void setMenu(List<Category> menu) {
        this.menu = menu;
    }
}
