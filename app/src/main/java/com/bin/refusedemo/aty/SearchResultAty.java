package com.bin.refusedemo.aty;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseAty;

public class SearchResultAty extends BaseAty {

    private String name = "";

    private String type = "";

    private TextView tv_title;

    private TextView tv1;

    private TextView tv2;

    private TextView tv3;

    private TextView tv4;

    private TextView tv_name;

    private TextView tv_type;

    private ImageView iv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_search_result);
        tv_title = findViewById(R.id.tv_title);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv_name = findViewById(R.id.tv_name);
        tv_type = findViewById(R.id.tv_type);
        iv = findViewById(R.id.iv);
        if (getIntent() != null) {
            if (getIntent().getStringExtra("name") != null) {
                name = getIntent().getStringExtra("name");
                type = getIntent().getStringExtra("type");
                tv_name.setText(name);
                tv_type.setText(type);
                switch (type) {
                    case "湿垃圾":
                        tv_title.setText("干垃圾投放指导");
                        tv1.setText("危害较小,但无再次利用价值,如建筑垃圾类,生活垃圾类等,一般采取填埋、焚烧卫生分解等方法,部分还可以使用生物解决,如放蚯蚓等。是可回收垃圾、厨余垃圾、有害垃圾剩余下来的一种垃圾。");
                        tv2.setText("");
                        tv3.setText("");
                        tv4.setText("");
                        iv.setImageResource(R.mipmap.icon_shi);
                        break;
                    case "可回收垃圾":
                        tv_title.setText("可回收物投放指导");
                        tv1.setText("1、可回收物应轻投轻放，清洁干燥、避免污染;");
                        tv2.setText("2、废纸尽量平整;");
                        tv3.setText("3、立体包装物请清空内容物，清洁后压扁投放;");
                        tv4.setText("4、有尖锐边角的，应包裹后投放");
                        iv.setImageResource(R.mipmap.icon_huishou);
                        break;
                    case "干垃圾":
                        tv_title.setText("湿垃圾投放指导");
                        tv1.setText("1、厨余垃圾(餐厨垃圾)应从产生时就与其他类别垃圾分开归类,去除食材食品的包装物,不得混入纸巾餐具、厨房用具等;");
                        tv2.setText("2、餐厨垃圾中的废弃食用油脂,应单独存放、交付餐厨垃圾收集运输单位;");
                        tv3.setText("3、难以生物降解的贝壳、大骨头、毛发等,宜作为其他垃圾投放;");
                        tv4.setText("4、厨余垃圾(餐厨垃圾)滤去水分后再投放。");
                        iv.setImageResource(R.mipmap.icon_gan);
                        break;
                    case "有害垃圾":
                        tv_title.setText("有害垃圾投放指导");
                        tv1.setText("1、有害垃圾指废电池、废灯管、废药品、废油漆及其容器等对人体健康或者自然环境造成直接或者潜在危害的生活废弃物;");
                        tv2.setText("2、常见包括废电池、废烫光灯管、废灯泡、废水银温度计、废油漆桶、过期药品等。有害有毒垃圾需特殊正确的方法安全处。");
                        tv3.setText("");
                        tv4.setText("");
                        iv.setImageResource(R.mipmap.icon_youhai);
                        break;
                    default:
                }
            }
        }
    }

}
