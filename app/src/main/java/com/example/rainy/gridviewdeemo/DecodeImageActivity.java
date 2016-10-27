package com.example.rainy.gridviewdeemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rainy.gridviewdeemo.view.CustomDialog;
import com.example.rainy.gridviewdeemo.view.CustomWebView;
import com.example.rainy.gridviewdeemo.zxing.DecodeImage;
import com.google.zxing.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DecodeImageActivity extends AppCompatActivity implements CustomWebView.LongClickCallBack {
    private CustomWebView mCustomWebView;
    private CustomDialog mCustomDialog;
    private String url;
    private File file;
    private boolean isQR;//判断是否为二维码
    private Result result;//二维码解析结果
    private ArrayAdapter<String> adapter;

    /**
     * 是二维码时，才添加为识别二维码
     */
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                if (isQR){
                    adapter.add("识别图中二维码");
                }
                adapter.notifyDataSetChanged();
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decode_image);
        initWebView();
    }

    private void initWebView() {
        //初始webview化控件
        mCustomWebView = new CustomWebView(this,this);
        //这里用博哥页面
        mCustomWebView.loadUrl("http://blog.csdn.net/lmj623565791/article/details/50709663");//加载页面
        mCustomWebView.setFocusable(true);
        mCustomWebView.setFocusableInTouchMode(true);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(mCustomWebView,lp);

    }

    @Override
    public void onLongClickCallBack(String imgUrl) {
        url = imgUrl;
        //获取到图片地址后做相应的处理
        MyAsyncTask	mTask = new MyAsyncTask();
        mTask.execute(imgUrl);
        showDialog();

    }

    public class MyAsyncTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(isQR){
                handler.sendEmptyMessage(0);
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            decodeImage(strings[0]);
            return null;
        }


    }

    /**
     * 判断是否为二维码
     * @param string 图片地址
     * return
     */
    private boolean decodeImage(String string) {
        result = DecodeImage.handleQRCodeFormBitmap(getBitmap(string));
        if(result == null){
            isQR = false;
        }else{
            isQR = true;
        }
        return isQR;
    }


    /**
     * 根据地址获取网络图片
     * @param string 图片地址
     * @return
     * @throws IOException
     */
    private Bitmap getBitmap(String string) {
        try {
            URL url = new URL(string);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                saveMyBitmap(bitmap,"code");//先把bitmap生成jpg图片
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * bitmap 保存为jpg 图片
     * @param mBitmap 图片源
     * @param bitName  图片名
     */
    public void saveMyBitmap(Bitmap mBitmap,String bitName)  {
        file= new File( Environment.getExternalStorageDirectory()+"/"+bitName + ".jpg");
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示Dialog
     * param v
     */
    private void  showDialog() {
        initAdapter();
        mCustomDialog = new CustomDialog(this) {
            @Override
            public void initViews() {
                // 初始CustomDialog化控件
                ListView mListView = (ListView) findViewById(R.id.lv_dialog);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // 点击事件
                        switch (position) {
                            case 0://把图片发送给好友
                                sendToFriends();//把图片发送给好友
                                closeDialog();
                                break;
                            case 1://保存图片到手机
                                saveImageToGallery(DecodeImageActivity.this);//保存图片到图库
                                closeDialog();
                                break;
                            case 2://收藏
                                Toast.makeText(DecodeImageActivity.this, "已收藏", Toast.LENGTH_LONG).show();
                                closeDialog();
                                break;
                            case 3://识别图中二维码
                                goIntent();
                                closeDialog();
                                break;
                        }

                    }
                });
            }
        };
        mCustomDialog.show();
    }

    /**
     * 识别图中二维码
     */
    private void goIntent() {
        Uri uri = Uri.parse(result.toString());
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(intent);
    }

    /**
     * 先保存到本地在广播到图库
     * 保存图片到图库
     * @param context
     */
    private void saveImageToGallery(Context context) {
        //其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), "code", null);
            // 最后通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"
                    + file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送给好友
     */
    private void sendToFriends() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri imageUri = Uri.parse(file.getAbsolutePath());
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_STREAM, imageUri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));

    }

    /**
     * 初始化数据
     */
    private void initAdapter() {
        adapter = new ArrayAdapter<String>(this, R.layout.item_dialog);
        adapter.add("发送给朋友");
        adapter.add("保存到手机");
        adapter.add("收藏");
    }
}
