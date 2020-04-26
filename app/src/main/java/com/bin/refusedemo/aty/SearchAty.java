package com.bin.refusedemo.aty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bin.refusedemo.R;
import com.bin.refusedemo.base.BaseAty;
import com.bin.refusedemo.bean.BianBean;
import com.bin.refusedemo.bean.TianXingBean;
import com.bin.refusedemo.utils.HistoryUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.util.Parameter;

import java.util.ArrayList;
import java.util.List;

public class SearchAty extends BaseAty {
    //列表
    private RecyclerView rv;
    //列表的数据
    private List<BianBean.ResultBean> mList;

    //列表的适配器
    private HomeAdapter adapter;

    private EditText et_refuse;

    //历史记录列表
    private RecyclerView rv_his;

    private HisAdapter hisAdapter;

    private List<String> hisList;

    private ImageView iv_delete;

    private LinearLayout ll_his;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_search);
        mList = new ArrayList<>();
        hisList = new ArrayList<>();
        rv = findViewById(R.id.rv);
        rv_his = findViewById(R.id.rv_his);
        et_refuse = findViewById(R.id.et_refuse);
        iv_delete = findViewById(R.id.iv_delete);
        ll_his = findViewById(R.id.ll_his);
        if (getIntent() != null) {
            if (getIntent().getStringExtra("lable") != null) {
                String lable = getIntent().getStringExtra("lable");
                et_refuse.setText(lable);
                ll_his.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                initHttpBian(lable);
            }else{
                initHisAdapter();
            }
        }else{
            initHisAdapter();
        }


        et_refuse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ll_his.setVisibility(View.VISIBLE);
                    rv.setVisibility(View.GONE);
                    initHisAdapter();
                    return;
                }
                ll_his.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                initHttpBian(s.toString());
            }
        });

        iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryUtils.deleteHistory(SearchAty.this);
                initHisAdapter();
            }
        });
    }


    private void initHttpBian(String word) {
        HttpRequest.GET(SearchAty.this, "http://api.tianapi.com/txapi/lajifenlei/index",
                new Parameter().add("word", word)
                        .add("key", "72e97b5ee4ca86811e7cf94fc0b7048d"), new ResponseListener() {
                    @Override
                    public void onResponse(String main, Exception error) {
                        if (error == null) {
                            if (mList.size() != 0) {
                                mList.clear();
                            }
                            TianXingBean tianXingBean = new Gson().fromJson(main, TianXingBean.class);

                            if (tianXingBean.getError_code() == 0) {
                                if (tianXingBean.getNewslist() != null) {
                                    for (int i = 0; i < tianXingBean.getNewslist().size(); i++) {
                                        TianXingBean.NewsList newsList = tianXingBean.getNewslist().get(i);
                                        BianBean.ResultBean resultBean = new BianBean.ResultBean();
                                        resultBean.setItemName(newsList.getName());
                                        switch (newsList.getType()) {
                                            case 0:
                                                resultBean.setItemCategory("可回收物");
                                                break;
                                            case 1:
                                                resultBean.setItemCategory("有害垃圾");
                                                break;
                                            case 2:
                                                resultBean.setItemCategory("湿垃圾");
                                                break;
                                            case 3:
                                                resultBean.setItemCategory("干垃圾");
                                                break;
                                            default:
                                        }
                                        mList.add(resultBean);
                                    }
                                }
                                initAdapter();
                            }
                        }
                    }
                });
    }

    //设置列表的数据
    private void initHisAdapter() {
        hisList.clear();
        for (int i = 0; i < HistoryUtils.getSearchHistory(SearchAty.this).size(); i++) {
            String[] temp = HistoryUtils.getSearchHistory(SearchAty.this).get(i).split("-->");
            hisList.add(temp[0]);
        }
        View view = View.inflate(SearchAty.this, R.layout.item_his_no, null);
        //实例化适配器
        hisAdapter = new HisAdapter(R.layout.item_his, hisList);
        rv_his.setLayoutManager(new LinearLayoutManager(SearchAty.this, LinearLayoutManager.VERTICAL, false));
        //列表设置适配器
        rv_his.setAdapter(hisAdapter);
        hisAdapter.setEmptyView(view);
        hisAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < HistoryUtils.getSearchHistory(SearchAty.this).size(); i++) {
                    String[] temp = HistoryUtils.getSearchHistory(SearchAty.this).get(i).split("-->");
                    if (hisList.get(position).equals(temp[0])) {
                        HistoryUtils.saveSearchHistory(SearchAty.this, temp[0] + "-->" + temp[1]);
                        Intent intent = new Intent(SearchAty.this, SearchResultAty.class);
                        intent.putExtra("name", temp[0]);
                        intent.putExtra("type", temp[1]);
                        startActivity(intent);
                        hideInput();
                    }
                }
            }
        });
    }

    //设置列表的数据
    private void initAdapter() {
        //实例化适配器
        View view = View.inflate(SearchAty.this, R.layout.item_no, null);
        adapter = new HomeAdapter(R.layout.item_bian, mList);
        rv.setLayoutManager(new LinearLayoutManager(SearchAty.this, LinearLayoutManager.VERTICAL, false));
        //列表设置适配器
        rv.setAdapter(adapter);
        adapter.setEmptyView(view);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HistoryUtils.saveSearchHistory(SearchAty.this, mList.get(position).getItemName() + "-->" + mList.get(position).getItemCategory());
                Intent intent = new Intent(SearchAty.this, SearchResultAty.class);
                intent.putExtra("name", mList.get(position).getItemName());
                intent.putExtra("type", mList.get(position).getItemCategory());
                startActivity(intent);
                hideInput();
            }
        });
    }

    public void guanbi(View view) {
        et_refuse.setText("");
        mList.clear();
        initAdapter();
    }


    //列表的适配器
    public class HomeAdapter extends BaseQuickAdapter<BianBean.ResultBean, BaseViewHolder> {
        public HomeAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, BianBean.ResultBean item) {
            helper.setText(R.id.tv_his, item.getItemName());
        }
    }

    //列表的适配器
    public class HisAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public HisAdapter(int layoutResId, List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_his, item);
        }
    }

}
