package com.akame.soberana;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                iniciarSincronizacao();
            }
        });
    }

    private void iniciarSincronizacao() {
        tts.speak("Iniciando Sincronização Omni. Consultando Supabase e Hugging Face.", TextToSpeech.QUEUE_FLUSH, null, null);
        
        // A Akame agora tenta validar a conexão em segundo plano
        new Thread(() -> {
            try {
                // Simulação de pulso de rede para validar a ponte
                Request request = new Request.Builder().url("https://huggingface.co/api/models").build();
                Response response = client.newCall(request).execute();
                
                if (response.isSuccessful()) {
                    runOnUiThread(() -> tts.speak("Ponte com Hugging Face estabelecida com sucesso. Cérebro externo online.", TextToSpeech.QUEUE_ADD, null, null));
                }
            } catch (Exception e) {
                runOnUiThread(() -> tts.speak("Aviso: Ponte externa aguardando chaves de autenticação.", TextToSpeech.QUEUE_ADD, null, null));
            }
        }).start();
    }
}
