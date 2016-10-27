package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.caverock.androidsvg.SVGParseException;
import com.example.rainy.gridviewdeemo.view.SearchView;
import com.example.rainy.gridviewdeemo.view.SearchView1;
import com.example.rainy.gridviewdeemo.view.SvgView;

import java.io.IOException;

public class SvgActivity extends AppCompatActivity {
    SvgView svgView;
    SearchView1 searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchView = new SearchView1(this);
        setContentView(searchView);
//        setContentView(R.layout.activity_svg);

////        LinearLayout layout = new LinearLayout(this);
//        try {
//            svgView  = new SvgView(this);
//        } catch (SVGParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        SvgView.setImageAsset("my_svg_file.svg");
//        layout.addView(svgView,
//            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//    setContentView(layout);
}
}
