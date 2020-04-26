package com.bin.refusedemo.aty;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseAty;
import com.bin.refusedemo.bean.RefuseBean;

import java.util.ArrayList;
import java.util.List;

public class TestAty extends BaseAty {
    private TextView btn_sub1;

    private List<RefuseBean> mList;

    private TextView select1;

    private RadioGroup select1_1;
    //    题目
    private int num = 0;
    //分数
//    private int count = 0;

    private boolean isXaun = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_test);
        select1 = findViewById(R.id.tv_timu);
        select1_1 = findViewById(R.id.select1_1);
        mList = new ArrayList<>();
        //  A  可回收物  B  干垃圾  C  有害垃圾 D  湿垃圾
        RefuseBean refuseBean1 = new RefuseBean();
        refuseBean1.setName("苹果皮属于什么垃圾?");
        refuseBean1.setType("D  湿垃圾");
        RefuseBean refuseBean2 = new RefuseBean();
        refuseBean2.setName("书本属于什么垃圾?");
        refuseBean2.setType("A  可回收物");
        RefuseBean refuseBean3 = new RefuseBean();
        refuseBean3.setName("苹果皮属于什么垃圾?");
        refuseBean3.setType("D  湿垃圾");
        RefuseBean refuseBean4 = new RefuseBean();
        refuseBean4.setName("电池属于什么垃圾?");
        refuseBean4.setType("C  有害垃圾");
        RefuseBean refuseBean5 = new RefuseBean();
        refuseBean5.setName("眼镜盒属于什么垃圾?");
        refuseBean5.setType("B  干垃圾");
        RefuseBean refuseBean6 = new RefuseBean();
        refuseBean6.setName("隐形眼镜包装盒属于什么垃圾?");
        refuseBean6.setType("A  可回收物");
        RefuseBean refuseBean7 = new RefuseBean();
        refuseBean7.setName("眼镜架属于什么垃圾?");
        refuseBean7.setType("B  干垃圾");
        RefuseBean refuseBean8 = new RefuseBean();
        refuseBean8.setName("红苹果属于什么垃圾?");
        refuseBean8.setType("D  湿垃圾");
        RefuseBean refuseBean9 = new RefuseBean();
        refuseBean9.setName("苹果耳机属于什么垃圾?");
        refuseBean9.setType("A  可回收物");
        RefuseBean refuseBean10 = new RefuseBean();
        refuseBean10.setName("一次性塑料袋属于什么垃圾?");
        refuseBean10.setType("B  干垃圾");

        mList.add(refuseBean1);
        mList.add(refuseBean2);
        mList.add(refuseBean3);
        mList.add(refuseBean4);
        mList.add(refuseBean5);
        mList.add(refuseBean6);
        mList.add(refuseBean7);
        mList.add(refuseBean8);
        mList.add(refuseBean9);
        mList.add(refuseBean10);


        num = (int) Math.floor(Math.random() * 10 + 1);
        select1.setText(mList.get(num - 1).getName());
        btn_sub1 = findViewById(R.id.btn_sub1);

        select1_1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                isXaun = true;
            }
        });
        btn_sub1.setOnClickListener(new View.OnClickListener() {
            //计分
            Double count = 0.00;
//            boolean isoncl = true;

            @Override
            public void onClick(View v) {
                TextView answer = (TextView) findViewById(R.id.answer);
                showLoadingDialog();
                if (!isXaun) {
                    dismissLoadingDialog();
                    showToast("请选择答案", 1);
                    return;
                }
//                if (isoncl) {
//                    isoncl = false;
                String select11 = select1.getText().toString();
                if (select11.equals(mList.get(num - 1).getName())) {
                    for (int i = 0; i < select1_1.getChildCount(); i++) {
                        RadioButton select1_1_1 = (RadioButton) select1_1.getChildAt(i);
                        String select1_1_11 = select1_1_1.getText().toString();
                        if (select1_1_1.isChecked()) {
                            if (select1_1_11.equals(mList.get(num - 1).getType())) {
//                                count += 10;
                                //Toast.makeText(getApplicationContext(), "选择对了哦！", Toast.LENGTH_SHORT).show();
                                switch (i) {
                                    case 0:
                                        answer.setText("您当前答案是 A ,真棒！！！答对了呢");
                                        break;
                                    case 1:
                                        answer.setText("您当前答案是 B ,真棒！！！答对了呢");
                                        break;
                                    case 2:
                                        answer.setText("您当前答案是 C ,真棒！！！答对了呢");
                                        break;
                                    case 3:
                                        answer.setText("您当前答案是 D ,真棒！！！答对了呢");
                                        break;
                                }

                            } else {
                                answer.setText("答错了，正确答案是    " + mList.get(num - 1).getType());
                            }
                            break;
                        }
                    }
                }
                dismissLoadingDialog();
                select1_1.clearCheck();
                num = (int) Math.floor(Math.random() * 10 + 1);
                select1.setText(mList.get(num - 1).getName());
            }
        });
    }
}
