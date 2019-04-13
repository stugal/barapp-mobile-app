package com.yc.foodbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.yc.foodbar.activities.FoodMenuActivity;
import com.yc.foodbar.remote.pojo.Category;
import com.yc.foodbar.remote.pojo.FoodMenu;
import com.yc.foodbar.services.RemotingService;
import com.yc.foodbar.services.ServiceRegistry;
import com.yc.foodbar.services.SessionService;
import com.yc.foodbar.tasks.FoodMenuRetrievalTask;
import com.yc.foodbar.ui.elements.SingleToast;
import com.yc.foodbar.utils.AppConstants;

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

    protected void processVendorTableData(String data) {
        if (data  == null || !data.contains(";")) {
            return ;
        }
        String [] dataArr = data.split(";");
        Integer vendorId = Integer.parseInt(dataArr[0]);
        Integer tableId = Integer.parseInt(dataArr[1]);

        getSessionService().activateSession(vendorId, tableId);

        SingleToast.show(this, "Vendor: " + vendorId + " table: " + tableId, Toast.LENGTH_LONG);

        new FoodMenuRetrievalTask(this) {
            @Override
            protected void onPostExecute(FoodMenu menu) {
                for (Category c : menu.getMenu()) {
                    Log.e("CAT: ", c.toString());
                }


                Bundle bundle = new Bundle();
                bundle.putSerializable(AppConstants.EXTRA_FOOD_MENU, menu);

                Intent intent = new Intent(AbstractFoodBarActivity.this.getActivity(), FoodMenuActivity.class);
                intent.putExtras(bundle);


                startActivity(intent);
            }
        }.execute();
    }


    protected Context getActivity() {
        return getApplicationContext();
    }
}
