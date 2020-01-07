package com.example.video;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void DisplayVideo(View v){
        int id = v.getId();
        LinearLayout layout = (LinearLayout)v.findViewById(id);
        String tag = (String)layout.getTag();

        Intent it = new Intent(this, Video.class);
        it.putExtra("it_tag", tag);
        startActivity(it);


    }
}
