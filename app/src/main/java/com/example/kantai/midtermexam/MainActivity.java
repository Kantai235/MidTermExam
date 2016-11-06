package com.example.kantai.midtermexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                String message = "";
                switch (tabId) {
                    case R.id.tab_1:
                        message = "我的收藏(紅色)";
                        break;
                    case R.id.tab_2:
                        message = "我的收藏(黃色)";
                        break;
                    case R.id.tab_3:
                        message = "我的收藏(綠色)";
                        break;
                    case R.id.tab_4:
                        message = "我的收藏(藍色)";
                        break;
                }
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        BootstrapButton toolbar_button = (BootstrapButton) findViewById(R.id.toolbar_button);
        toolbar_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://google.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    public void videoClick(View view) {
        Intent intent = new Intent(this, VideoListActivity.class);
        startActivity(intent);
    }

    public void newsClick(View view) {
        Intent intent = new Intent(this, NewsListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_dialog_title)
            .setPositiveButton(R.string.exit_dialog_fire, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            })
            .setNegativeButton(R.string.exit_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    onStop();
                }
            });
        // Create the AlertDialog object and return it
        builder.create();
        builder.show();
        return true;
    }


    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }
}
