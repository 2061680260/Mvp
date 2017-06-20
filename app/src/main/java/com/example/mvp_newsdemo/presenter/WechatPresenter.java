package com.example.mvp_newsdemo.presenter;

import android.content.Context;

import com.example.mvp_newsdemo.base.BasePresenter;
import com.example.mvp_newsdemo.bean.WeChatNewsBean;
import com.example.mvp_newsdemo.http.WeChatApis;
import com.example.mvp_newsdemo.view.BaseFragmentI;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/6.
 */

public class WechatPresenter extends BasePresenter implements WechatPresenterI{

    private BaseFragmentI baseFragmentI;
    private Context context;
    private int page=1;

    @Override
    public void getdata(WeChatApis weChatApis) {
        Observable<WeChatNewsBean> observable=weChatApis.getWeChatNews(WeChatApis.MYKEY,page++,"10");


        Subscription subscribe=observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<WeChatNewsBean>() {
                    @Override
                    public void call(WeChatNewsBean weChatNewsBean) {

                        //数据请求成功后展示数据并隐藏Dialog
                        ArrayList<WeChatNewsBean.NewslistBean> newslist = weChatNewsBean.getNewslist();
                        list.addAll(0,newslist);
                        baseFragmentI.showSuccessPage(list);
                        baseFragmentI.hideProcessDialog();
                    }
                });

        addSubscription(subscribe);
    }

    public void attachView(BaseFragmentI v,Context context){
        this.baseFragmentI=v;
        this.context=context;
    }
}
