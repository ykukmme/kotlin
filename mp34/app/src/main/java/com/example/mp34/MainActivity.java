package com.example.mp34;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button pl_a, st_a, pl_v, st_v, ca_a, pl_b, st_b, b1, b2;
    View.OnClickListener cl;
    MediaPlayer mp;
    VideoView scr;
    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mc = new MediaController(this);

        pl_a = (Button) findViewById(R.id.play_audio);
        st_a = (Button) findViewById(R.id.stop_audio);

        pl_v = (Button) findViewById(R.id.play_video);
        st_v = (Button) findViewById(R.id.stop_video);

        pl_b = (Button) findViewById(R.id.play_back);
        st_b = (Button) findViewById(R.id.stop_back);

        b1 = (Button) findViewById(R.id.love_start);
        b2 = (Button) findViewById(R.id.love_stop);

        ca_a = (Button) findViewById(R.id.call_activity);

        scr = (VideoView) findViewById(R.id.screen);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.play_audio:
                        if(mp==null){
                           // mp = MediaPlayer.create(getApplicationContext(), R.raw.kal);
                            //mp.start();
                            try {
                                mp = new MediaPlayer();
                                mp.setDataSource("/data/user/0/com.example.mp34/love.mp3");
                                //mp.setDataSource("/storage/emulated/0/Android/data/com.example.mp34/files/love.mp3");
                                //mp.setDataSource(getApplicationContext(),Uri.parse("http://naver.com"));-> 해당 링크에서 다운로드 받고 실행 파일로 만들기.
                                mp.prepare();
                                mp.start();

                            } catch (Exception e){
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                        break;
                    case R.id.stop_audio:
                        if(mp!= null){
                            mp.stop();
                            mp.release();
                            mp = null;
                        }
                        break;
                    case R.id.play_video:
                        try {
                            scr.setMediaController(mc);
                            scr.setVideoURI(Uri.parse("android.resource://com.example.mp34/raw/son"));
                            //scr.setVideoPath("/data/user/0/com.example.mp34/son.mp4");
                            //scr.setVideoURI(Uri.parse("다운로드 링크"));
                            scr.requestFocus();
                            scr.start();
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.stop_video:
                        scr.stopPlayback();
                        break;
                    case R.id.call_activity:
                        Intent i =new Intent(getApplicationContext(), myjava.class);
                        startActivity(i);
                        break;
                    case R.id.play_back:
                        Intent j = new Intent(getApplicationContext(), music.class);
                        startService(j);
                        break;
                    case R.id.stop_back:
                        Intent k = new Intent(getApplicationContext(), music.class);
                        stopService(k);
                        break;
                    case R.id.love_start:
                        Intent A = new Intent(getApplicationContext(), music2.class);
                        startService(A);
                        break;
                    case R.id.love_stop:
                        Intent B = new Intent(getApplicationContext(), music2.class);
                        stopService(B);
                        break;
                }
            }
        };
        pl_a.setOnClickListener(cl);
        st_a.setOnClickListener(cl);

        pl_v.setOnClickListener(cl);
        st_v.setOnClickListener(cl);

        pl_b.setOnClickListener(cl);
        st_b.setOnClickListener(cl);

        b1.setOnClickListener(cl);
        b2.setOnClickListener(cl);

        ca_a.setOnClickListener(cl);

    }
}