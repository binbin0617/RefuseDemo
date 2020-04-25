package com.bin.refusedemo.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bin.refusedemo.R;

/**
 *  所有  Activity 的基类  存放一些公用的方法
 */

public class BaseAty extends AppCompatActivity {

    public void showToast(String toast, int flg) {
        // 短toast
        if (flg == 1) {
            Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        } else {
            // 长toast
            Toast.makeText(this, toast, Toast.LENGTH_LONG).show();
        }
    }

    public void finish(View view) {
        this.finish();
    }

    //网络请求加载的Loading框
    private AlertDialog alertDialog;
    //Loading框显示
    public void showLoadingDialog() {
        //实例化
        alertDialog = new AlertDialog.Builder(this).create();
        //设置背景
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        //dialog弹出后会点击屏幕或物理返回键，dialog不消失
        alertDialog.setCancelable(false);
        //设置点击事件
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        //显示
        alertDialog.show();
        //设置布局样式
        alertDialog.setContentView(R.layout.loading_alert);
        //dialog弹出后会点击屏幕，dialog不消失；点击物理返回键dialog消失
        alertDialog.setCanceledOnTouchOutside(false);
    }

    //取消loading
    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    /**
     * 隐藏键盘
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
