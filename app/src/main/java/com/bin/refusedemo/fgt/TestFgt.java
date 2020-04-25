package com.bin.refusedemo.fgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bin.refusedemo.R;
import com.bin.refusedemo.aty.TestAty;
import com.bin.refusedemo.base.BaseFgt;

public class TestFgt extends BaseFgt {

    private Button btn_go;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fgt_test, null);
        btn_go = view.findViewById(R.id.btn_go);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TestAty.class));
            }
        });
        return view;
    }
}
