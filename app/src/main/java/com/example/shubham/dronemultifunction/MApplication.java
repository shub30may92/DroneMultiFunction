package com.example.shubham.dronemultifunction;

import android.app.Application;
import android.content.Context;

import com.secneo.sdk.Helper;

/**
 * Created by shubgupta on 2/4/18.
 */

public class MApplication extends Application {

    private FPVDemoApplication fpvDemoApplication;
    @Override
    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
        Helper.install(MApplication.this);
        if (fpvDemoApplication == null) {
            fpvDemoApplication = new FPVDemoApplication();
            fpvDemoApplication.setContext(this);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fpvDemoApplication.onCreate();
    }
}
