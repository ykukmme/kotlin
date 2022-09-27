package com.example.mp34;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class music2 extends Service {
    MediaPlayer mp;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mp==null){
            mp = MediaPlayer.create(getApplicationContext(), Uri.parse("/data/user/0/com.example.mp34/love.mp3"));
            mp.setLooping(true);
            mp.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp!=null){
            mp.stop();
            mp = null;
        }
    }
}
