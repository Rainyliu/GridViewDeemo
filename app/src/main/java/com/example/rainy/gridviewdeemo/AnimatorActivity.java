package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.rainy.gridviewdeemo.view.PathView;

public class AnimatorActivity extends AppCompatActivity {
    PathView pathView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        pathView.getPathAnimator()
                .delay(100)
                .duration(500)
//                .listenerStart(new AnimationListenerStart())
//                .listenerEnd(new AnimationListenerEnd())
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();

    }
}
