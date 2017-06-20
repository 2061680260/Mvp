package com.example.mvp_newsdemo.ui.activity;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mvp_newsdemo.R;
import com.example.mvp_newsdemo.base.BaseActivity;
import com.example.mvp_newsdemo.ui.fragment.GankFragment;
import com.example.mvp_newsdemo.ui.fragment.HomeFragment;
import com.example.mvp_newsdemo.ui.fragment.WechatFragment;
import com.example.mvp_newsdemo.ui.fragment.WyFragment;
import com.example.mvp_newsdemo.view.MainInterface;

public class MainActivity extends BaseActivity implements MainInterface{

    private RadioGroup mRg;
    private android.support.v4.app.FragmentManager fm;
    private HomeFragment homeFragment;
    private GankFragment gankFragment;
    private WechatFragment wechatFragment;
    private WyFragment wyFragment;
    private RadioButton mRb_home,mRb_wechat,mRb_gank,mRb_wy;

    private String[] strs={"home","wechat","news","gank"};
    @Override
    public void initview() {
        setContentView(R.layout.activity_main);
        mRb_gank= (RadioButton) findViewById(R.id.mRb_gank);
        mRb_home= (RadioButton) findViewById(R.id.mRb_home);
        mRb_wechat= (RadioButton) findViewById(R.id.mRb_wechat);
        mRb_wy= (RadioButton) findViewById(R.id.mRb_news);

        mRg= (RadioGroup) findViewById(R.id.mRg);

        if(fm==null){
            fm=getSupportFragmentManager();
            if(homeFragment==null){
                homeFragment=new HomeFragment();
            }
        }
        showFragment(fm,homeFragment,"home");

        swichFragment();

    }

    @Override
    public void initData() {

    }

    @Override
    public void swichFragment() {
        fm=getSupportFragmentManager();
        wechatFragment=new WechatFragment();
        wyFragment=new WyFragment();
        gankFragment=new GankFragment();

        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
              clear();
              switch(checkedId){
                  case R.id.mRb_gank:
                      showFragment(fm,gankFragment,"gank");
                      mRb_gank.setTextColor(Color.RED);
                      break;
                  case R.id.mRb_home:
                      showFragment(fm,homeFragment,"home");
                      mRb_home.setTextColor(Color.RED);
                      break;
                  case R.id.mRb_wechat:
                      showFragment(fm,wechatFragment,"wechat");
                      mRb_wechat.setTextColor(Color.RED);
                      break;
                  case R.id.mRb_news:
                      showFragment(fm,wyFragment,"news");
                      mRb_wy.setTextColor(Color.RED);
                      break;
              }
          }
      });
    }

    @Override
    public void showFragment(android.support.v4.app.FragmentManager fm, Fragment fragment, String str) {
        if(fm==null){
            fm=getSupportFragmentManager();
        }
        FragmentTransaction ft=fm.beginTransaction();
        for(String s:strs){
            if(!s.equals(str)){
                Fragment fragmentTemp = fm.findFragmentByTag(s);
                if (fragmentTemp!=null){
                    ft.hide(fragmentTemp);
                }
            }
        }

        if (fm.findFragmentByTag(str)==null){
            ft.add(R.id.mContains, fragment, str);
        }
        ft.show(fragment);
        ft.commit();

    }

   public void clear(){
       mRb_gank.setTextColor(Color.BLACK);
       mRb_wy.setTextColor(Color.BLACK);
       mRb_home.setTextColor(Color.BLACK);
       mRb_wechat.setTextColor(Color.BLACK);
   }
}
