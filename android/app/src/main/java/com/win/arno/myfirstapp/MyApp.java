package com.win.arno.myfirstapp;

import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.justalk.cloud.juslogin.LoginDelegate;

import static com.justalk.cloud.juslogin.LoginDelegate.InitStat.MTC_INIT_SUCCESS;

/**
 * Created by arno on 2017/1/7.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT > 22 && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) return;

        if (LoginDelegate.init(this, getString(R.string.JusTalkCloud_AppKey)) != MTC_INIT_SUCCESS) return;

    }
}
