package com.example.mrxu.pathview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：xuxin
 * 日期：2017/7/10
 * 描述：饼状图
 */

public class PieMapView extends View {

    private Paint mPaint;

    private int mWeight, mHeight; //屏幕的宽、高
    private float[] values; //每个扇形的数值集合
    private int[] colors; //每个扇形的颜色集合
    private float[] angles; //每个扇形的起始角度集合
    private RectF mRect;
    private float mCurStartAngle; //每个扇形的起始角度

    public PieMapView(Context context) {
        this(context, null);
    }

    public PieMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    /**
     * 设置参数
     *
     * @param values 每个扇形的数值集合
     * @param colors 每个扇形的颜色集合（其中颜色的int值必须加上ff表示不透明，例如：0xff000000表示黑色）
     */
    public void setValues(float[] values, int[] colors) {
        this.values = values;
        this.colors = colors;
        int sumAngle = 0; //起始角度
        angles = new float[values.length];
        for (int i = 0; i < values.length; i++) {
            //得到全部数值
            sumAngle += values[i];
        }
        for (int i = 0; i < values.length; i++) {
            //得到每个数值对应的角度
            angles[i] = values[i] / sumAngle * 360;
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWeight = w;
        this.mHeight = h;
        float r = (float) (Math.min(w, h) / 2 * 0.8); //饼状图的半径
        mRect = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (values == null || values.length == 0 || colors == null || colors.length == 0) {
            return;
        }
        canvas.translate(mWeight / 2, mHeight / 2); //将画布的坐标原点移到屏幕中心点

        for (int i = 0; i < values.length; i++) {
            mPaint.setColor(colors[i]);
            canvas.drawArc(mRect, mCurStartAngle, angles[i], true, mPaint);
            mCurStartAngle += angles[i];
        }
    }
}
