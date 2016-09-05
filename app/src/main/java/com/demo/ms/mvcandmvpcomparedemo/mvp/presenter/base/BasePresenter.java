package com.demo.ms.mvcandmvpcomparedemo.mvp.presenter.base;

import android.content.Context;
import android.test.FlakyTest;

import com.demo.ms.mvcandmvpcomparedemo.mvp.view.base.IRootView;

/**
 * MVP中的Presenter层
 *  负责数据加载、传递
 * Created by ms on 2016/9/2.
 */
public abstract class BasePresenter<V extends IRootView> implements IPresenter<V> {

    public final Context mCtx;
    private V mView;

    /**
     * @param context 控制某些控件 如：Dialog,Toast
     */
    public BasePresenter(Context context){
        this.mCtx = context;
    }

    /**
     * 绑定
     * @param view
     */
    @Override
    public void bindView(V view) {
        this.mView = view;
    }

    /**
     * 解绑
     */
    @Override
    public void unBindView() {
        this.mView = null;
    }

    /**
     * 获取绑定View
     * @return
     */
    public V getView(){
        return mView;
    }

    /**
     * 检查是否绑定
     */
    public void checkIsBind() {
        if (!isBind()){
            throw new IllegalStateException("You need bind your IMvpView before you request data to the presenter");
        }
    }

    private boolean isBind() {
        return mView != null;
    }

}
