package com.yc.foodbar.remote.api;

import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.remote.pojo.Order;
import com.yc.foodbar.remote.pojo.OrderResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by stugal on 4/13/2019.
 */

public interface OrderEx {

    @POST("order")
    Call<OrderResult> placeOrder(@Body Order body);
}
