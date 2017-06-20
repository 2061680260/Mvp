package com.example.mvp_newsdemo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.mvp_newsdemo.R;
import com.example.mvp_newsdemo.base.BaseFragment;
import com.example.mvp_newsdemo.presenter.HomePresenter;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/5.
 */

public class HomeFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener,
        OnRefreshListener,OnLoadMoreListener{
    private Handler handler;
    private SwipeToLoadLayout swipeToLoadLayout;
    private HashMap<String, String> url_maps;
    private  SliderLayout sliderLayout;
    private HomePresenter hp;

    @Override
    protected View initview() {
        View v=inflater.inflate(R.layout.homefragment,null,false);
        handler=new Handler();


        swipeToLoadLayout= (SwipeToLoadLayout) v.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);

        sliderLayout= (SliderLayout) v.findViewById(R.id.mSlider);
        initslider();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProcessDialog();
            }
        },1000);

        return v;
    }


    private void initslider() {

        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(4000);

        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            sliderLayout.addSlider(textSliderView);
        }
    }

    @Override
    protected void initdata() {
      hp=new HomePresenter();
      url_maps=hp.getViewPageData();
    }


    @Override
    protected void showsuccesspage(Object bean) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }



    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
                sliderLayout.startAutoCycle();
            }
        },2000);

    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                sliderLayout.startAutoCycle();
            }
        },2000);

    }

    @Override
    public void onResume() {
        super.onResume();
        sliderLayout.startAutoCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        sliderLayout.stopAutoCycle();
    }
}
