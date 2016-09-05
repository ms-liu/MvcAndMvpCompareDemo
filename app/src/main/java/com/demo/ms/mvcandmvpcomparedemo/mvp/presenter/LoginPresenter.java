package com.demo.ms.mvcandmvpcomparedemo.mvp.presenter;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;

import com.demo.ms.mvcandmvpcomparedemo.R;
import com.demo.ms.mvcandmvpcomparedemo.mvc.control.MvcLoginActivity;
import com.demo.ms.mvcandmvpcomparedemo.mvc.model.UserInfoBean;
import com.demo.ms.mvcandmvpcomparedemo.mvp.presenter.base.BasePresenter;
import com.demo.ms.mvcandmvpcomparedemo.mvp.view.iview.ILoginView;
import com.demo.ms.mvcandmvpcomparedemo.uitls.SharedPreferencesUtils;


/**
 * Mvp中Presenter
 *  用于数据的处理
 * Created by ms on 2016/9/5.
 */
public class LoginPresenter extends BasePresenter<ILoginView>{

    /**
     * @param context 控制某些控件 如：Dialog,Toast
     */
    public LoginPresenter(Context context) {
        super(context);
    }

    @Override
    public void bindView(ILoginView view) {
        super.bindView(view);


    }

    @Override
    public void unBindView() {
        super.unBindView();
    }

    /**
     * load data form DB、Internet、local
     * @param isFirstReq 该参数没有任何意义 只是为了展示Error状态 可以自定义一些有用参数
     */
    public void loadData(boolean isFirstReq){
        //suggest call this method
        checkIsBind();
        getView().showLoading();
        doYourLoadMethod(isFirstReq);
    }

    private void doYourLoadMethod(final boolean isFirstReq) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getView().hideLoading();
                String username = SharedPreferencesUtils.getString(mCtx, MvcLoginActivity.KEY_USERNAME, "");
                String pwd = SharedPreferencesUtils.getString(mCtx, MvcLoginActivity.KEY_PWD, "");
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(pwd)){
                    if (isFirstReq){
                        getView().showError();
                    }
                }else {
                    UserInfoBean userInfoBean = new UserInfoBean();
                    userInfoBean.userName = username;
                    userInfoBean.userPwd = pwd;
                    getView().setUserInfoData(userInfoBean);
                }
            }
        },500);
    }


    public void saveData(final String userName, final String pwd){
        getView().showLoading();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getView().hideLoading();
                    SharedPreferencesUtils.saveString(mCtx, MvcLoginActivity.KEY_USERNAME, userName);
                    SharedPreferencesUtils.saveString(mCtx, MvcLoginActivity.KEY_PWD, pwd);
                    if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd)){
                        getView().clearDataSuccess("","");
                    }else {
                        getView().saveDataSuccess(userName,pwd);
                    }
                }
            }, 500);
    }

    public void loadMvpDesc() {
        getView().setMvpDesc(mCtx.getResources().getString(R.string.action_mvp_desc));
    }
}
