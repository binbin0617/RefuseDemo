package com.bin.refusedemo.aty;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseAty;
import com.bin.refusedemo.fgt.HomePageFgt;
import com.bin.refusedemo.fgt.MineFgt;
import com.bin.refusedemo.fgt.SpeakFgt;
import com.bin.refusedemo.fgt.TestFgt;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainAty extends BaseAty {

    //首页底部四个文字显示
    private String[] tabText = {"首页", "语音", "测试", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.icon_home_black, R.mipmap.icon_speak_black, R.mipmap.icon_zidian_black, R.mipmap.icon_my_black};
    //选中时icon
    private int[] selectIcon = {R.mipmap.icon_home_blue, R.mipmap.icon_speak_blue, R.mipmap.icon_zidian_blue, R.mipmap.icon_my_blue};

    //首页的四个Fragment
    private List<Fragment> fragments = new ArrayList<>();


    private EasyNavigationBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_main);
        initView();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.MODIFY_AUDIO_SETTINGS) != PackageManager.PERMISSION_GRANTED
//                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.ACCESS_NETWORK_STATE,
//                        Manifest.permission.INTERNET,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MODIFY_AUDIO_SETTINGS}, 00000000);
//            }
//        }
        //动态申请权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                ||ContextCompat.checkSelfPermission(this, Manifest.permission.CHANGE_WIFI_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE}, 00000000);
            }
        }

    }

    public void initView() {
        //找到布局文件中的navigationBar
        navigationBar = findViewById(R.id.navigationBar);


        //添加fragment布局
        fragments.add(new HomePageFgt());
        fragments.add(new SpeakFgt());
        fragments.add(new TestFgt());
        fragments.add(new MineFgt());

        //navigationBar进行设置
        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .build();
    }


    /**
     * HomePageFgt  四种分类的点击事件
     *
     * @param view
     */
    public void kehuishou(View view) {
        tiao("1");
    }

    public void ganlaji(View view) {
        tiao("2");
    }

    public void shilaji(View view) {
        tiao("4");
    }

    public void youhailaji(View view) {
        tiao("3");
    }

    private void tiao(String type) {
        Intent intent = new Intent(MainAty.this, DetailsAty.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
