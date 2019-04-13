package com.yc.foodbar.remote.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by stugal on 4/13/2019.
 */

public class OrderResult implements Serializable {
    @SerializedName("order_number")
    private int orderNumber;
    @SerializedName("expected_wait_time")
    private int expectedWaitTime;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getExpectedWaitTime() {
        return expectedWaitTime;
    }

    public void setExpectedWaitTime(int expectedWaitTime) {
        this.expectedWaitTime = expectedWaitTime;
    }

    @Override
    public String toString() {
        return "OrderResult{" +
                "orderNumber=" + orderNumber +
                ", expectedWaitTime=" + expectedWaitTime +
                '}';
    }
}
