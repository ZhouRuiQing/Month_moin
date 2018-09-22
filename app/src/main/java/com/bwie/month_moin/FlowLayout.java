package com.bwie.month_moin;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private int mleftMargin=20;
    private int mtopMargin=20;



    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int leftMargin = mleftMargin;
        int topMargin = mtopMargin;

        //父控件传进来的宽度和高度以及对应的测量模式
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        switch (modeHeight){
            case MeasureSpec.AT_MOST:
                int measuredHeight = 0;
                for (int j = 0; j < getChildCount(); j++) {
                    int measuredWidth = getChildAt(j).getMeasuredWidth();
                    measuredHeight = getChildAt(j).getMeasuredHeight();
                    if (leftMargin+measuredWidth+mleftMargin>getWidth()){
                        leftMargin=mleftMargin;
                        topMargin+=measuredHeight+mtopMargin;
                    }
                    leftMargin+=measuredWidth+mleftMargin;
                }
                topMargin+=measuredHeight+mtopMargin;
                break;
        }
        setMeasuredDimension(sizeWidth,topMargin);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int leftMargin = mleftMargin;
        int topMargin = mtopMargin;

        for (int j = 0; j < getChildCount(); j++) {
            int measuredWidth = getChildAt(j).getMeasuredWidth();
            int measuredHeight = getChildAt(j).getMeasuredHeight();
            if (leftMargin+measuredWidth+mleftMargin>getWidth()){
                leftMargin=mleftMargin;
                topMargin+=measuredHeight+mtopMargin;
                getChildAt(j).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }else {
                getChildAt(j).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }
            leftMargin+=measuredWidth+mleftMargin;
        }
    }

}
