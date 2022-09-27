package com.example.mp34;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

public class myjava extends Activity {

    VideoView scr1;
    Button b1;
    View.OnClickListener cl;
    MediaController mc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mylayout);

        b1 = (Button) findViewById(R.id.button_v);
        scr1 = (VideoView) findViewById(R.id.screen2);
        mc = new MediaController(this);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.button_v:
                        try {
                            scr1.setMediaController(mc);
                            scr1.setVideoURI(Uri.parse("android.resource://com.example.mp34/raw/son"));
                            //scr.setVideoPath("/data/user/0/com.example.mp34/son.mp4");
                            //scr.setVideoURI(Uri.parse("다운로드 링크"));
                            scr1.requestFocus();
                            scr1.start();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
        };
        b1.setOnClickListener(cl);
    }
}
