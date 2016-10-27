package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ButterKnifeActivity extends AppCompatActivity {

    @BindView(R.id.iv) AppCompatImageView iv;
    @BindView(R.id.tv) TextView tv;
    @BindView(R.id.btn) Button btn;

    @OnClick(R.id.btn) void say(){
        Toast.makeText(this,"这是一个按钮",Toast.LENGTH_LONG).show();
    }

    @OnLongClick(R.id.btn) boolean sayGetOffMe() {
        Toast.makeText(this, "Let go of me!", Toast.LENGTH_SHORT).show();
        return true;
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        ButterKnife.bind(this);
    }
}
