package com.example.mvp_newsdemo.ui.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mvp_newsdemo.R;
import com.example.mvp_newsdemo.Utils.RetrofitUtils;
import com.example.mvp_newsdemo.base.BaseFragment;
import com.example.mvp_newsdemo.bean.WeChatNewsBean;
import com.example.mvp_newsdemo.presenter.WechatPresenter;
import com.example.mvp_newsdemo.ui.adapter.WeChatRecAdapter;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/5.
 */

public class WechatFragment extends BaseFragment {
    private WechatPresenter wechatPresenter;
    public SwipeRefreshLayout srl;
    private WeChatRecAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected View initview() {
        View v=inflater.inflate(R.layout.wechatfragment,null,false);
        srl= (SwipeRefreshLayout) v.findViewById(R.id.srl);
        recyclerView= (RecyclerView) v.findViewById(R.id.wechat_rc);

        setPresenter(wechatPresenter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        HorizontalDividerItemDecoration divider = new HorizontalDividerItemDecoration.Builder(getActivity())
                .color(Color.GRAY)
                .size(5)
                .margin(10, 10)
                .build();
        recyclerView.addItemDecoration(divider);
        wechatPresenter.attachView(this,getActivity());

        srl.setColorSchemeColors(
                getActivity().getResources().getColor(R.color.gray),
                getActivity().getResources().getColor(R.color.orange),
                getActivity().getResources().getColor(R.color.purple),
                getActivity().getResources().getColor(R.color.pink)
                ,getActivity().getResources().getColor(R.color.pink)
                ,getActivity().getResources().getColor(R.color.pink)
                ,getActivity().getResources().getColor(R.color.pink));
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(1000);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                wechatPresenter.getdata(RetrofitUtils.getInstance().getWeChatApis());
                            }
                        });

                    }
                }).start();
            }
        });

        return v;
    }

    @Override
    protected void initdata() {
        wechatPresenter=new WechatPresenter();
        wechatPresenter.getdata(RetrofitUtils.getInstance().getWeChatApis());
    }


    @Override
    protected void showsuccesspage(Object bean) {
        adapter=new WeChatRecAdapter(getActivity(), (ArrayList<WeChatNewsBean.NewslistBean>) bean);
        recyclerView.setAdapter(adapter);

        srl.setRefreshing(false);
    }




}
