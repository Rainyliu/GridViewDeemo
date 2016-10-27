package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.rainy.gridviewdeemo.view.Bezier;
import com.example.rainy.gridviewdeemo.view.Bezier2;
import com.example.rainy.gridviewdeemo.view.Bezier3;
import com.example.rainy.gridviewdeemo.view.DetailsPath;

public class BezierActivity extends AppCompatActivity {
    private Bezier bezier;
    private Bezier2 bezier2;
    private Bezier3 bezier3;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DetailsPath(this));
//        setContentView(new Bezier3(this));
//        setContentView(new Bezier(this));
//        setContentView(R.layout.activity_bezier);
//        bezier2 = (Bezier2) findViewById(R.id.bezier2);
//        rg = (RadioGroup) findViewById(R.id.rg);
//
//        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                RadioButton rb = (RadioButton) findViewById(i);
//                Log.d("mtag","i=============="+i);
//                switch (rb.getId()){
//                    case R.id.rb1:
//                        bezier2.setMode(true);
//                        break;
//                    case R.id.rb2:
//                        bezier2.setMode(false);
//                        break;
//                }
//            }
//        });

}
}
