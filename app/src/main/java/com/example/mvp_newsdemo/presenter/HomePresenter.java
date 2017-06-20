package com.example.mvp_newsdemo.presenter;

import com.example.mvp_newsdemo.base.BasePresenter;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/8.
 */

public class HomePresenter extends BasePresenter implements HomePresenterI {
    private HashMap<String,String> url_maps;
    @Override
    public HashMap<String, String> getViewPageData() {
        url_maps = new HashMap();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");
        return url_maps;
    }
}
