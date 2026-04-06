package com.akame.soberana;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private SpeechRecognizer speechRecognizer;
    private Intent intentRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                configurarEscuta();
            }
        });
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
                if (data != null && data.get(0).toLowerCase().contains("akame ga kill")) {
                    tts.speak("Sim, Mestre. Estou a ouvir. Quais são as ordens?", TextToSpeech.QUEUE_FLUSH, null, null);
                }
                speechRecognizer.startListening(intentRecognizer); // Reinicia a escuta
            }
            @Override public void onReadyForSpeech(Bundle bundle) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float v) {}
            @Override public void onBufferReceived(byte[] bytes) {}
            @Override public void onEndOfSpeech() {}
            @Override public void onError(int i) { speechRecognizer.startListening(intentRecognizer); }
            @Override public void onPartialResults(Bundle bundle) {}
            @Override public void onEvent(int i, Bundle bundle) {}
        });

        speechRecognizer.startListening(intentRecognizer);
        tts.speak("Sistemas auditivos sincronizados. Pode chamar-me a qualquer momento.", TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
