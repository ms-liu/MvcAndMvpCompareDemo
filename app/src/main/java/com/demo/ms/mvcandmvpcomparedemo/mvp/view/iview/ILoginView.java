package com.demo.ms.mvcandmvpcomparedemo.mvp.view.iview;

import com.demo.ms.mvcandmvpcomparedemo.mvc.model.UserInfoBean;
import com.demo.ms.mvcandmvpcomparedemo.mvp.view.base.IRootView;

/**
 * 登录页面的接口 View
 *      用于与LoginPresenter作交互
 * Created by ms on 2016/9/5.
 */
public interface ILoginView extends IRootView {
    /**
     * 与view的数据交互的方法
     * 可以自定义不同名称
     * 设置数据给view
     *  set data to view
     * @param userInfoBean
     */
    void setUserInfoData(UserInfoBean userInfoBean);

    /**
     * 结果通知
     * 保存数据成功
     *  custom to your view
     * @param userName
     * @param pwd
     */
    void saveDataSuccess(String userName, String pwd);

    /**
     * 结果通知
     * 清除数据成功
     *   custom to your view
     * @param userName
     * @param pwd
     */
    void clearDataSuccess(String userName, String pwd);

    /**
     *
     * @param mvpDesc
     */
    void setMvpDesc(String mvpDesc);
}
