package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by stugal on 4/12/2019.
 */

public class Ingredient {

    @SerializedName("ingredient_name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                '}';
    }
}
