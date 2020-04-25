package com.bin.refusedemo.aty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseAty;
import com.bin.refusedemo.utils.ContactInjfoDao;

public class RegisterAty extends BaseAty {

    private String TAG = RegisterAty.class.getSimpleName();

    private EditText et_name;

    private EditText et_pass;

    private String name;

    private String pass;

    private ContactInjfoDao mDao;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_register);

        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);

        mDao = new ContactInjfoDao(RegisterAty.this);

    }


    public void loginClick(View view) {
        name = et_name.getText().toString().trim();
        pass = et_pass.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("名字不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            showToast("密码不能为空", 1);
            return;
        }
        showLoadingDialog();
        long addLong = mDao.addDate(name, pass, "", "");
        if (addLong == -1) {
            dismissLoadingDialog();
            showToast("注册失败", 1);
        } else {
            dismissLoadingDialog();
            Log.e(TAG, "数据添加在第  " + addLong + "   行");
            showToast("注册成功，请登录", 1);
            startActivity(new Intent(RegisterAty.this, LoginAty.class));
            finish();
        }
    }

    public void registerClick(View view) {
        startActivity(new Intent(RegisterAty.this, LoginAty.class));
        finish();
    }
}
