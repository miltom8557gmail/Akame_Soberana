package com.akame.soberana;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;

import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class AkameService extends Service {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private final OkHttpClient httpClient = new OkHttpClient();

    // Constantes de Conexão (O ecossistema usará estas chaves)
    private static final String HF_API_URL = "https://api-inference.huggingface.co/models/nlpconnect/vit-gpt2-image-captioning";
    private static final String SUPABASE_URL = "SUA_URL_DO_SUPABASE"; // O Nexus-Fix deve preencher ou já possuir
    private static final String API_KEY = "SUA_CHAVE_API";

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) tts.setLanguage(new Locale("pt", "BR"));
        });
        configurarEscuta();
    }

    private void configurarEscuta() {
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");

        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (data != null && !data.isEmpty()) {
                    String cmd = data.get(0).toLowerCase();
                    if (cmd.contains("akame ga kill")) {
                        processarComando(cmd);
                    }
                }
                recognizer.startListening(intent);
            }
            @Override public void onError(int i) { recognizer.startListening(intent); }
            @Override public void onReadyForSpeech(Bundle b) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float v) {}
            @Override public void onBufferReceived(byte[] b) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onPartialResults(Bundle b) {}
            @Override public void onEvent(int i, Bundle b) {}
        });
        recognizer.startListening(intent);
    }

    private void processarComando(String cmd) {
        enviarParaSupabase(cmd); // Registra na memória eterna

        if (cmd.contains("analisar") || cmd.contains("veja")) {
            tts.speak("Iniciando varredura tática. Consultando Hugging Face.", TextToSpeech.QUEUE_FLUSH, null, null);
            // Aqui a Akame dispararia a captura de imagem e enviaria para HF_API_URL
        } else if (cmd.contains("quem é você")) {
            tts.speak("Eu sou Akame, a Soberana de Ferro. Sua lâmina e sua inteligência.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("Entendido, Mestre. Comando registrado no banco de dados.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void enviarParaSupabase(String log) {
        // Lógica de inserção silenciosa para manter a memória do JARVIS
        RequestBody body = new FormBody.Builder()
            .add("command", log)
            .add("timestamp", String.valueOf(System.currentTimeMillis()))
            .build();

        Request request = new Request.Builder()
            .url(SUPABASE_URL + "/rest/v1/logs")
            .addHeader("apikey", API_KEY)
            .addHeader("Authorization", "Bearer " + API_KEY)
            .post(body)
            .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {}
            @Override public void onResponse(Call call, Response response) throws IOException {
                response.close();
            }
        });
    }

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame", "Akame Service", NotificationManager.IMPORTANCE_LOW);
            getSystemService(NotificationManager.class).createNotificationChannel(chan);
        }
        Notification notification = new NotificationCompat.Builder(this, "akame")
                .setContentTitle("Akame Online")
                .setContentText("Aguardando comando 'Akame Ga Kill'")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build();
        startForeground(1, notification);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
