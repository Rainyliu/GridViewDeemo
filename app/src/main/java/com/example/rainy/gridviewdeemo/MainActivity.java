package com.example.rainy.gridviewdeemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private List<String> list = new ArrayList<String>();
    private MyAdapter adapter;
    private Map<Integer,Boolean> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);

        for (int i = 0; i < 10; i++) {
            list.add("签"+i);
        }

        adapter = new MyAdapter();
        gridView.setAdapter(adapter);


    }

    public class DelClick implements View.OnClickListener {

        private int position;
        private DelInferface inter;
        public DelClick(int position, DelInferface inter) {
            this.position = position;
            this.inter = inter;
        }

        @Override
        public void onClick(View view) {
            inter.delClick(position);
        }
    }

    interface DelInferface{
        void delClick(int position);
    }


    class MyAdapter extends BaseAdapter{
        LayoutInflater inflater;
        private Boolean showBtn = false;
        private DelInferface inter;

        MyAdapter(){
            inflater = getLayoutInflater();
            map = new HashMap<Integer,Boolean>();
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void showDelBtn(Boolean b) {
            showBtn = b;
            notifyDataSetChanged();
        }

        public void setDelInferface(DelInferface inter) {
            this.inter = inter;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if(view == null){
                view = inflater.inflate(R.layout.item_gridview,viewGroup,false);
                holder = new ViewHolder();
                holder.cb = (CheckBox) view.findViewById(R.id.cb);
                holder.iv = (ImageView) view.findViewById(R.id.iv);

                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            if(inter != null) {
                holder.iv.setOnClickListener(new DelClick(i, inter));
            }
            holder.cb.setText(list.get(i));
            if(showBtn) {
                holder.iv.setVisibility(View.VISIBLE);
            } else {
                holder.iv.setVisibility(View.INVISIBLE);
            }
            return view;
        }

        public class ViewHolder{
            CheckBox cb;
            ImageView iv;
        }
    }

}
