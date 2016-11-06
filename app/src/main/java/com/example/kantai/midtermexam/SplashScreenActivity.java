package com.example.kantai.midtermexam;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;

public class SplashScreenActivity extends AppCompatActivity {

    protected HTextView hTextView;
    protected MediaPlayer mediaPlayer;
    protected int mCounter = 0;
    protected String[] sentences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_sound);
        this.mediaPlayer.start();

        this.sentences = getResources().getStringArray(R.array.splash_screen_title);

        hTextView = (HTextView) findViewById(R.id.textView);
        hTextView.setAnimateType(HTextViewType.TYPER);
        hTextView.animateText(sentences[mCounter]);
    }

    public void splashScreenClick(View view) {
        mCounter++;
        if (mCounter < sentences.length) {
            hTextView.animateText(sentences[mCounter]);
        } else {
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            this.mediaPlayer.stop();
        }
    }

}