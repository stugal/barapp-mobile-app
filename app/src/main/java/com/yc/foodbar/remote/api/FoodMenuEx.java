package com.yc.foodbar.remote.api;

import com.yc.foodbar.remote.pojo.FoodMenu;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by stugal on 4/12/2019.
 */

public interface FoodMenuEx {

    @GET("menu")
    Call<FoodMenu> getFoodMenu();

}