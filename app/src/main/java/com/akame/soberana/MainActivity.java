package com.akame.soberana;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        log = findViewById(R.id.console_log);
        findViewById(R.id.btn_status).setOnClickListener(v -> exec("akame-status"));
        findViewById(R.id.btn_push).setOnClickListener(v -> exec("akame-push"));
        findViewById(R.id.btn_build).setOnClickListener(v -> exec("akame-build"));
        findViewById(R.id.btn_reset).setOnClickListener(v -> {
            log.append("\n🔱 Expurgo Iniciado...");
            exec("pkill -f python && sh ~/AkameApp/LEGIÃO_IMPERIAL/akame_watchdog.sh");
        });
    }
    private void exec(String cmd) {
        try { Runtime.getRuntime().exec(new String[]{"/system/bin/sh", "-c", cmd});
        log.append("\n> " + cmd + " [OK]"); } catch (Exception e) { log.append("\nERRO: " + e.getMessage()); }
    }
}
