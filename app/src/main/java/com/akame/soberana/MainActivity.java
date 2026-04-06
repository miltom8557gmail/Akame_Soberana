package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.*;
import android.os.Handler;

public class MainActivity extends Activity {
    private TextView cpuStatus, batStatus, displayNeural;
    private EditText inputComando;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpuStatus = findViewById(R.id.cpu_status);
        batStatus = findViewById(R.id.bat_status);
        displayNeural = findViewById(R.id.display_neural);
        inputComando = findViewById(R.id.input_comando);

        findViewById(R.id.btn_enviar).setOnClickListener(v -> {
            String msg = inputComando.getText().toString();
            if(!msg.isEmpty()) {
                displayNeural.append("\nMESTRE: " + msg);
                inputComando.setText("");
                displayNeural.append("\nAKAME: Processando via Llama-3...");
            }
        });

        atualizarHardware();
    }

    private void atualizarHardware() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent batteryIntent = registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
                int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                batStatus.setText("BAT: " + level + "%");
                cpuStatus.setText("CPU: " + (int)(Math.random() * 30 + 10) + "%"); // Simulação PhD
                handler.postDelayed(this, 3000);
            }
        }, 1000);
    }
}
