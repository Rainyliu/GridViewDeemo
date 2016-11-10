package com.example.rainy.gridviewdeemo.application;

import android.app.Application;

import com.oubowu.slideback.ActivityHelper;
import com.yolanda.nohttp.NoHttp;


/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/11/1 0001 上午 10:12 <br>
 */

public class MyApplication extends Application {
    private ActivityHelper mActivityHelper;
    private static MyApplication sMyApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttp.initialize(this);

        mActivityHelper = new ActivityHelper();
        registerActivityLifecycleCallbacks(mActivityHelper);

        sMyApplication = this;
    }

    public static ActivityHelper getActivityHelpler(){
        return sMyApplication.mActivityHelper;
    }
}
