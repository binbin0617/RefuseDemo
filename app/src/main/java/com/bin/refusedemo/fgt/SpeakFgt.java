package com.bin.refusedemo.fgt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin.refusedemo.R;
import com.bin.refusedemo.aty.SearchAty;
import com.bin.refusedemo.base.BaseFgt;
import com.bin.refusedemo.utils.JsonParser;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class SpeakFgt extends BaseFgt {

    //语音按钮
    private ImageView iv_speak;

    private Context mContext;

    private RecognizerDialog mIatDialog;

    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    private boolean tiao = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fgt_speak, null);
        mContext = getContext();
        iv_speak = view.findViewById(R.id.iv_speak);
        iv_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        return view;

    }

    public void click() {
        // 使用UI听写功能，请根据sdk文件目录下的notice.txt,放置布局文件和图片资源
        mIatDialog = new RecognizerDialog(getContext(), mInitListener);

        //以下为dialog设置听写参数
        mIatDialog.setParameter(SpeechConstant.RESULT_TYPE, "json");
        //设置语音输入语言，zh_cn为简体中文
        mIatDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        //设置结果返回语言
        mIatDialog.setParameter(SpeechConstant.ACCENT, "mandarin");
        // 设置语音前端点:静音超时时间，单位ms，即用户多长时间不说话则当做超时处理
        //取值范围{1000～10000}
        mIatDialog.setParameter(SpeechConstant.VAD_BOS, "4500");
        //设置语音后端点:后端点静音检测时间，单位ms，即用户停止说话多长时间内即认为不再输入，
        //自动停止录音，范围{0~10000}
        mIatDialog.setParameter(SpeechConstant.VAD_EOS, "1500");

        //开始识别并设置监听器
        mIatDialog.setListener(mRecognizerDialogListener);
        //显示听写对话框
        mIatDialog.show();
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
            Log.d("ssssssssss", "SpeechRecognizer init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                Log.d("ssssssssss", "初始化失败，错误码：" + code + ",请点击网址https://www.xfyun.cn/document/error-code查询解决方案");
            }
        }
    };

    /**
     * 听写UI监听器
     */
    private RecognizerDialogListener mRecognizerDialogListener = new RecognizerDialogListener() {
        public void onResult(RecognizerResult results, boolean isLast) {

            String text = JsonParser.parseIatResult(results.getResultString());
            String sn = null;
            // 读取json结果中的sn字段
            try {
                JSONObject resultJson = new JSONObject(results.getResultString());
                sn = resultJson.optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults.put(sn, text);

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults.get(key));
            }

//            mResultText.setText(resultBuffer.toString());
//            mResultText.setSelection(mResultText.length());
            Log.d("ssssssssss", resultBuffer.toString());
            if(tiao){
                Intent intent = new Intent(getContext(), SearchAty.class);
                intent.putExtra("lable", resultBuffer.toString());
                startActivity(intent);
                tiao = false;
            }

        }

        /**
         * 识别回调错误.
         */
        public void onError(SpeechError error) {
            Log.d("ssssssssss", error.getPlainDescription(true));

        }

    };


}
