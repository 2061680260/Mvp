package com.example.mvp_newsdemo.Utils;

import com.example.mvp_newsdemo.http.WeChatApis;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/6.
 */

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;
    private WeChatApis weChatApis;

    private RetrofitUtils(){
        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(WeChatApis.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        weChatApis=retrofit.create(WeChatApis.class);
    }

    public static RetrofitUtils getInstance(){
        if(retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }

        return retrofitUtils;
    }

    public WeChatApis getWeChatApis(){
        return weChatApis;
    }
}
