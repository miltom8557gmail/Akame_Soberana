package com.akame.omni;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView logOutput;
    private final OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        logOutput = findViewById(R.id.log_output);
        Button btnSinc = findViewById(R.id.btn_sincronizar);
        btnSinc.setOnClickListener(v -> {
            logOutput.setText("⚡ Acessando Nexus Cloud...");
            Request request = new Request.Builder().url("http://127.0.0.1:5000/sincronizar").build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(() -> logOutput.setText("❌ MOTOR V9.2 DISCONNECTED"));
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String res = response.body().string();
                    runOnUiThread(() -> logOutput.setText("🔱 STATUS OMNI:\n\n" + res));
                }
            });
        });
    }
}
