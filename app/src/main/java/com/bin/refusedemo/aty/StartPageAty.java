package com.bin.refusedemo.aty;

import android.content.Intent;
import android.os.Bundle;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseApplication;
import com.bin.refusedemo.base.BaseAty;


public class StartPageAty extends BaseAty {
    Thread myThread = new Thread() {//创建子线程
        @Override
        public void run() {
            try {
                sleep(1000);//使程序休眠一秒
                if (BaseApplication.getIsLogin()) {
                    Intent it = new Intent(getApplicationContext(), MainAty.class);
                    startActivity(it);
                    finish();//关闭当前活动
                } else {
                    Intent it = new Intent(getApplicationContext(), LoginAty.class);
                    startActivity(it);
                    finish();//关闭当前活动
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_start_page);
        myThread.start();//启动线程
    }
}
