package com.bin.refusedemo.fgt;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baidu.speech.VoiceRecognitionService;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.bin.refusedemo.R;
import com.bin.refusedemo.aty.SearchAty;
import com.bin.refusedemo.base.BaseFgt;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;


public class SpeakFgt extends BaseFgt {

    //语音按钮
    private ImageView iv_speak;

    private Context mContext;

    private SpeechRecognizer speechRecognizer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fgt_speak, null);
        mContext = getContext();
        iv_speak = view.findViewById(R.id.iv_speak);
        iv_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSpeak();
            }
        });
        return view;

    }

    private void initSpeak() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mContext, new ComponentName(mContext, VoiceRecognitionService.class));
        speechRecognizer.setRecognitionListener(recognitionListener);
        Intent intent = new Intent(getActivity(), BaiduASRDigitalDialog.class);
        Bundle params = new Bundle();
        intent.putExtras(params);
//        intent.putExtra(BaiduConstant.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
//        intent.putExtra(BaiduConstant.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
//        intent.putExtra(BaiduConstant.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
//        intent.putExtra(BaiduConstant.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
//        intent.putExtra(BaiduConstant.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
//        intent.putExtra(BaiduConstant.EXTRA_PROP, 10060);
        startActivityForResult(intent, 2);
    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            StringBuilder sb = new StringBuilder();
            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    sb.append("音频问题");
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    sb.append("没有语音输入");
                    break;
                case SpeechRecognizer.ERROR_CLIENT:

                    sb.append("其它客户端错误");
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    sb.append("权限不足");
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    sb.append("网络问题");
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    sb.append("没有匹配的识别结果");
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    sb.append("引擎忙");
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    sb.append("服务端错误");
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    sb.append("连接超时");
                    break;
            }
            sb.append(":" + error);
            print("识别失败：" + sb.toString());
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            String json_res = results.getString("origin_result");
            print(json_res);
//            et.setText(nbest.get(0));
//            et.setSelection(nbest.get(0).length());
        }

        @Override
        public void onPartialResults(Bundle partialResults) {
            ArrayList<String> nbest = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (nbest.size() > 0) {
//                et.setText(nbest.get(0));
//                et.setSelection(nbest.get(0).length());
            }
        }

        @Override
        public void onEvent(int eventType, Bundle params) {
            switch (eventType) {
                case 11:
                    String reason = params.get("reason") + "";
                    print("EVENT_ERROR, " + reason);
                    break;
                case VoiceRecognitionService.EVENT_ENGINE_SWITCH:
                    int type = params.getInt("engine_type");
                    print("*引擎切换至" + (type == 0 ? "在线" : "离线"));
                    break;
            }
        }
    };

    private void print(String msg) {
        Log.e("SpeakFgt", msg);
    }

    private void onResults(Bundle results) {
        ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        print("识别成功：" + Arrays.toString(nbest.toArray(new String[nbest.size()])));
        String json_res = results.getString("origin_result");
        try {
            print("origin_result=\n" + new JSONObject(json_res).toString(4));
        } catch (Exception e) {
            print("origin_result=[warning: bad json]\n" + json_res);
        }
        Intent intent = new Intent(getContext(), SearchAty.class);
        intent.putExtra("lable", nbest.get(0));
        startActivity(intent);

//        et.setText(nbest.get(0));
//        et.setSelection(nbest.get(0).length());
    }

    /**
     * 重写取得活动返回值的方法
     **/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 2:
                if (intent != null) {
                    onResults(intent.getExtras());
                }
                break;
            default:
        }
    }


}
