package com.example.mrxu.pathview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：xuxin
 * 日期：2017/7/12
 * 描述：画太极
 */

public class TaiChiView extends View {

    private Paint mWhitePaint;
    private Paint mBlackPaint;

    private int mWeight, mHeight;
    private int r; //大圆半径
    private RectF rect;
    private float mAngle;

    public TaiChiView(Context context) {
        this(context, null);
    }

    public TaiChiView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiChiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        mWhitePaint = new Paint();
        mWhitePaint.setAntiAlias(true);
        mWhitePaint.setColor(Color.WHITE);
        mBlackPaint = new Paint(mWhitePaint); //沿用mWhitePaint的设置
        mBlackPaint.setColor(Color.BLACK);
    }

    /**
     * 开始旋转
     *
     * @param angle 旋转角度
     */
    public void startRotate(float angle) {
        this.mAngle = angle;
        invalidate();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWeight = w;
        this.mHeight = h;
        r = Math.min(w, h) / 2 - 100;
        rect = new RectF(-r, -r, r, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWeight / 2, mHeight / 2);
        canvas.drawColor(Color.CYAN); //画布的背景色
        canvas.rotate(mAngle);

        //从90度角度开始，顺时针旋转180度（零点为三点钟方向，顺时针为增加角度）
        canvas.drawArc(rect, 90, 180, true, mBlackPaint);
        canvas.drawArc(rect, -90, 180, true, mWhitePaint);
        //绘制两个小圆（第一个和第二个参数分别是圆心的x、y坐标）
        canvas.drawCircle(0, -r / 2, r / 2, mBlackPaint);
        canvas.drawCircle(0, r / 2, r / 2, mWhitePaint);
        //绘制两个更小的圆
        canvas.drawCircle(0, -r / 2, r / 8, mWhitePaint);
        canvas.drawCircle(0, r / 2, r / 8, mBlackPaint);
    }
}
