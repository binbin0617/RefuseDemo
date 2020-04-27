package com.bin.refusedemo.base;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

public class BaseApplication extends Application {

    private static SharedPreferences preferences;
    private static Boolean isLogin = false;

    public static Boolean getIsLogin() {
        return isLogin;
    }

    public static void setIsLogin(Boolean isLogin) {
        BaseApplication.isLogin = isLogin;
    }

    public static SharedPreferences getPreferences() {
        return preferences;
    }

    public static void setPreferences(SharedPreferences preferences) {
        BaseApplication.preferences = preferences;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        preferences = getSharedPreferences("IpAndPort", Activity.MODE_PRIVATE);
        isLogin = preferences.getBoolean("islogin", false);
        SpeechUtility.createUtility(getApplicationContext(), SpeechConstant.APPID + "=5ea67b14");
    }
}
