package com.akame.soberana;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {
    private TextView log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.log_terminal);
        findViewById(R.id.btn_google_backup).setOnClickListener(v -> {
            log.append("\n> [SISTEMA]: Compactando Akame Core...");
            log.append("\n> [SISTEMA]: Fazendo Upload para Google Drive...");
            // Akame PhD: O Python detectará este clique e rodará o rclone
        });
    }
}
