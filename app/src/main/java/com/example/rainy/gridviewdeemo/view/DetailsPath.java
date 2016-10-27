package com.example.rainy.gridviewdeemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/9/20 0020 上午 10:04 <br>
 */

public class DetailsPath extends View {
    private Paint mDeafultPaint;//画笔
    private int mViewWidth,mViewHeight;
    public DetailsPath(Context context) {
        super(context);
    }

    public DetailsPath(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 非零环绕规则与反非零环绕规则
         * 大正方形里面叠放小正方形
         * 内外同向：填充整个大正方形
         * 内外反向：填充小正方形以外的大正方形部分
         *
         */


        mDeafultPaint = new Paint();// 设置画笔模式为填充
        mDeafultPaint.setColor(Color.BLACK);
        mDeafultPaint.setStyle(Paint.Style.FILL);

        /* canvas.translate(mViewWidth / 2, mViewHeight / 2);          // 移动画布(坐系)
        Path path = new Path();                                     // 创建Path

        // 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
         path.addRect(-200, -200, 200, 200, Path.Direction.CW);//顺时针
//        path.addRect(-200, -200, 200, 200, Path.Direction.CCW);//逆时针

        // 添加大正方形
        path.addRect(-400, -400, 400, 400, Path.Direction.CCW);

        path.setFillType(Path.FillType.WINDING);                    // 设置Path填充模式为非零环绕规则
//        path.setFillType(Path.FillType.INVERSE_WINDING);                    // 设置Path填充模式为反非零环绕规则

        canvas.drawPath(path, mDeafultPaint);                       // 绘制Path*/



        /**
         * 奇偶规则与反奇偶规则
         * 内外同向和内外反向填充结果一样
         * 都是填充的小正方形以外的大正方形部分
         *
         * 一个正方形的时候：假设顺时针
         * 奇偶规则：填充的图形内部
         * 反奇偶规则：填充图形以外的部分
         *这里有疑问：验证结果相同？
         */

        canvas.translate(mViewWidth / 2, mViewHeight / 2);          // 移动画布(坐标系)

        Path path = new Path();                                     // 创建Path

//        path.setFillType(Path.FillType.EVEN_ODD);                   // 设置Path填充模式为 奇偶规则
         path.setFillType(Path.FillType.INVERSE_EVEN_ODD);            // 反奇偶规则

        path.addRect(-200,-200,200,200, Path.Direction.CCW);         // 给Path中添加一个矩形

        canvas.drawPath(path, mDeafultPaint);                       // 绘制Path


    }
}
