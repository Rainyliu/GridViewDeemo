package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class TwinklingRefreshLayoutActivity extends AppCompatActivity {

    private TwinklingRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twinkling_refresh_layout);

        refreshLayout = (TwinklingRefreshLayout) findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        setRecyclerView(recyclerView);

    }

    /**
     * 设置recyclerView
     * @param rv
     */
    public void setRecyclerView(RecyclerView rv){
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));


    }
}
