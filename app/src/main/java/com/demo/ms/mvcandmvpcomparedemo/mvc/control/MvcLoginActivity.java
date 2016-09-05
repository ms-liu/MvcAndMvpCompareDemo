package com.demo.ms.mvcandmvpcomparedemo.mvc.control;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
import android.inputmethodservice.Keyboard;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.demo.ms.mvcandmvpcomparedemo.R;
import com.demo.ms.mvcandmvpcomparedemo.mvc.model.UserInfoBean;
import com.demo.ms.mvcandmvpcomparedemo.uitls.SharedPreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Mvc层中Controller层
 *    负责数据的加载、传递
 *     相较于MVP的Activity 在传统的MVC的Activity中，代码多，任务繁重，既要操作数据，还要控制UI的显示
 *     耦合程度差
 * Created by ms on 2016/9/2.
 */
public class MvcLoginActivity extends AppCompatActivity {

    public static final String KEY_USERNAME = "USERNAME";
    public static final String KEY_PWD = "PWD";

    private Toolbar mToolbar;
    private android.support.v7.app.ActionBar actionBar;

    @Bind(R.id.et_username)
    AppCompatEditText mEtUserName;
    @Bind(R.id.et_pwd)
    AppCompatEditText mEtPwd;
    @Bind(R.id.btn_save)
    Button mBtnSave;

    @Bind(R.id.btn_clear)
    Button mBtnClear;

    @Bind(R.id.tv_username)
    TextView mTvUsername;

    @Bind(R.id.tv_pwd)
    TextView mTvPwd;

    @Bind(R.id.tv_model_desc)
    TextView mTvDesc;
    private UserInfoBean mUserInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 数据加载
     *      正常子线程
     */
    private void loadData() {
        mUserInfo = getUserInfo();
        setDataToView();
    }

    /**
     * 数据设置给View
     *
     */
    private void setDataToView() {
        mEtUserName.setText(mUserInfo.userName);
        mEtPwd.setText(mUserInfo.userPwd);
        mTvDesc.setText(getResources().getString(R.string.action_mvc_desc));
    }

    /**
     * 具体获取用户信息方法
     * @return
     */
    private UserInfoBean getUserInfo() {
        showProgressDialog();
        String username = SharedPreferencesUtils.getString(this, KEY_USERNAME, "");
        String pwd = SharedPreferencesUtils.getString(this, KEY_PWD, "");
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.userName = username;
        userInfoBean.userPwd = pwd;
        return userInfoBean;
    }

    /**
     * 进度框
     */
    private void showProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("请稍候...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
            }
        },500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    /**
     * 隐藏键盘
     * @param v
     */
    public void hideSystemKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * 保存用户信息
     */
    @OnClick(R.id.btn_save)
    protected void save(){
        showProgressDialog();
        hideSystemKeyBoard(mEtUserName);
        hideSystemKeyBoard(mEtPwd);
        String username = mEtUserName.getText().toString();
        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)){
            Snackbar snackbar = Snackbar.make(mBtnSave, "输入信息有误！", Snackbar.LENGTH_SHORT);
            View view = snackbar.getView();
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            snackbar.show();
            return;
        }
        showToView(username,pwd);
        saveUserInfo(username,pwd);
    }

    /**
     * 保存用户信息具体方法
     * @param username
     * @param pwd
     */
    private void saveUserInfo(String username, String pwd) {
        SharedPreferencesUtils.saveString(this,KEY_USERNAME,username);
        SharedPreferencesUtils.saveString(this,KEY_PWD,pwd);
    }

    /**
     * 展示 用户信息
     * @param username
     * @param pwd
     */
    private void showToView(String username, String pwd){
        mTvUsername.setText(username);
        mTvPwd.setText(pwd);
    }

    /**
     * 清除用户信息
     */
    @OnClick(R.id.btn_clear)
    protected void clear(){
        showProgressDialog();
        hideSystemKeyBoard(mEtUserName);
        hideSystemKeyBoard(mEtPwd);
        mEtUserName.setText("");
        mEtPwd.setText("");
        mUserInfo = null;
        showToView("","");
        saveUserInfo("","");
    }
}
