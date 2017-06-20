package com.example.mvp_newsdemo.view;

import android.app.Activity;

/**
 * Created by Administrator on 2017/6/5.
 */

public interface BaseFragmentI {

    //展示成功获取数据的page
    void showSuccessPage(Object bean);

    void showProcessDialog();
    void hideProcessDialog();

    void showErrorNetWorkInfo(Activity context);
}
