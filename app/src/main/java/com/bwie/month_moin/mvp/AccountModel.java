package com.bwie.month_moin.mvp;

import com.bwie.month_moin.utils.OkHttpUtills;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class AccountModel implements AccountCenter.Model {

    private OkHttpClient client;

    public AccountModel(){

        client=new OkHttpClient();
    }

    @Override
    public void ShowData(String url, final MvpCallBack callBack) {

        OkHttpUtills.getInstance().get(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String msg = e.getMessage().toString();
                callBack.Error(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String estrign = response.body().string().toString();
                callBack.OnSuccess(estrign);
            }
        });
    }
}
