package com.bin.refusedemo.aty;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseApplication;
import com.bin.refusedemo.base.BaseAty;
import com.bin.refusedemo.utils.ContactInjfoDao;


public class LoginAty extends BaseAty {

    private EditText et_name;

    private EditText et_pass;

    private String name;

    private String pass;


    private ContactInjfoDao mDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);
        mDao = new ContactInjfoDao(LoginAty.this);
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

        String pass1 = mDao.alterDate(name);

        String[] temp = pass1.split("-");
        if(temp.length<=0){
            showToast("登录失败！", 1);
            return;
        }
        if (TextUtils.isEmpty(pass1)) {
            showToast("当前账号没有被注册！", 1);
            dismissLoadingDialog();
            return;
        }
        if (!pass.equals(temp[0])) {
            showToast("密码错误！", 1);
            dismissLoadingDialog();
            return;
        } else {
            dismissLoadingDialog();
            SharedPreferences.Editor editor = BaseApplication.getPreferences().edit();
            editor.putString("name", name);
            editor.putBoolean("islogin", true);
            editor.commit();
            startActivity(new Intent(LoginAty.this, MainAty.class));
            showToast("登录成功！", 1);
            finish();
        }

    }

    public void registerClick(View view) {
        startActivity(new Intent(LoginAty.this, RegisterAty.class));
        finish();
    }
}
