package com.example.mp34;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class music extends Service {
    MediaPlayer mp;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mp==null){
            mp = MediaPlayer.create(getApplicationContext(), R.raw.kal);
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
