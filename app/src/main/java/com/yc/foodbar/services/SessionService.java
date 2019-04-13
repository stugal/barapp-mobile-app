package com.yc.foodbar.services;

/**
 * Created by stugal on 4/13/2019.
 */

public class SessionService extends AbstractFoodBarService {
    private int tableId;
    private int vendorId;
    private boolean sessionActive;

    public void activateSession(int vendorId, int tableId) {
        this.vendorId = vendorId;
        this.tableId = tableId;
        this.sessionActive = true;
    }

    public void deactivateSession() {
        this.sessionActive = false;
        this.vendorId = -1;
        this.tableId = -1;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }
}
