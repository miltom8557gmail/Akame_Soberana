package com.akame.soberana;

import android.app.*;
import android.content.*;
import android.os.*;
import android.speech.*;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.core.app.NotificationCompat;
import java.util.*;

public class AkameService extends Service {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private View pulseCircle;
    private Animation pulseAnim;

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
        
        // Carregar animação de pulsação (Precisa ser criada em res/anim)
        pulseAnim = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        pulseAnim.setRepeatCount(Animation.INFINITE);
        pulseAnim.setRepeatMode(Animation.REVERSE);

        tts = new TextToSpeech(this, s -> {
            if (s != TextToSpeech.ERROR) tts.setLanguage(new Locale("pt", "BR"));
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
            public void onBeginningOfSpeech() {
                // Ativar animação de "Ouvindo" no Avatar
                ativarPulsação(true);
            }

            @Override
            public void onResults(Bundle b) {
                ativarPulsação(false);
                ArrayList<String> res = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (res != null && !res.isEmpty()) {
                    String cmd = res.get(0).toLowerCase();
                    processarOrdem(cmd);
                }
                recognizer.startListening(intent);
            }

            @Override public void onError(int i) { ativarPulsação(false); recognizer.startListening(intent); }
            @Override public void onReadyForSpeech(Bundle b) {}
            @Override public void onRmsChanged(float v) {}
            @Override public void onBufferReceived(byte[] b) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onPartialResults(Bundle b) {}
            @Override public void onEvent(int i, Bundle b) {}
        });
        recognizer.startListening(intent);
    }

    private void ativarPulsação(boolean ligar) {
        // Esta lógica deve ser vinculada à UI da MainActivity.
        // Como é um Service, enviaremos um Broadcast para a MainActivity atualizar a UI.
        Intent intent = new Intent("AKAME_PULSE");
        intent.putExtra("estado", ligar);
        sendBroadcast(intent);
    }

    private void processarOrdem(String cmd) {
        if (cmd.contains("akame ga kill")) {
            tts.speak("Soberana em prontidão, Mestre. O que deseja criar?", TextToSpeech.QUEUE_FLUSH, null, null);
        } else if (cmd.contains("status")) {
            tts.speak("V31 operando. Colmeia ativa no Termux. Nexo de Supercomputador sincronizado.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("Analisando ordem multimodal.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame_v31", "Akame Visual", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(chan);
        }
        Notification n = new NotificationCompat.Builder(this, "akame_v31")
                .setContentTitle("Akame: Protocolo Avatar V31")
                .setContentText("A Soberana tem um rosto.")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .build();
        startForeground(1, n);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
