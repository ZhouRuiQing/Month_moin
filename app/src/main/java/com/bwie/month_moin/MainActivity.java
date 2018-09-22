package com.bwie.month_moin;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.month_moin.OnResfreshLinter.OnResfreshLinter;
import com.bwie.month_moin.apdater.ShopCartapdater;
import com.bwie.month_moin.bean.ShopBean;
import com.bwie.month_moin.mvp.AccountCenter;
import com.bwie.month_moin.mvp.AccountPresent;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AccountCenter.View {

    private RelativeLayout rlShopcartHave;
    private RecyclerView recylerView;
    private LinearLayout llPay;
    private TextView tvShopcartAddselect;
    private TextView tvShopcartTotalprice;
    private TextView tvShopcartTotalnum;
    private TextView tvShopcartSubmit;

    private AccountPresent present;
    private List<ShopBean.DataBean.ListBean> list = new ArrayList<>();
    private List<ShopBean.DataBean.ListBean> mlist = new ArrayList<>();
    private ShopCartapdater apdater;
    private boolean misSelect;
    private float mtotalprice1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         init();
         recylerView=findViewById(R.id.recylerView);
         recylerView.setLayoutManager(new LinearLayoutManager(this));

         apdater = new ShopCartapdater(this,list);
         recylerView.setAdapter(apdater);
        present=new AccountPresent();
        present.attch(this);
        present.ShwoData("https://www.zhaoapi.cn/product/getCarts?uid=71");


        //实时监控全选按钮
        apdater.setOnResfreshLinter(new OnResfreshLinter() {
            @Override
            public void onResfresh(boolean isSelect) {
                misSelect=isSelect;
                if(isSelect){
                    Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                    tvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                }else{

                    Drawable letf = getResources().getDrawable(R.drawable.shopcart_unselected);
                    tvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(letf,null,null,null);
                }
                //计算总价
                float mtotalprice=0;
                int mtotalnum=0;
                mtotalprice1=0;
                mlist.clear();
                for (int i = 1; i < list.size(); i++) {
                    if(list.get(i).getSelected()==0){

                        mtotalprice+=list.get(i).getPrice()*list.get(i).getNum();
                        mtotalnum+=1;
                        mlist.add(list.get(i));

                    }
                    mtotalprice1=mtotalprice;
                    tvShopcartTotalprice.setText("总价:"+mtotalprice);
                    tvShopcartTotalnum.setText("共"+mtotalnum+"件商品");
                }
            }
        });
        tvShopcartAddselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                misSelect=!misSelect;
                if(misSelect){
                    //全部选中

                    Drawable left = getResources().getDrawable(R.drawable.shopcart_selected);
                    tvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(left,null,null,null);
                    for (ShopBean.DataBean.ListBean listBean : list) {
                        listBean.setSelected(0);
                        listBean.setShopSelect(true);
                    }

                }else{

                    Drawable letf = getResources().getDrawable(R.drawable.shopcart_unselected);
                    tvShopcartAddselect.setCompoundDrawablesWithIntrinsicBounds(letf,null,null,null);
                    for (ShopBean.DataBean.ListBean listBean : list) {
                        listBean.setSelected(1);
                        listBean.setShopSelect(false);
                    }
                }
                apdater.notifyDataSetChanged();
            }
        });
    }

    private void init() {

        rlShopcartHave =  findViewById(R.id.rl_shopcart_have);
        llPay =  findViewById(R.id.ll_pay);
        tvShopcartAddselect =  findViewById(R.id.tv_shopcart_addselect);
        tvShopcartTotalprice =  findViewById(R.id.tv_shopcart_totalprice);
        tvShopcartTotalnum =  findViewById(R.id.tv_shopcart_totalnum);
        tvShopcartSubmit = findViewById(R.id.tv_shopcart_submit);
    }

    @Override
    public void Showdata(String reluct) {
        Log.i("aaa",reluct);

        Gson gson = new Gson();
        ShopBean shopBean = gson.fromJson(reluct, ShopBean.class);

        for (ShopBean.DataBean dataBean : shopBean.getData()) {

            for (ShopBean.DataBean.ListBean listBean : dataBean.getList()) {
                listBean.setShopName(dataBean.getSellerName());
                list.add(listBean);
            }
        }
        isSelectFirst(list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                apdater.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void hideing() {

    }

    @Override
    public void Error() {

    }
    public static void isSelectFirst(List<ShopBean.DataBean.ListBean> list){

        if(list.size()>0){
            list.get(0).setFirst(true);
            for (int i =1;i<list.size();i++){

                if(list.get(i).getSelected()==list.get(i-1).getSelected()){
                    list.get(i).setFirst(false);
                }else{
                    list.get(i).setFirst(true);
                }
            }
        }
    }
}
