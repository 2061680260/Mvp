package com.example.mvp_newsdemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvp_newsdemo.Utils.NetWorkUtils;
import com.example.mvp_newsdemo.Utils.ToastUtils;
import com.example.mvp_newsdemo.customview.WYDialog;
import com.example.mvp_newsdemo.view.BaseFragmentI;
import com.example.mvp_newsdemo.view.BasePresenterI;

/**
 * Created by Administrator on 2017/6/5.
 */

public abstract class BaseFragment extends Fragment implements BaseFragmentI{
    public LayoutInflater inflater;
    private BasePresenterI basePresenterI;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater=inflater;
        initdata();
        showProcessDialog();
        return initview();
    }

    //此方法是为了取消关联(unsubcrible),得到一个具体的实现类的实例
    public void setPresenter(BasePresenterI basePresenterI){
        this.basePresenterI=basePresenterI;
    }

    @Override
    public void onResume() {
        super.onResume();
        showErrorNetWorkInfo(getActivity());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        basePresenterI.unsubcrible();
    }

    protected abstract View initview();

    protected abstract void initdata();


    protected abstract void showsuccesspage(Object bean);

    @Override
    public void showSuccessPage(Object bean) {
            showsuccesspage(bean);
    }

    @Override
    public void showProcessDialog() {
        WYDialog.showProcessDialog(getActivity());
    }

    @Override
    public void hideProcessDialog() {
        WYDialog.hideProgressDialog();
    }

    @Override
    public void showErrorNetWorkInfo(Activity context) {
         if(!NetWorkUtils.isNetworkAvailable(context)){
             ToastUtils.show(context,"你现在处于没有网的二次元，请检查网络");
         }
    }

}
