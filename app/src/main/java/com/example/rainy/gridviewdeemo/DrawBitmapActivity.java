package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rainy.gridviewdeemo.view.CheckView;

public class DrawBitmapActivity extends AppCompatActivity {
    private CheckView checkView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_bitmap);
        checkView = (CheckView) findViewById(R.id.checkView);
//        checkView.check();
        checkView.setAnimDuration(500);
    }

    public void click(View v){
       switch (v.getId()){
           case R.id.check://选中
               checkView.check();
               break;
           case R.id.unCheck://未选中
               checkView.unCheck();
               break;
       }
    }
}
