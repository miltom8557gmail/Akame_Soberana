package com.akame.soberana;

import android.app.*;
import android.content.*;
import android.os.*;
import android.speech.*;
import android.speech.tts.TextToSpeech;
import androidx.core.app.NotificationCompat;
import okhttp3.*;
import java.io.IOException;
import java.util.*;

public class AkameService extends Service {
    private SpeechRecognizer recognizer;
    private TextToSpeech tts;
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void onCreate() {
        super.onCreate();
        iniciarNotificacao();
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
                        executarOrdemCriativa(cmd);
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

    private void executarOrdemCriativa(String cmd) {
        if (cmd.contains("criar filme") || cmd.contains("gerar cena")) {
            tts.speak("Iniciando renderização no Supercomputador do GitHub via Hugging Face. Aguarde, Mestre.", TextToSpeech.QUEUE_FLUSH, null, null);
            enviarComandoParaSupabase("PRODUCAO_VIDEO", cmd);
        } else if (cmd.contains("postar") || cmd.contains("rede social")) {
            tts.speak("Analisando engajamento e preparando publicação estratégica.", TextToSpeech.QUEUE_FLUSH, null, null);
            enviarComandoParaSupabase("SOCIAL_MEDIA", cmd);
        } else if (cmd.contains("luz")) {
            // Comando herdado da V26 para manter integridade
            tts.speak("Acionando hardware tático.", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak("Akame em prontidão. Sistema sincronizado com a nuvem.", TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private void enviarComandoParaSupabase(String tipo, String ordem) {
        // Esta função dispara o gatilho que o GitHub Actions lerá para iniciar o Stable Diffusion
        RequestBody body = new FormBody.Builder()
            .add("type", tipo)
            .add("prompt", ordem)
            .add("status", "pending")
            .build();

        Request request = new Request.Builder()
            .url("https://miltom8557.supabase.co/rest/v1/tasks")
            .addHeader("apikey", "SUA_API_KEY")
            .post(body)
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {}
            @Override public void onResponse(Call call, Response response) throws IOException { response.close(); }
        });
    }

    private void iniciarNotificacao() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel chan = new NotificationChannel("akame_v27", "Akame Creative", NotificationManager.IMPORTANCE_LOW);
            ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).createNotificationChannel(chan);
        }
        Notification n = new NotificationCompat.Builder(this, "akame_v27")
                .setContentTitle("Akame: Soberana V27")
                .setContentText("Nexo HuggingFace-Supabase-GitHub Ativo")
                .setSmallIcon(android.R.drawable.ic_menu_camera)
                .build();
        startForeground(1, n);
    }

    @Override public int onStartCommand(Intent i, int f, int s) { return START_STICKY; }
    @Override public IBinder onBind(Intent i) { return null; }
}
