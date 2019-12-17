package com.example.musicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonPlay;
    Button buttonStop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPlay = findViewById(R.id.play);
        buttonStop = findViewById(R.id.stop);
        buttonPlay.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonPlay) {
            player = MediaPlayer.create(this, R.raw.abc);
            player.start();
            player.setLooping(true);
        } else if (view == buttonStop) {
            player.stop();
        }
    }
}
