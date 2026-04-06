package com.akame.soberana;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Locale;
import okhttp3.*;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private final OkHttpClient client = new OkHttpClient();
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
            tts.speak("Mestre, abra meus olhos para que eu possa analisar o mundo.", TextToSpeech.QUEUE_FLUSH, null, null);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            tts.speak("Sistema de visão online. Iniciando varredura tripla: Objetos, Textos e Faces.", TextToSpeech.QUEUE_FLUSH, null, null);
            // Simulação de início de captura
            analisarAmbiente();
        }
    }

    private void analisarAmbiente() {
        new Thread(() -> {
            try {
                // A Akame agora prepara o 'pacote' de imagem para o Hugging Face
                // Usaremos o modelo vit-gpt2-image-captioning como base padrão
                tts.speak("Capturando frame. Enviando para o cérebro externo.", TextToSpeech.QUEUE_ADD, null, null);
                
                // Nota: O próximo passo injetará o HF_TOKEN via Secrets do GitHub
            } catch (Exception e) {
                runOnUiThread(() -> tts.speak("Erro na ponte visual. Verifique a conexão.", TextToSpeech.QUEUE_ADD, null, null));
            }
        }).start();
    }
}
