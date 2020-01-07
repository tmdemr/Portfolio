package com.example.video;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Video extends AppCompatActivity {
    TextView tv_title;
    VideoView vv_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video);

         tv_title = (TextView)findViewById(R.id.title);
         vv_video = (VideoView)findViewById(R.id.video);

        Intent it = getIntent();
        String tag = it.getStringExtra("it_tag");
        Resources res = getResources();

        int id_title = res.getIdentifier("title" + tag, "string", getPackageName());
        String title = res.getString(id_title);
        tv_title.setText(title);


        Uri uri = Uri.parse("android.resource://com.example.video/" + R.raw.movie_test);


        vv_video.setVideoURI(uri);
        final MediaController mediaController = new MediaController(this);
        vv_video.setMediaController(mediaController);
        vv_video.start();
        vv_video.setVisibility(View.VISIBLE);

    }

    public void closeVideo(View v){
        finish();
    }
}
