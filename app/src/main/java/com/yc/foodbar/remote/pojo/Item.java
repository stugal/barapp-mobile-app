package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stugal on 4/12/2019.
 */

public class Item implements Serializable {
    @SerializedName("item_id")
    private int itemId;

    @SerializedName("item_name")
    private String name;

    @SerializedName("price")
    private float price;

    @SerializedName("image_path")
    private String imagePath;

    @SerializedName("sold_out")
    private boolean soldOut;

    private List<String> ingredients;

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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
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
