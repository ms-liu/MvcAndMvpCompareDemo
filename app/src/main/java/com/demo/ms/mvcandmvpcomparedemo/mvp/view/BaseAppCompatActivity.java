package com.demo.ms.mvcandmvpcomparedemo.mvp.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.demo.ms.mvcandmvpcomparedemo.mvp.view.base.IRootView;

/**
 * Created by ms on 2016/9/5.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements IRootView {

    /**
     * 加载进度dialog
     */
    private ProgressDialog mProgressDialog;
    /**
     *
     */
    private AlertDialog mErrorDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new ProgressDialog(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("网络异常是否重试");
        builder.setNegativeButton("否", null);

        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onPositiveButtonClick();
            }
        });
        mErrorDialog = builder.show();
    }

    public abstract void onPositiveButtonClick();

    @Override
    public void showError() {
        mErrorDialog.show();
    }

    @Override
    public void showLoading() {
        mProgressDialog.setMessage("请稍候...");
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.hide();
    }
}
