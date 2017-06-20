package com.example.mvp_newsdemo.view;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2017/6/5.
 */

public interface MainInterface {

    void swichFragment();

    void showFragment(android.support.v4.app.FragmentManager fm, Fragment fragment, String str);
}
