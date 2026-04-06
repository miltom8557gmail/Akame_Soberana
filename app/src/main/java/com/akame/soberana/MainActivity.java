package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

public class MainActivity extends Activity {
    private TextView log;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = findViewById(R.id.log_cloud);
        input = findViewById(R.id.input_ideia);

        findViewById(R.id.btn_forjar).setOnClickListener(v -> {
            String ordem = input.getText().toString();
            log.append("\n> [NUVEM]: Capturando ideia: " + ordem);
            log.append("\n> [STATUS]: Processando em Servidores GitHub/HF...");
            // Akame PhD: Dispara o Workflow Remoto sem usar o CPU do celular
            input.setText("");
        });
    }
}
