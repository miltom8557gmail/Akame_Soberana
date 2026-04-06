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
import java.util.ArrayList;
import java.util.Locale;

public class AkameService extends Service {
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecognizer;
    private TextToSpeech tts;
    private static final String CHANNEL_ID = "AkameChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        criarCanalNotificacao();
        
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Akame: Soberana de Ferro")
                .setContentText("Protocolo de Escuta Ativa...")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build();
        
        startForeground(1, notification);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) tts.setLanguage(new Locale("pt", "BR"));
        });

        configurarEscuta();
    }

    private void configurarEscuta() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        intentRecognizer = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intentRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (data != null && !data.isEmpty()) {
                    String cmd = data.get(0).toLowerCase();
                    if (cmd.contains("akame ga kill")) {
                        if (cmd.contains("status")) {
                            tts.speak("Sistemas operacionais estáveis, Mestre.", TextToSpeech.QUEUE_FLUSH, null, null);
                        } else {
                            tts.speak("Às suas ordens.", TextToSpeech.QUEUE_FLUSH, null, null);
                        }
                    }
                }
                speechRecognizer.startListening(intentRecognizer);
            }
            @Override public void onError(int i) { speechRecognizer.startListening(intentRecognizer); }
            @Override public void onReadyForSpeech(Bundle b) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float v) {}
            @Override public void onBufferReceived(byte[] b) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onPartialResults(Bundle b) {}
            @Override public void onEvent(int i, Bundle b) {}
        });
        speechRecognizer.startListening(intentRecognizer);
    }

    private void criarCanalNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Akame Service", NotificationManager.IMPORTANCE_LOW);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override public int onStartCommand(Intent intent, int flags, int startId) { return START_STICKY; }
    @Override public IBinder onBind(Intent intent) { return null; }
}
