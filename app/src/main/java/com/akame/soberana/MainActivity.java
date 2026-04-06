package com.akame.soberana;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private static final int CAMERA_PERMISSION_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                verificarPermissoes();
            }
        });
    }

    private void verificarPermissoes() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            tts.speak("Mestre, os três pilares da visão aguardam permissão.", TextToSpeech.QUEUE_FLUSH, null, null);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            tts.speak("Sistemas de visão integrados. Pronta para reconhecer objetos, ler textos e identificar sua face.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    // O motor de processamento será ativado aqui no próximo passo (Passo 2 da Visão)
    private void processarVisao(int modo) {
        // modo 1: Objetos | modo 2: Texto | modo 3: Face
        tts.speak("Iniciando análise sensorial profunda.", TextToSpeech.QUEUE_ADD, null, null);
    }
}
