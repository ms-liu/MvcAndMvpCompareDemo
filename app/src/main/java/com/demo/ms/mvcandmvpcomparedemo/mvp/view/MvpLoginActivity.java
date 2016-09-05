package com.demo.ms.mvcandmvpcomparedemo.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.demo.ms.mvcandmvpcomparedemo.R;
import com.demo.ms.mvcandmvpcomparedemo.mvc.model.UserInfoBean;
import com.demo.ms.mvcandmvpcomparedemo.mvp.presenter.LoginPresenter;
import com.demo.ms.mvcandmvpcomparedemo.mvp.view.iview.ILoginView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  MVP中的View层
 *     负责数据的显示
 * Created by ms on 2016/9/2.
 */
public class MvpLoginActivity extends BaseAppCompatActivity implements ILoginView {

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
    private LoginPresenter mPresenter;
    private ActionBar actionBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        createAndBindPresenter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onPositiveButtonClick() {
        mPresenter.loadData(false);
    }

    private void createAndBindPresenter() {
        mPresenter = new LoginPresenter(this);
        mPresenter.bindView(this);
        mPresenter.loadData(true);
        mPresenter.loadMvpDesc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /*------------------Mvp中View层要实现的方法-------------------------------------------------*/
    @Override
    public void setUserInfoData(UserInfoBean userInfoBean) {
        mEtUserName.setText(userInfoBean.userName);
        mEtPwd.setText(userInfoBean.userPwd);
    }

    @Override
    public void saveDataSuccess(String userName, String pwd) {
        showSnack("保存成功！");
        mTvUsername.setText(userName);
        mTvPwd.setText(pwd);
    }

    @Override
    public void clearDataSuccess(String userName, String pwd) {
        showSnack("清除成功！");
        mEtUserName.setText(userName);
        mEtPwd.setText(pwd);
        mTvUsername.setText(userName);
        mTvPwd.setText(pwd);
    }

    @Override
    public void setMvpDesc(String mvpDesc) {
        mTvDesc.setText(mvpDesc);
    }

    private void showSnack(String message) {
        Snackbar snackbar = Snackbar.make(mBtnSave, message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        snackbar.show();
    }

    /*---------------------点击事件--------------------------------------------------------*/
    @OnClick(R.id.btn_save)
    public void save(){
        hideSystemKeyBoard(mEtUserName);
        hideSystemKeyBoard(mEtPwd);
        String userName = mEtUserName.getText().toString();
        String pwd = mEtPwd.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(pwd)){
            showSnack("信息有误！");
        }else {
            mPresenter.saveData(userName,pwd);
        }
    }

    @OnClick(R.id.btn_clear)
    public void clear(){
        hideSystemKeyBoard(mEtUserName);
        hideSystemKeyBoard(mEtPwd);
        mPresenter.saveData("","");
    }

    /**
     * 隐藏键盘
     * @param v
     */
    public void hideSystemKeyBoard(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

}
