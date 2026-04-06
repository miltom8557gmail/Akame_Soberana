package com.akame.soberana;

import android.app.*;
import android.content.*;
import android.hardware.camera2.*;
import android.os.*;
import android.speech.*;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;
import java.util.*;

public class AkameService extends Service {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private CameraManager cameraManager;
    private String cameraId;

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try { cameraId = cameraManager.getCameraIdList()[0]; } catch (Exception e) {}
        
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
            public void onResults(Bundle b) {
                ArrayList<String> res = b.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (res != null && !res.isEmpty()) {
                    String cmd = res.get(0).toLowerCase();
                    if (cmd.contains("akame ga kill")) {
                        executarProtocolo(cmd);
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

    private void executarProtocolo(String cmd) {
        if (cmd.contains("luz") || cmd.contains("lanterna")) {
            alternarLanterna(true);
            tts.speak("Lanterna ativada, Mestre.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else if (cmd.contains("desligar luz")) {
            alternarLanterna(false);
            tts.speak("Luz desativada.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else if (cmd.contains("relatório") || cmd.contains("forja")) {
            tts.speak("Acessando GitHub. Versão 26 operacional. Build da V25 concluída sem erros.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("Akame ouvindo. Aguardando ordens táticas.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void alternarLanterna(boolean estado) {
        try { cameraManager.setTorchMode(cameraId, estado); } catch (Exception e) {}
    }

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame_v26", "Akame Ômega", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(chan);
        }
        Notification notification = new NotificationCompat.Builder(this, "akame_v26")
                .setContentTitle("Akame: Protocolo Ômega")
                .setContentText("Hardware e Nuvem Sincronizados")
                .setSmallIcon(android.R.drawable.ic_menu_manage)
                .build();
        startForeground(1, notification);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
