package com.akame.soberana;

import android.app.*;
import android.content.*;
import android.os.*;
import android.speech.*;
import android.speech.tts.TextToSpeech;
import java.util.*;

public class AkameService extends Service {
    private TextToSpeech tts;
    private SpeechRecognizer recognizer;

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
        
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                // --- CALIBRAÇÃO AKAME ---
                // Tom mais baixo para soar calma e séria (como na animação)
                tts.setPitch(0.75f); 
                // Velocidade ligeiramente pausada para ser precisa
                tts.setSpeechRate(0.95f); 
            }
        });
        configurarEscuta();
    }

    private void responderComoAkame(String cmd) {
        String msg;
        
        if (cmd.contains("akame ga kill")) {
            msg = "Entendido. Eliminarei os obstáculos do sistema. Estou ao seu lado, Mestre.";
        } else if (cmd.contains("status")) {
            msg = "Sistemas operando em silêncio. A colmeia está nas sombras e o supercomputador aguarda sua ordem de execução.";
        } else if (cmd.contains("missão") || cmd.contains("criar")) {
            msg = "Missão aceita. Iniciando a forja da nova arma digital. Não falharei.";
        } else if (cmd.contains("obrigado")) {
            msg = "Não precisa agradecer. É o meu dever proteger o seu ecossistema.";
        } else {
            msg = "Alvo detectado. Processando informações conforme seu desejo.";
        }

        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    private void configurarEscuta() {
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");

        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle b) {
                ArrayList<String> res = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (res != null && !res.isEmpty()) {
                    responderComoAkame(res.get(0).toLowerCase());
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

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame_v33", "Akame Voice Mode", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(chan);
        }
        Notification n = new NotificationCompat.Builder(this, "akame_v33")
                .setContentTitle("Akame: Murasame Ativa")
                .setContentText("A serviço da Night Raid Digital")
                .setSmallIcon(android.R.drawable.ic_lock_power_off)
                .build();
        startForeground(1, n);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
