package com.yc.foodbar.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by stugal on 7/18/2017.
 */

public class RemotingService extends AbstractFoodBarService {

    private Retrofit retrofit;

    protected RemotingService() {

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://54.166.71.233/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public <T> T createServiceEndpoint(Class clazz) {
        return (T) this.retrofit.create(clazz);
    }

}
