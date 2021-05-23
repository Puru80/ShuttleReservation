package com.mainpage.shuttlereservation;

import android.content.Context;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class ShuttleResApplication extends MultiDexApplication
{
    private static Context ctx;
    private static ShuttleResApplication instance;
    private AppBeanFactory appBeanFactory;

    public static ShuttleResApplication getInstance() {
        return instance;
    }

    public static Context getCtx() {
        return ctx;
    }

    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        Log.v("Aapplication Started", "Oncreate");
        instance = this;
        ShuttleResApplication.ctx = getApplicationContext();
    }

    public AppBeanFactory getAppBeanFactory() {
        if (this.appBeanFactory == null) {
            this.appBeanFactory = new AppBeanFactory();
        }
        return this.appBeanFactory;
    }
}
