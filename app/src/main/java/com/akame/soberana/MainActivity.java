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
                saudacaoProtocolo();
            }
        });
    }

    private void saudacaoProtocolo() {
        // Fusão: A eficiência do JARVIS com a seriedade da Akame
        String saudacao = "Sistemas online, Mestre. Protocolo Akame-Jarvis ativo. " +
                         "Monitorando integridade do ecossistema e sensores visuais. " +
                         "Eliminarei qualquer erro que cruzar o nosso caminho.";
        tts.speak(saudacao, TextToSpeech.QUEUE_FLUSH, null, null);
        verificarPermissoes();
    }

    private void verificarPermissoes() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            tts.speak("Mestre, para uma análise tática completa, meus olhos precisam estar abertos. Solicito acesso à câmera.", TextToSpeech.QUEUE_ADD, null, null);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        }
    }
}
