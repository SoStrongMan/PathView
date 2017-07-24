package com.example.mrxu.pathview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 作者：xuxin
 * 日期：2017/7/3
 * 描述：属性图
 */

public class AttributeMapView extends View {

    private static final int UNIT_LENGTH = 100; //每条边的单位长度
    private String[] text; //网格外的文字
    private Path mPath;
    private Paint mNetPaint; //画网格线的画笔
    private Paint mPointPaint; //画顶点的画笔
    private Paint mClosePaint; //画闭合区域的画笔
    private int[] values;

    private float mWidth, mHeight;

    public AttributeMapView(Context context) {
        this(context, null);
    }

    public AttributeMapView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AttributeMapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();

        mNetPaint = new Paint();
        mNetPaint.setAntiAlias(true); //抗锯齿
        mNetPaint.setColor(Color.BLUE); //画笔颜色

        mPointPaint = new Paint();
        mPointPaint.setColor(Color.RED);
        mPointPaint.setStrokeWidth(10);

        mClosePaint = new Paint();
        mClosePaint.setColor(0x7f1aaf03); //50%的透明度的绿色
        mClosePaint.setAntiAlias(true);

        text = new String[]{"攻击", "防御", "生存", "逃跑", "团战", "毒瘤"};
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2); //移动画布到屏幕中心
//        canvas.scale(1, -1); //坐标以X轴翻转
        canvas.save(); //保存当前状态

        //总过有4个网格，只是边的长度不同
        for (int i = 1; i < 5; i++) {
            if (i == 4) { //给网格外围加上文字
                int width = i * UNIT_LENGTH / 2;
                int height = (int) (i * UNIT_LENGTH * Math.cos(Math.toRadians(30)));
                mNetPaint.setTextSize(30); //设置画笔文字的大小
                canvas.drawText(text[0], width - 20, -height - 20, mNetPaint);
                canvas.drawText(text[1], -width - 20, -height - 20, mNetPaint);
                canvas.drawText(text[2], -i * UNIT_LENGTH - 60, 0, mNetPaint);
                canvas.drawText(text[3], -width - 40, height + 40, mNetPaint);
                canvas.drawText(text[4], width - 20, height + 40, mNetPaint);
                canvas.drawText(text[5], i * UNIT_LENGTH, 0, mNetPaint);
            }
            drawNet(canvas, i * UNIT_LENGTH);
        }

        drawPoints(canvas, values);
    }

    /**
     * 绘制每层网格的六条边和中间的斜线
     *
     * @param canvas 画布
     * @param length 边的长度
     */
    private void drawNet(Canvas canvas, int length) {
        //通过边的长度得到该点的高度
        int height = (int) (length * Math.cos(Math.toRadians(30)));
        for (int i = 0; i < 6; i++) {
            //绘制网格的边
            canvas.drawLine(-length / 2, height, length / 2, height, mNetPaint);
            //绘制网格中的斜线
            canvas.drawLine(-length / 2, height, length / 2, -height, mNetPaint);
            //旋转画布60度
            canvas.rotate(60, 0, 0);
        }
    }

    /**
     * 设置点的参数（不能超过400，因为网格最大长度是400）
     *
     * @param values 参数
     */
    public void setValues(int[] values) {
        this.values = values;
        invalidate(); //重新绘制
    }

    /**
     * 绘制所有坐标顶点
     *
     * @param canvas 画布
     * @param values 参数
     */
    private void drawPoints(Canvas canvas, int[] values) {
        Point[] points = new Point[values.length];
        for (int i = 0; i < values.length; i++) {
            Point point = getRotatePoint(i, values[i]);
            points[i] = point;
            canvas.drawPoint(point.x, point.y, mPointPaint);
        }
        pointLine(canvas, points);
    }

    /**
     * 根据值计算旋转后的坐标顶点
     *
     * @param index 第几条边,起始边为1点钟方向那条边
     * @param value 待计算值
     * @return 顶点
     */
    private Point getRotatePoint(int index, int value) {
        //在五个角度的边上
        int degrees = index * 60 + 30;
        double radians = Math.toRadians(degrees);
        double sin = Math.sin(radians);
        double cos = Math.cos(radians);
        int x = (int) (sin * value);
        int y = (int) (cos * value);
        Log.e("tag", "x:" + x + ",y:" + y);
        Point point = new Point();
        point.set(x, y);
        return point;
    }

    /**
     * 连接各个顶点，形成闭合的图形
     *
     * @param canvas 画布
     * @param points 所有的顶点
     */
    private void pointLine(Canvas canvas, Point[] points) {
        mPath.moveTo(points[0].x, points[0].y); //第一个点
        for (int i = 1; i < points.length; i++) {
            mPath.lineTo(points[i].x, points[i].y);
        }
        canvas.drawPath(mPath, mClosePaint);
    }
}
