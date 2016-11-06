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

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_list);

        // 指定在頁面當中的 ListView 元件
        ListView listView = (ListView) findViewById(R.id.activity_News_listView);
        // 賦予 ListView 一個 Adapter 元件
        listView.setAdapter(new NewsPostAdapter(this));
        // 賦予 OnItemClick 選項被點擊的事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsListActivity.this, NewsFullActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView = (ImageView) findViewById(R.id.activity_News_list_toolbar_callback);
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
class NewsPostAdapter extends BaseAdapter {

    protected int cellLayout;
    protected LayoutInflater layoutInflater;
    protected List<NewsPost> postList = new ArrayList<>();

    public NewsPostAdapter(Context context) {
        if (context.getClass().equals(NewsListActivity.class))
            this.cellLayout = R.layout.news_list_layout;

        this.layoutInflater = LayoutInflater.from(context);
        // 利用 for 迴圈去建造 50 筆資料
        for (int i = 0; i < 20; i++)
            postList.add(new NewsPost(context.getResources().getString(R.string.news_title), context.getResources().getString(R.string.news_subtitle)));
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
        NewsPost NewsPost = postList.get(position);

        // 抓取 convertView 的 TextView(Title)，並指定它顯示的內容
        ((TextView) convertView.findViewById(R.id.activity_news_list_titleText)).setText(String.valueOf(NewsPost.getTitle()));

        // 抓取 convertView 的 TextView(Subitle)，並指定它顯示的內容
        ((TextView) convertView.findViewById(R.id.activity_news_list_subtitleText)).setText(String.valueOf(NewsPost.getSubitle()));

        // 將整個 convertView 回傳
        return convertView;
    }
}

// 存放資料的種子
class NewsPost {

    // 定義 TextView(title) 的值
    protected String title;
    // 定義 TextView(suubtitle) 的值
    protected String subtitle;

    public NewsPost(String title, String subtitle) {
        this.setTitle(title);
        this.setSubitle(subtitle);
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubitle() {
        return this.subtitle;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setSubitle(String subtitle) {
        this.subtitle = subtitle;
    }
}