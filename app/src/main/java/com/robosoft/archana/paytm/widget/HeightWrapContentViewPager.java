package com.robosoft.archana.paytm.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by archana on 7/8/16.
 */
public class HeightWrapContentViewPager extends ViewPager {

    public HeightWrapContentViewPager(Context context) {
        super(context);
    }

    public HeightWrapContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child = (View) this.findViewWithTag(this.getCurrentItem());//linearlayout i.e child of viewPager
        if (child != null) {
            child.measure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int height = child.getMeasuredHeight();
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void reMeasureCurrentPage() {
        if(!isInLayout()){
            //this.requestLayout();
            super.requestLayout();
        }

    }

}

