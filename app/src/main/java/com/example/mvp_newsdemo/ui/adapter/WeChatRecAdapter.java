package com.example.mvp_newsdemo.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mvp_newsdemo.R;
import com.example.mvp_newsdemo.bean.WeChatNewsBean;
import com.example.mvp_newsdemo.ui.activity.DetailActivity;

import java.util.ArrayList;

/**
 * Created by root on 17-6-5.
 */

public class WeChatRecAdapter extends RecyclerView.Adapter<WeChatRecAdapter.MyViewHolder> {
    private Context context;
    private ArrayList list;
    private LayoutInflater inflater;

    public WeChatRecAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(inflater.inflate(R.layout.wechat_item,null,false));
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final WeChatNewsBean.NewslistBean bean = (WeChatNewsBean.NewslistBean) list.get(position);
        if (bean.getTitle()!=null){
            holder.title.setText(bean.getTitle());
        }
        if (bean.getDescription()!=null){

        holder.des.setText(bean.getDescription());
        }
        if (bean.getCtime()!=null){
        holder.time.setText(bean.getCtime());
        }
        if(bean.getPicUrl()!=null){
        Glide.with(context)
                .load(bean.getPicUrl())
                .into(holder.iv);
        }
        if (!TextUtils.isEmpty(bean.getUrl())){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("url",bean.getUrl());
                context.startActivity(intent);
            }
        });}
    }

    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }else{
            return 0;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public TextView title,des,time;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
            title=(TextView)itemView.findViewById(R.id.title);
            des=(TextView)itemView.findViewById(R.id.des);
            time=(TextView)itemView.findViewById(R.id.time);
        }

    }
}
