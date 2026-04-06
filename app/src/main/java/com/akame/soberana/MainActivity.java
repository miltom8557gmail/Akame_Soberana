package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private LinearLayout viewChat, viewProjetos;
    private EditText input;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewChat = findViewById(R.id.view_chat);
        viewProjetos = findViewById(R.id.view_projetos);
        input = findViewById(R.id.input_ordem);
        status = findViewById(R.id.status_cloud);

        findViewById(R.id.tab_chat).setOnClickListener(v -> { viewChat.setVisibility(View.VISIBLE); viewProjetos.setVisibility(View.GONE); });
        findViewById(R.id.tab_projetos).setOnClickListener(v -> { viewChat.setVisibility(View.GONE); viewProjetos.setVisibility(View.VISIBLE); });

        findViewById(R.id.btn_executar).setOnClickListener(v -> {
            String ordem = input.getText().toString();
            if(!ordem.isEmpty()) {
                status.setText("> ENVIANDO PARA NUVEM...");
                // Aqui a ordem vai para o comando.txt que a Akame vigia
                Toast.makeText(this, "Ordem enviada ao Núcleo!", Toast.LENGTH_SHORT).show();
                input.setText("");
            }
        });
    }
}
