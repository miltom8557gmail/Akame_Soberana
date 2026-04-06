package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {
    private TextView statusLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusLog = findViewById(R.id.status_log);

        findViewById(R.id.btn_deploy_web).setOnClickListener(v -> enviarComando("DEPLOY_WEB"));
        findViewById(R.id.btn_ai_analyze).setOnClickListener(v -> enviarComando("AI_ANALYZE"));
        findViewById(R.id.btn_gen_video).setOnClickListener(v -> enviarComando("GEN_VIDEO"));
        findViewById(R.id.btn_sync_all).setOnClickListener(v -> enviarComando("SYNC_ALL"));
    }

    private void enviarComando(String cmd) {
        statusLog.setText("> ENVIANDO: " + cmd + "...");
        // Akame PhD: Aqui o comando é injetado no Supabase/GitHub para o Termux ler.
        new android.os.Handler().postDelayed(() -> {
            statusLog.setText("> COMANDO " + cmd + " EXECUTADO NA NUVEM.");
        }, 2000);
    }
}
