package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.os.Handler;
import java.io.*;

public class MainActivity extends Activity {
    private TextView chatDisplay;
    private EditText chatInput;
    private Button btnSend;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chatDisplay = findViewById(R.id.chat_display);
        chatInput = findViewById(R.id.chat_input);
        btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(v -> {
            String msg = chatInput.getText().toString();
            if(!msg.isEmpty()) {
                chatDisplay.append("\nMESTRE: " + msg);
                chatInput.setText("");
                // Aqui o Java enviaria a ordem para o Python via Intent ou Socket
            }
        });

        iniciarLeituraDePensamento();
    }

    private void iniciarLeituraDePensamento() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simulação de leitura do log de saída da Akame (brain_stream.log)
                chatDisplay.append("\nAKAME: Processando ecossistema...");
                handler.postDelayed(this, 5000);
            }
        }, 2000);
    }
}
