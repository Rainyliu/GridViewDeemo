package com.example.rainy.gridviewdeemo.application;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;


/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/11/1 0001 上午 10:12 <br>
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
//        super.onCreate();
        NoHttp.initialize(this);
    }
}
