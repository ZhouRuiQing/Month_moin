package com.bwie.month_moin.utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtills {

    public OkHttpClient client;
    private static OkHttpUtills okHttpUtills = new OkHttpUtills();

    public static OkHttpUtills getInstance() {
        if(okHttpUtills==null){

            synchronized (OkHttpUtills.class){

                    okHttpUtills=new OkHttpUtills();
            }
        }
        return okHttpUtills;
    }

    private OkHttpUtills() {
        if(client==null){

            synchronized (OkHttpClient.class){

                client=new OkHttpClient.Builder().build();
            }
        }
    }

    public void  get(String url, Callback callback){

        Request request = new Request.Builder().url(url).build();

         client.newCall(request).enqueue(callback);
    }
}
