package com.zj.loadingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public Dialog mDialog;

    public void initLoadDialog(String content) {
        if (mDialog == null) {
            mDialog = getLoadingDialog(MainActivity.this, content);
            mDialog.setCancelable(false);
        } else {
            dialogTvContent.setText(content);
        }

        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    TextView dialogTvContent;

    public  Dialog getLoadingDialog(Context context, String content) {
        Dialog dialog = new Dialog(context, R.style.AlertDialogStyle);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        dialogTvContent = view.findViewById(R.id.tv_note);
        if (TextUtils.isEmpty(content)) {
            dialogTvContent.setVisibility(View.GONE);
        } else {
            dialogTvContent.setText(content);
        }
        dialog.setContentView(view, new ViewGroup.LayoutParams(DensityUtil.dp2px(context, 96), DensityUtil.dp2px(context, 96)));
        return dialog;
    }

    public void dismissLoadDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    public void testShowLoading(View view) {
        initLoadDialog("请等待");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismissLoadDialog();
            }
        }, 2000);
    }
}