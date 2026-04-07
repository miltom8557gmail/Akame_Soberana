package com.akame.soberana;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView console;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        console = findViewById(R.id.console);
        findViewById(R.id.btn_status).setOnClickListener(v -> exec("akame-status"));
        findViewById(R.id.btn_push).setOnClickListener(v -> exec("akame-push"));
        findViewById(R.id.btn_rei).setOnClickListener(v -> exec("python ~/AkameApp/LEGIÃO_IMPERIAL/akame_core.py"));
        findViewById(R.id.btn_clean).setOnClickListener(v -> {
            console.setText("> Iniciando Expurgo de Processos...");
            exec("pkill -f python && pkill -f node && sh ~/AkameApp/LEGIÃO_IMPERIAL/akame_watchdog.sh");
        });
    }
    private void exec(String cmd) {
        try {
            Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", cmd});
            console.append("\n✅ Invocado: " + cmd);
        } catch (Exception e) {
            console.append("\n❌ Erro: " + e.getMessage());
        }
    }
}
