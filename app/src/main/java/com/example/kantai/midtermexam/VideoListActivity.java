package com.example.kantai.midtermexam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class VideoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_video_list);

        // 指定在頁面當中的 ListView 元件
        ListView listView = (ListView) findViewById(R.id.activity_video_listView);
        // 賦予 ListView 一個 Adapter 元件
        listView.setAdapter(new VideoPostAdapter(this));
        // 賦予 OnItemClick 選項被點擊的事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得被點擊的 Item(Cell)
                VideoPost post = (VideoPost) parent.getItemAtPosition(position);
                // 取得 Item(Cell) 當中的 Arg 與 Name
                String text = "Title : " + post.getTitle();
                // 以 Toast(吐司訊息) show 出內容
                Toast.makeText(VideoListActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.activity_video_list_toolbar_callback);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack();
            }
        });
    }

    private void callBack() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        this.callBack();
        return true;
    }

    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }
}

// 自建一個 PostAdapter(轉接器)，並繼承 BaseAdapter
class VideoPostAdapter extends BaseAdapter {

    protected int cellLayout;
    protected LayoutInflater layoutInflater;
    protected List<VideoPost> postList = new ArrayList<>();

    public VideoPostAdapter(Context context) {
        if (context.getClass().equals(VideoListActivity.class))
            this.cellLayout = R.layout.video_list_layout;

        this.layoutInflater = LayoutInflater.from(context);
        // 利用 for 迴圈去建造 50 筆資料
        for (int i = 0; i < 10; i++)
            postList.add(new VideoPost(R.drawable.rurumi, context.getResources().getString(R.string.main_news_top_left)));
    }

    // (1)複寫長度方法
    @Override
    public int getCount() {
        // 回傳 Post(Cell) 的長度
        return postList.size();
    }

    // (2)複寫取得 Post(Cell) 的方法
    @Override
    public Object getItem(int position) {
        // 回傳目標 Post(Cell)，例如被點到10號 Post(Cell)，就會回傳 postList.get(10))
        // 像 onItemClick 事件的 (... int position ...)
        return postList.get(position);
    }

    // (3)複寫取得 Post(Cell) 編號的方法
    @Override
    public long getItemId(int position) {
        // 回傳目標 Post(Cell) 的編號，例如被點到 10 號 Post(Cell)，就會回傳 10 號
        // 像 onItemClick 事件的 (... long id)
        return position;
    }

    // (4)複寫取得目標 Post(Cell) 畫面(Item View) 的方法
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 如果 convertView 是空的
        if (convertView == null)
            // 就給它一個 View，這個 View 是自訂的 activity_list_view_cell
            convertView = layoutInflater.inflate(this.cellLayout, parent, false);

        // 取得目標 Post 的資料
        VideoPost videoPost = postList.get(position);

        // 抓取 convertView 的 ImageView，並指定它顯示的圖片
        ((ImageView) convertView.findViewById(R.id.activity_video_list_imageView)).setImageResource(videoPost.getImage());

        // 抓取 convertView 的 TextView(Title)，並指定它顯示的內容
        ((TextView) convertView.findViewById(R.id.activity_video_list_titleText)).setText(String.valueOf(videoPost.getTitle()));

        // 將整個 convertView 回傳
        return convertView;
    }
}

// 存放資料的種子
class VideoPost {

    // 定義 ImageView 的 R.id
    protected int imageId;
    // 定義 TextView(title) 的值
    protected String title;

    public VideoPost(int imageId, String title) {
        this.setImage(imageId);
        this.setTitle(title);
    }

    public int getImage() {
        return this.imageId;
    }

    public String getTitle() {
        return this.title;
    }

    private void setImage(int imageId) {
        this.imageId = imageId;
    }

    private void setTitle(String title) {
        this.title = title;
    }
}