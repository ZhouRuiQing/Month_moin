package com.bwie.month_moin.apdater;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.month_moin.R;

public class MyViewHoder extends RecyclerView.ViewHolder {

    public LinearLayout llShopcartHeader;
    public View view;
    public ImageView ivItemShopcartShopselect;
    public TextView tvItemShopcartShopname;
    public TextView tvItemShopcartClothname;
    public ImageView tvItemShopcartClothselect;
    public ImageView ivItemShopcartClothPic;
    public TextView tvItemShopcartClothPrice;
    public TextView tvItemShopcartClothColor;
    public TextView tvItemShopcartClothSize;
    public ImageView ivItemShopcartClothMinus;
    public TextView etItemShopcartClothNum;
    public ImageView ivItemShopcartClothAdd;
;


    public MyViewHoder(View itemView) {
        super(itemView);


        llShopcartHeader =  itemView.findViewById(R.id.ll_shopcart_header);
        view =  itemView.findViewById(R.id.view);
        ivItemShopcartShopselect =  itemView.findViewById(R.id.iv_item_shopcart_shopselect);
        tvItemShopcartShopname   =  itemView.findViewById(R.id.tv_item_shopcart_shopname);
        tvItemShopcartClothname  =  itemView.findViewById(R.id.tv_item_shopcart_clothname);
        tvItemShopcartClothselect= itemView.findViewById(R.id.tv_item_shopcart_clothselect);
        ivItemShopcartClothPic   =  itemView.findViewById(R.id.iv_item_shopcart_cloth_pic);
        tvItemShopcartClothPrice = itemView.findViewById(R.id.tv_item_shopcart_cloth_price);
        tvItemShopcartClothColor =  itemView.findViewById(R.id.tv_item_shopcart_cloth_color);
        tvItemShopcartClothSize  =  itemView.findViewById(R.id.tv_item_shopcart_cloth_size);
        ivItemShopcartClothMinus =  itemView.findViewById(R.id.iv_item_shopcart_cloth_minus);
        etItemShopcartClothNum   =  itemView.findViewById(R.id.et_item_shopcart_cloth_num);
        ivItemShopcartClothAdd   =  itemView.findViewById(R.id.iv_item_shopcart_cloth_add);


    }
}
