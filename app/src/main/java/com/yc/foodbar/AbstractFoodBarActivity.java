package com.yc.foodbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yc.foodbar.services.RemotingService;
import com.yc.foodbar.services.ServiceRegistry;
import com.yc.foodbar.services.SessionService;

public class AbstractFoodBarActivity extends AppCompatActivity {

    /**
     * Services
     */
    private RemotingService remotingService;

    /**
     * Session service
     */
    private SessionService sessionService;

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

    public SessionService getSessionService() {
        return sessionService;
    }

    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    protected void initializeServices() {
        ServiceRegistry.INSTANTIATE(this);
        setRemotingService((RemotingService) ServiceRegistry.getService(RemotingService.class));
        setSessionService((SessionService) ServiceRegistry.getService(SessionService.class));
    }

    public void initializeSpinner() {
        progressBar = new ProgressDialog(this);
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setCancelable(false);
    }
}
