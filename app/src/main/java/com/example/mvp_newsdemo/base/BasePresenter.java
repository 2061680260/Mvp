package com.example.mvp_newsdemo.base;

import android.content.Context;

import com.example.mvp_newsdemo.bean.WeChatNewsBean;
import com.example.mvp_newsdemo.view.BasePresenterI;

import java.util.ArrayList;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2017/6/6.
 */

public class BasePresenter implements BasePresenterI{

    public Context context;
    private CompositeSubscription mCompositeSubscription;
    public ArrayList<WeChatNewsBean.NewslistBean> list=new ArrayList();


    /**
     * 事件订阅
     * */
    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }



    public void unsubcrible() {
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
        list=null;
        context=null;
        this.mCompositeSubscription=null;
    }


}
