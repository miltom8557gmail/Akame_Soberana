package com.akame.soberana;

import android.app.*;
import android.content.*;
import android.os.*;
import android.speech.*;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;
import java.util.*;

public class AkameService extends Service {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private long lastActiveTime = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
        tts = new TextToSpeech(this, s -> {
            if (s != TextToSpeech.ERROR) tts.setLanguage(new Locale("pt", "BR"));
        });
        configurarEscutaInteligente();
    }

    private void configurarEscutaInteligente() {
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");

        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onResults(Bundle b) {
                ArrayList<String> res = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (res != null && !res.isEmpty()) {
                    String cmd = res.get(0).toLowerCase();
                    if (cmd.contains("akame ga kill")) {
                        processar(cmd);
                    }
                }
                reiniciarEscuta(intent);
            }
            @Override public void onError(int i) { reiniciarEscuta(intent); }
            // Métodos obrigatórios vazios para limpeza
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

    private void reiniciarEscuta(Intent intent) {
        // Lógica de Economia: Se não houve comando nos últimos 10 min, aumenta o delay de escuta
        long delay = (System.currentTimeMillis() - lastActiveTime > 600000) ? 2000 : 100;
        new Handler(Looper.getMainLooper()).postDelayed(() -> recognizer.startListening(intent), delay);
    }

    private void processar(String cmd) {
        lastActiveTime = System.currentTimeMillis();
        if (cmd.contains("localização") || cmd.contains("onde estou")) {
            tts.speak("Rastreando coordenadas, Mestre. Localização enviada ao Supabase.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("Protocolo Akame-Jarvis ativo. Ordens recebidas.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame_v25", "Akame Sentinela", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(chan);
        }
        Notification notification = new NotificationCompat.Builder(this, "akame_v25")
                .setContentTitle("Akame: Soberana V25")
                .setContentText("Modo Sentinela: Monitorando Voz e Localização")
                .setSmallIcon(android.R.drawable.ic_lock_idle_lock)
                .build();
        startForeground(1, notification);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
