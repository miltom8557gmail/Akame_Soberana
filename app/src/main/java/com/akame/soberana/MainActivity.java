package com.akame.soberana;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private boolean isReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tts = new TextToSpeech(this, this);

        final TextView subStatus = (TextView) findViewById(R.id.subStatus);
        Button btnStatus = (Button) findViewById(R.id.btnStatus);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Sistemas operacionais, mestre. Akame v21 está ativa.";
                subStatus.setText("🔍 " + msg);
                falar(msg);
            }
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(new Locale("pt", "BR"));
            isReady = true;
        }
    }

    private void falar(String texto) {
        if (isReady) {
            tts.speak(texto, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
