package com.bwie.month_moin.IBase;

public interface IBasePresent<V extends IBaseView>{

    void attch(V v);
    void deattch();
}
