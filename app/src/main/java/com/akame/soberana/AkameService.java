package com.akame.soberana;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AkameService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            while(true) {
                try {
                    Runtime.getRuntime().exec("sh ~/AkameApp/LEGIÃO_IMPERIAL/keep_alive.sh");
                    Thread.sleep(120000);
                } catch (Exception e) {}
            }
        }).start();
        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) { return null; }
}
