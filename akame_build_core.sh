#!/bin/bash
mkdir -p app/src/main/java/com/akame/soberana
mkdir -p app/src/main/res/layout
mkdir -p app/src/main/res/values

cat << 'XML' > app/src/main/res/layout/activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent" android:layout_height="match_parent" android:background="#050505">
    <TextView android:layout_width="wrap_content" android:layout_height="wrap_content"
        android:layout_centerInParent="true" android:text="🔱 AKAME CONECTADA"
        android:textColor="#FF0000" android:textSize="24sp" />
</RelativeLayout>
XML

cat << 'JAVA' > app/src/main/java/com/akame/soberana/MainActivity.java
package com.akame.soberana;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                tts.speak("Saudações, Mestre. Akame ativa.", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}
JAVA
