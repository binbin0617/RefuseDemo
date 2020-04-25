package com.bin.refusedemo.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.refusedemo.R;
import com.bin.refusedemo.adapter.ImageAdapter;
import com.bin.refusedemo.aty.SearchAty;
import com.bin.refusedemo.base.BaseFgt;
import com.kongzue.stacklabelview.StackLabel;
import com.kongzue.stacklabelview.interfaces.OnLabelClickListener;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class HomePageFgt extends BaseFgt {

    private TextView et_refuse;

    //轮播图插件
    private Banner banner;

    //存储轮播图图片的集合
    private List<Integer> mList;

    //堆叠标签组件
    private StackLabel stackLabelView;

    //堆叠标签组件的数据
    private List<String> labels;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fgt_home_page, null);
        et_refuse = view.findViewById(R.id.et_refuse);
        banner = view.findViewById(R.id.banner);
        stackLabelView = view.findViewById(R.id.stackLabelView);
        initView();
        return view;
    }

    //初始化事件
    private void initView() {

        //实例化数组并添加数据
        mList = new ArrayList<>();
        mList.add(R.mipmap.index1);
        mList.add(R.mipmap.index2);
        mList.add(R.mipmap.index3);
        mList.add(R.mipmap.index1);
        labels = new ArrayList<>();
        labels.add("骨头");
        labels.add("塑料袋");
        labels.add("快递");
        labels.add("袋子");
        labels.add("核桃壳");
        labels.add("烟盒");
        labels.add("外卖");
        labels.add("电池");
        stackLabelView.setLabels(labels);
        //设置轮播图属性
        banner.setAdapter(new ImageAdapter(mList))
                .setOrientation(Banner.HORIZONTAL)
                .setIndicator(new CircleIndicator(getContext()))
                .setUserInputEnabled(true);

        //堆叠标签组件的点击事件
        stackLabelView.setOnLabelClickListener(new OnLabelClickListener() {
            @Override
            public void onClick(int index, View v, String s) {
                Intent intent = new Intent(getContext(), SearchAty.class);
                intent.putExtra("lable", labels.get(index));
                startActivity(intent);
            }
        });

        et_refuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchAty.class));
            }
        });


        //软键盘上面的搜索按钮
//        et_refuse.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                /*判断是否是“搜索”键*/
//                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
////                    hideInput();
////                    word = et.getText().toString();
////                    if (TextUtils.isEmpty(word)) {
////                        Toast.makeText(MainActivity.this,
////                                "请输入关键字", Toast.LENGTH_SHORT).show();
////                        return true;
////                    }
////                    initHttp();
//                    return true;
//                }
//                return false;
//            }
//        });
    }

}
