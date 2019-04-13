package com.yc.foodbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yc.foodbar.services.RemotingService;
import com.yc.foodbar.services.ServiceRegistry;

public class AbstractFoodBarActivity extends AppCompatActivity {

    /**
     * Services
     */
    private RemotingService remotingService;

    /**
     * Progress bar
     */
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeServices();

        // initialize spinner
        initializeSpinner();
    }

    public ProgressDialog getProgressBar() {
        return this.progressBar;
    }

    public RemotingService getRemotingService() {
        return remotingService;
    }

    public void setRemotingService(RemotingService remotingService) {
        this.remotingService = remotingService;
    }

    protected void initializeServices() {
        ServiceRegistry.INSTANTIATE(this);
        setRemotingService((RemotingService) ServiceRegistry.getService(RemotingService.class));
    }

    public void initializeSpinner() {
        progressBar = new ProgressDialog(this);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);
    }
}
