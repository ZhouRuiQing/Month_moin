package com.bwie.month_moin.apdater;

import android.content.Context;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.month_moin.MainActivity;
import com.bwie.month_moin.OnResfreshLinter.OnResfreshLinter;
import com.bwie.month_moin.R;
import com.bwie.month_moin.bean.ShopBean;

import java.util.List;

public class ShopCartapdater extends RecyclerView.Adapter<MyViewHoder> {
    Context context;
    List<ShopBean.DataBean.ListBean> list;

    public ShopCartapdater(Context context, List<ShopBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHoder hoder = new MyViewHoder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, final int position) {
        Glide.with(context).load(list.get(position).getImages().split("\\|")[0]).into(holder.ivItemShopcartClothPic);
        holder.tvItemShopcartClothPrice.setText(list.get(position).getPrice()+"");
        holder.tvItemShopcartClothColor.setText(list.get(position).getTitle());
        holder.tvItemShopcartShopname.setText(list.get(position).getShopName());
        holder.tvItemShopcartClothname.setText(list.get(position).getSellerid()+"");
        holder.etItemShopcartClothNum.setText(list.get(position).getNum()+"");


        if(list.get(position).getSelected()==0){
            holder.ivItemShopcartShopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));

        }else {
            holder.ivItemShopcartShopselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }

        if(list.get(position).getSelected()==0){
            holder.tvItemShopcartClothselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_selected));

        }else{
            holder.tvItemShopcartClothselect.setImageDrawable(context.getResources().getDrawable(R.drawable.shopcart_unselected));
        }


        /*判断是否显示商铺*/

        if(position>0){
            if(list.get(position).getSellerid()==list.get(position-1).getSellerid()){

                holder.llShopcartHeader.setVisibility(View.GONE);
            }else{
                holder.llShopcartHeader.setVisibility(View.VISIBLE);
            }
        }else{
            holder.llShopcartHeader.setVisibility(View.VISIBLE);
        }

        //自定义加
        holder.ivItemShopcartClothAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setNum(list.get(position).getNum()+1);
                notifyDataSetChanged();
            }
        });

        //自定义减
        holder.ivItemShopcartClothMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list.get(position).getNum()>1){
                list.get(position).setNum(list.get(position).getNum()-1);
                notifyDataSetChanged();
                }
            }
        });

        //计算逻辑
        if(onResfreshLinter!=null){

            boolean isselect=false;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getSelected()==1){
                  isselect=false;
                  break;
                }else {
                    isselect=true;
                }
            }
            onResfreshLinter.onResfresh(isselect);
        }

        //单个商品选中状态
        holder.tvItemShopcartClothselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.get(position).setSelected(list.get(position).getSelected()==0 ? 1:0);
                //通过循环找出不同位置的商品
                for (int i = 0; i < list.size(); i++) {
                    if(list.get(i).isFirst()){
                        for (int j=0;j<list.size();j++){
                            //判断是不是同一家 若果不是勾选取消
                            if(list.get(j).getSellerid()==list.get(i).getSellerid()&&list.get(j).getSelected()==1){
                                list.get(i).setShopSelect(false);
                                break;
                            }else{
                                //如果是同一家商品 选中
                                list.get(i).setShopSelect(true);
                            }
                        }
                    }
                }
                notifyDataSetChanged();
            }
        });

        //商铺选中状态
        holder.ivItemShopcartShopselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(position).isFirst()){

                    //商铺选中状态
                    list.get(position).setShopSelect(!list.get(position).isShopSelect());

                    //改变商铺选中状态
                    for (int i = 0; i < list.size(); i++) {
                        if(list.get(i).getSellerid()==list.get(position).getSellerid()){

                            list.get(i).setSelected(list.get(position).isShopSelect()?0:1);
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    //回调的监听
    private OnResfreshLinter onResfreshLinter;

    public void setOnResfreshLinter(OnResfreshLinter onResfreshLinter){
        this.onResfreshLinter=onResfreshLinter;
    }

}
