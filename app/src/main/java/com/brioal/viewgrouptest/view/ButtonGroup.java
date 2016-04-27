package com.brioal.viewgrouptest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brioal on 16-4-24.
 */
public class ButtonGroup extends ViewGroup {
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    int mPaddingBottom;

    public ButtonGroup(Context context) {
        this(context, null);
    }

    public ButtonGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int currentHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int height = child.getMeasuredHeight();
            if (child.getVisibility() != GONE) {
                child.layout(l+mPaddingLeft, currentHeight+mPaddingTop, r-mPaddingRight, currentHeight + height+mPaddingBottom);
                currentHeight += height;
            }
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingRight = getPaddingRight();
        mPaddingBottom = getPaddingBottom();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int mWidth = 0;
        int mHeight = 0;
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) { //wrap_content 未指定宽高
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                mWidth += child.getMeasuredWidth();
                mHeight += child.getMeasuredHeight();
                //总宽高是所有子组件的宽高
            }
            setMeasuredDimension(mWidth + mPaddingLeft + mPaddingRight, mHeight + mPaddingTop + mPaddingBottom);
        } else if (widthMode == MeasureSpec.AT_MOST) { //宽度未指定 ,取最大的一个宽度
            for (int i = 0; i < childCount; i++) {
                mWidth = Math.max(mWidth, getChildAt(i).getMeasuredWidth());
            }
            setMeasuredDimension(mWidth + mPaddingLeft + mPaddingRight, heightSize + mPaddingTop + mPaddingBottom);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            for (int i = 0; i < childCount; i++) {
                mHeight += getChildAt(i).getMeasuredHeight();
            }
            setMeasuredDimension(widthSize + mPaddingLeft + mPaddingRight, mHeight + mPaddingTop + mPaddingBottom);
        }

    }


}
