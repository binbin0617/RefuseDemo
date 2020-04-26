package com.bin.refusedemo.fgt;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin.refusedemo.R;
import com.bin.refusedemo.aty.LoginAty;
import com.bin.refusedemo.base.BaseApplication;
import com.bin.refusedemo.base.BaseFgt;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import static android.app.Activity.RESULT_OK;

public class MineFgt extends BaseFgt {

//    private TextView tv_xinxi;
//
//    private TextView tv_baojing;

    private TextView tv_haoping;

    private TextView tv_yijian;

    private TextView tv_jiancha;

    private TextView tv_guanyu;

    private LinearLayout ll_shezhi;

    private TextView tv_name;

    private ImageView iv_head;

    private int REQUEST_CODE_CHOOSE = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fgt_mine, null);
//        tv_xinxi = view.findViewById(R.id.tv_xinxi);
//        tv_baojing = view.findViewById(R.id.tv_baojing);
        tv_haoping = view.findViewById(R.id.tv_haoping);
        tv_yijian = view.findViewById(R.id.tv_yijian);
        tv_jiancha = view.findViewById(R.id.tv_jiancha);
        tv_guanyu = view.findViewById(R.id.tv_guanyu);
        ll_shezhi = view.findViewById(R.id.ll_shezhi);
        tv_name = view.findViewById(R.id.tv_name);
        iv_head = view.findViewById(R.id.iv_head);
        String name = BaseApplication.getPreferences().getString("name", "");
        if (!TextUtils.isEmpty(name)) {
            tv_name.setText(name);
        }
        initClick();
        return view;
    }

    private void initClick() {
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
                startActivityForResult(intentToPickPic, REQUEST_CODE_CHOOSE);
            }
        });
//        tv_xinxi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                startActivity(new Intent(getContext(), SettingAty.class));
//            }
//        });
//        tv_baojing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:15810277571"));
//                startActivity(intent);
//            }
//        });
        tv_yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getContext(), FeedBackAty.class));
            }
        });
        tv_jiancha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("当前已是最新版本", 1);
            }
        });
        tv_guanyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setTitle("关于");
                builder.setMessage("Version 1.0");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        builder.create().dismiss();

                    }
                });
                builder.create().show();
            }
        });
        //退出登录
        ll_shezhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = BaseApplication.getPreferences().edit();
                editor.putString("name", "");
                editor.putBoolean("islogin", false);
                editor.commit();
                startActivity(new Intent(getContext(), LoginAty.class));
                showToast("退出登录成功！", 1);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 获取图片
            try {
                //该uri是上一个Activity返回的
                Uri imageUri = data.getData();
                if(imageUri!=null) {
                    Glide.with(getActivity()).load(imageUri).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
