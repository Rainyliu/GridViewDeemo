package com.example.rainy.gridviewdeemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

/**
 * Author: Rainy <br>
 * Description: GridViewDeemo <br>
 * Since: 2016/9/22 0022 上午 9:54 <br>
 */

public class SearchView extends View {
    private Paint mPaint;//画笔
    private int mViewWidth,mViewHeight;//view的宽高

    //这个视图拥有的状态
    private static enum State{
        NONE,
        STARTING,
        SEARCHING,
        ENDING
    }

    //当前的状态（非常重要）
    private State mCurrentState = State.NONE;

    //放大镜与外部圆环
    private Path path_search;
    private Path path_circle;

    //测量Path 并截取部分的工具
    private PathMeasure mMeasure;

    //默认的动效周期 2s
    private int defaultDuration = 2000;

    //控制各个过程的动画
    private ValueAnimator mStartingAnimator;
    private ValueAnimator mSearchingAnimator;
    private ValueAnimator mEndingAnimator;

    //动画数值（用于控制动画状态，因为同一时间内只允许一种状态出现，具体数值处理取决于当前状态）
    private float mAnimatorValue = 0;

    //动效过程监听器
    private ValueAnimator.AnimatorUpdateListener mUpdateListener;
    private Animator.AnimatorListener mAnimatorListener;

    //用于控制动画状态转换
    private Handler mAnimatorHandler;

    //判断是否已经搜索结束
    private boolean isOver = false;

    private int count = 0;

    public SearchView(Context context) {
        super(context);

        initPaint();
        initPath();
        initListener();
        initHandler();

        initAnimator();

        // 进入开始动画
        mCurrentState = State.STARTING;
        mStartingAnimator.start();
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

        drawSearch(canvas);
    }

    /**
     * 画形状
     * @param canvas
     */
    private void drawSearch(Canvas canvas) {
        mPaint.setColor(Color.WHITE);

        canvas.translate(mViewWidth/2,mViewHeight/2);
        canvas.drawColor(Color.parseColor("#0082D7"));

        switch (mCurrentState){
            case NONE://无
                canvas.drawPath(path_search,mPaint);
                break;
            case STARTING://开始
                mMeasure.setPath(path_search,false);
                Path dst = new Path();
                mMeasure.getSegment(mMeasure.getLength()*mAnimatorValue,mMeasure.getLength(),dst,true);
                canvas.drawPath(dst,mPaint);
                break;
            case SEARCHING://搜索
                mMeasure.setPath(path_circle,false);
                Path dst2 = new Path();
                float stop = mMeasure.getLength()*mAnimatorValue;
                float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
                mMeasure.getSegment(start,stop,dst2,true);
                canvas.drawPath(dst2,mPaint);
                break;
            case ENDING://结束
                mMeasure.setPath(path_search,false);
                Path dst3 = new Path();
                mMeasure.getSegment(mMeasure.getLength()*mAnimatorValue,mMeasure.getLength(),dst3,true);
                canvas.drawPath(dst3,mPaint);
                break;
        }
    }

    /**
     * 初始化动画
     */
    private void initAnimator() {
        mStartingAnimator = ValueAnimator.ofFloat(0,1).setDuration(defaultDuration);
        mSearchingAnimator = ValueAnimator.ofFloat(0,1).setDuration(defaultDuration);
        mEndingAnimator = ValueAnimator.ofFloat(1,0).setDuration(defaultDuration);

        mStartingAnimator.addUpdateListener(mUpdateListener);
        mSearchingAnimator.addUpdateListener(mUpdateListener);
        mEndingAnimator.addUpdateListener(mUpdateListener);

        mStartingAnimator.addListener(mAnimatorListener);
        mSearchingAnimator.addListener(mAnimatorListener);
        mEndingAnimator.addListener(mAnimatorListener);
    }

    /**
     * 初始化handler
     */
    private void initHandler() {
        mAnimatorHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (mCurrentState){
                    case STARTING://开始状态
                        //从开始动画转换好搜索动画
                        isOver = false;
                        mCurrentState = State.STARTING;
                        mStartingAnimator.removeAllListeners();
                        mSearchingAnimator.start();
                        break;
                    case SEARCHING://搜索状态
                        if(!isOver){//如果搜索未结束，继续执行搜索动画
                            mSearchingAnimator.start();
                            Log.e("mtag", "----------------RESTART");
                            count++;
                            if(count > 2){//count大于2 则进入结束状态
                                isOver = true;

                            }else {//如果搜索已经结束，则进入结束动画
                                mCurrentState = State.ENDING;
                                mEndingAnimator.start();

                            }
                        }
                        break;
                    case ENDING://结束状态
                        //从结束动画转变为无状态
                        mCurrentState = State.NONE;
                        break;
                }
            }
        };
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        mUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        };

        mAnimatorListener = new Animator.AnimatorListener(){

            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                //getHandler 发消息通知动画状态更新
                mAnimatorHandler.sendEmptyMessage(0);

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        };
    }

    /**
     * 初始化路径
     */
    private void initPath() {
        path_circle = new Path();
        path_search = new Path();

        mMeasure = new PathMeasure();

        //注意，不要到360度，否则内部会自动优化，测量不能取到需要的数值
        RectF oval1 = new RectF(-50,-50,50,50);//放大镜圆环
        path_search.addArc(oval1,45,359.9f);

        RectF oval2 = new RectF(-100,-100,100,100);//外部圆环
        path_search.addArc(oval2,45,359.9f);

        float[] pos = new float[2];

        mMeasure.setPath(path_circle,false);//放大镜把手的位置
        mMeasure.getPosTan(0,pos,null);

        path_search.lineTo(pos[0],pos[1]);//放大镜把手

        Log.d("mtag","pos=======" + pos[0] + ":" + pos[1]);

    }

    /**
     * 初始画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
    }
}
