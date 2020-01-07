package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(this, R.raw.third);
        mp.setLooping(false);
        mp.start();


    }

    @Override
    protected void onDestroy() {
        if (mp.isPlaying()){
            mp.stop();
            mp.release();
        }

        super.onDestroy();
    }
}
