package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by stugal on 4/12/2019.
 */

public class Item {
    @SerializedName("item_id")
    private int itemId;

    @SerializedName("item_name")
    private String name;

    private List<Ingredient> ingredients;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
