package com.akame.soberana;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    private OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tts = new TextToSpeech(this, status -> {
            if (status != TextToSpeech.ERROR) {
                tts.setLanguage(new Locale("pt", "BR"));
                tts.speak("Permissões de rede ativadas. Supabase e Hugging Face estão no meu radar.", TextToSpeech.QUEUE_FLUSH, null, null);
            }
        });
    }
}
