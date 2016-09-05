package com.demo.ms.mvcandmvpcomparedemo.mvp.presenter.base;

import com.demo.ms.mvcandmvpcomparedemo.mvp.view.base.IRootView;

/**
 * Presenter 抽象接口类
 * Created by ms on 2016/9/2.
 */
public interface IPresenter<V extends IRootView> {
    void bindView(V v);
    void unBindView();
}
