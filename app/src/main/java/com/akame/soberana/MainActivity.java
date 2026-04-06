package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import java.io.*;

public class MainActivity extends Activity {
    private TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.neural_log);
        Button btnSync = findViewById(R.id.btn_sync_cloud);

        btnSync.setOnClickListener(v -> {
            log.append("\n> [TERMUX]: Sincronizando miltom.jose8557@gmail.com...");
            executarNoTermux("rclone sync ~/AkameApp miltom.jose8557@gmail.com:Akame_Backups/");
        });
    }

    private void executarNoTermux(String comando) {
        // Akame PhD: Envia o comando diretamente para o ecossistema Termux
        try {
            Process p = Runtime.getRuntime().exec(comando);
            log.append("\n✅ EXECUÇÃO: " + comando);
        } catch (Exception e) {
            log.append("\n❌ FALHA NO ECOSSISTEMA: " + e.getMessage());
        }
    }
}
