package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import butterknife.BindView;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitDemoActivity extends AppCompatActivity {
    @BindView(R.id.contentTV) TextView tv;
    public static final String BASE_URL = "http://api.myservice.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo);

        //要向一个api发送我们的网络请求 ，我们需要使用 Retrofit builder 类并指定service的base URL （通常情况下就是域名）。
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //传入一个自定义的Gson 解析实例
        //要指定一个factory 来对响应进行反序列化，使用的是 Gson library。
        //converters 被添加的顺序将是它们被Retrofit尝试的顺序
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //可以添加多个converters
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ProtoConverterFactory.create())
                .build();


    }
}
