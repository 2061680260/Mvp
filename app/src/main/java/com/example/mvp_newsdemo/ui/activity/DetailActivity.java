package com.example.mvp_newsdemo.ui.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.mvp_newsdemo.R;
import com.example.mvp_newsdemo.base.BaseActivity;

public class DetailActivity extends BaseActivity {

    private WebView webView;
    private String url;
    private static final String TAG = "DetailActivity";
    @Override
    public void initview() {
        setContentView(R.layout.activity_detail);
        webView= (WebView) findViewById(R.id.nWb);

        ActionBar actionBar=getActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        webView.loadUrl(url);

        //与js交互
        WebSettings webSettings = webView.getSettings();

        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

    }

    @Override
    public void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        Log.e(TAG, url );
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return super.onNavigateUp();
    }
}
