package com.bwie.month_moin.mvp;

import java.lang.ref.WeakReference;

public class AccountPresent implements AccountCenter.Present {

    private AccountCenter.Model model;
    private AccountCenter.View view;
    private WeakReference<AccountCenter.View> viewWeakReference;
    private WeakReference<AccountModel> modelWeakReference;

    @Override
    public void ShwoData(String url) {

        modelWeakReference.get().ShowData(url, new AccountCenter.Model.MvpCallBack() {
            @Override
            public void OnSuccess(String reluet) {
                view.Showdata(reluet);
            }

            @Override
            public void Error(String msg) {
            }
        });
    }


    @Override
    public void attch(AccountCenter.View view) {

        this.view=view;
        viewWeakReference = new WeakReference(view);
        model=new AccountModel();
        modelWeakReference=new WeakReference(model);
    }

    @Override
    public void deattch() {

    }
}
