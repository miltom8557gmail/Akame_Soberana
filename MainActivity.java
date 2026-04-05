package com.akame;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        status = findViewById(R.id.status_txt);
        findViewById(R.id.btn_sinc).setOnClickListener(v -> api("/sincronizar", "☁️ SINCRONIZANDO..."));
        findViewById(R.id.btn_run).setOnClickListener(v -> api("/compilar", "🔥 FORJANDO..."));
        findViewById(R.id.btn_limpar).setOnClickListener(v -> api("/limpar_logs", "🧹 LIMPANDO..."));
    }
    private void api(String rota, String msg) {
        status.setText(msg);
        new Thread(() -> {
            try {
                URL url = new URL("http://127.0.0.1:8080" + rota);
                HttpURLConnection c = (HttpURLConnection) url.openConnection();
                int r = c.getResponseCode();
                runOnUiThread(() -> status.setText(r == 200 ? "🔱 SUCESSO" : "⚠️ ERRO"));
            } catch (Exception e) {
                runOnUiThread(() -> status.setText("🔥 MOTOR OFF"));
            }
        }).start();
    }
}
