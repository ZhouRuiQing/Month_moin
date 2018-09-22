package com.bwie.month_moin.mvp;

import com.bwie.month_moin.IBase.IBaseModel;
import com.bwie.month_moin.IBase.IBasePresent;
import com.bwie.month_moin.IBase.IBaseView;

/**
 *  契约类
 */
public interface AccountCenter {


    interface View extends IBaseView {

        void Showdata(String reluct);
        void hideing();
        void Error();
    }

    interface Model extends IBaseModel{

        interface MvpCallBack{

            void OnSuccess(String reluet);
            void Error(String msg);
        }

        void ShowData(String url,MvpCallBack callBack);
    }

    interface Present extends IBasePresent<View>{

        void ShwoData(String url);
    }
}
