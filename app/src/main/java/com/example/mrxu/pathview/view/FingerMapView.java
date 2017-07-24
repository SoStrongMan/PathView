package com.example.mrxu.pathview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * 作者：xuxin
 * 日期：2017/7/6
 * 描述：用手指画图
 */

public class FingerMapView extends View {

    private float mX, mY; //初始的点坐标

    private Path mPath;
    private Paint mPaint;

    public FingerMapView(Context context) {
        this(context, null);
    }

    public FingerMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FingerMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
            case MotionEvent.ACTION_UP:
                Toast.makeText(getContext(), "哎呦不错哦", Toast.LENGTH_SHORT).show();
                break;
        }
        invalidate(); //通知重绘
        return true;
    }

    /**
     * 手指按下
     *
     * @param event 触摸事件
     */
    private void touchDown(MotionEvent event) {
        mPath.reset(); //清空之前画的内容
        mX = event.getX();
        mY = event.getY();
        mPath.moveTo(mX, mY); //绘制的起点
    }

    /**
     * 手指移动
     *
     * @param event 触摸事件
     */
    private void touchMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (Math.abs(x - mX) >= 3 || Math.abs(y - mY) >= 3) {
            //两个辅助控制点，为起点和终点的一半
            float controlX = (mX + x) / 2;
            float controlY = (mY + y) / 2;
            mPath.quadTo(controlX, controlY, x, y);
            mX = x;
            mY = y;
        }
    }
}
