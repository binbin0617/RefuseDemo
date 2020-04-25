package com.bin.refusedemo.base;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bin.refusedemo.R;

/**
 *  所有Fragment 的基类  存放一些公用的方法
 */
public class BaseFgt extends Fragment {
    public void showToast(String toast, int flg) {
        // 短toast
        if (flg == 1) {
            Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT).show();
        } else {
            // 长toast
            Toast.makeText(getContext(), toast, Toast.LENGTH_LONG).show();
        }
    }

    private AlertDialog alertDialog;

    public void showLoadingDialog() {
        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable());
        alertDialog.setCancelable(false);
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK)
                    return true;
                return false;
            }
        });
        alertDialog.show();
        alertDialog.setContentView(R.layout.loading_alert);
        alertDialog.setCanceledOnTouchOutside(false);
    }

    public void dismissLoadingDialog() {
        if (null != alertDialog && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }
}
