package com.example.mvp_newsdemo.view;

import rx.Subscription;

/**
 * Created by Administrator on 2017/6/7.
 */

public interface BasePresenterI {

    void addSubscription(Subscription subscription);
    void unsubcrible();
}
