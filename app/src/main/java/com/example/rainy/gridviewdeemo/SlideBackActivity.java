package com.example.rainy.gridviewdeemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rainy.gridviewdeemo.application.MyApplication;
import com.oubowu.slideback.SlideBackHelper;
import com.oubowu.slideback.SlideConfig;
import com.oubowu.slideback.widget.SlideBackLayout;

public class SlideBackActivity extends AppCompatActivity implements View.OnClickListener{
    private SlideBackLayout mSlideBackLayout;
    private Button btnJump;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_back);

        btnJump = (Button) findViewById(R.id.btn);
        btnJump.setOnClickListener(this);
//        mSlideBackLayout = SlideBackHelper.attach(
//                //当前activity
//                SlideBackActivity.this,
//                //activity栈管理工具
//                MyApplication.getActivityHelpler(),
//                //参数的配置
//                new SlideConfig.Builder()
//                        //屏幕是否旋转
//                        .rotateScreen(true)
//                        //是否侧滑
//                        .edgeOnly(false)
//                        //是否禁止侧滑
//                        .lock(false)
//                        //边缘滑动的响应阈（yu）值，0~1，对应屏幕宽度*percent
//                        .edgePercent(0.1f)
//                        // 关闭页面的阈值，0~1，对应屏幕宽度*percent
//                        .slideOutPercent(0.5f)
//                        .create(),
//                // 滑动的监听
//                null);
    }

    /**
     * 如果开启了屏幕旋转，SlideBackLayout需要监听Activity的结束事件，
     * 例如这里的onBackPressed，
     * 所以你需要在调用结束事件的地方加上SlideBackLayout.isComingToFinish()
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mSlideBackLayout.isComingToFinish();
        overridePendingTransition(R.anim.anim_none,R.anim.anim_none);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}
