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

/**
 * 登录界面
 */
public class LoginAty extends BaseAty {

    private EditText et_name;

    private EditText et_pass;

    private String name;

    private String pass;

    //查询数据用到的类
    private ContactInjfoDao mDao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);
        //找到控件在布局中的位置
        et_name = findViewById(R.id.et_name);
        et_pass = findViewById(R.id.et_pass);
        //实例化mDao 这个类 准备查询数据
        mDao = new ContactInjfoDao(LoginAty.this);
    }

    /**
     * 登录按钮点击事件
     * @param view
     */
    public void loginClick(View view) {
        //获取输入的用户名和密码
        name = et_name.getText().toString().trim();
        pass = et_pass.getText().toString().trim();
        //判断登录名和密码是否为空
        if (TextUtils.isEmpty(name)) {
            showToast("名字不能为空", 1);
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            showToast("密码不能为空", 1);
            return;
        }
        //显示loading对话框
        showLoadingDialog();

        //根据登录名去查找密码
        String pass1 = mDao.alterDate(name);
        //查到的数据进行分割 得到的数据第一个是密码
        String[] temp = pass1.split("-");
        //如果查不到 证明没有注册
        if (pass1.equals("null")) {
            showToast("当前账号没有被注册！", 1);
            dismissLoadingDialog();
            return;
        }
        //如果查询到的和输入的不一样 就是密码错误
        if (!pass.equals(temp[0])) {
            showToast("密码错误！", 1);
            //取消掉loading对话框
            dismissLoadingDialog();
            return;
        } else {
            //取消掉loading对话框
            dismissLoadingDialog();
            //实例化本地存储 的Editor
            SharedPreferences.Editor editor = BaseApplication.getPreferences().edit();
            //存储登录名和登录状态
            editor.putString("name", name);
            editor.putBoolean("islogin", true);
            //提交
            editor.commit();
            //跳转到首页
            startActivity(new Intent(LoginAty.this, MainAty.class));
            showToast("登录成功！", 1);
            finish();
        }

    }

    public void registerClick(View view) {
        //跳转到注册界面
        startActivity(new Intent(LoginAty.this, RegisterAty.class));
        finish();
    }
}
