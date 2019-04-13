package com.yc.foodbar.remote.api;

import com.yc.foodbar.remote.pojo.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by stugal on 4/12/2019.
 */

public interface FoodMenuEx {

    @GET("menu")
    Call<List<Category>> getFoodMenu();

}