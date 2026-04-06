package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private LinearLayout layoutChat, layoutProjetos;
    private ArrayList<String> listaProjetos = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutChat = findViewById(R.id.layout_chat);
        layoutProjetos = findViewById(R.id.layout_projetos);
        ListView lv = findViewById(R.id.list_projetos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProjetos);
        lv.setAdapter(adapter);

        // Troca de Abas
        findViewById(R.id.tab_chat).setOnClickListener(v -> mostrarAba(layoutChat));
        findViewById(R.id.tab_projetos).setOnClickListener(v -> {
            mostrarAba(layoutProjetos);
            // Akame PhD: Carregar projetos do SQLite aqui
        });

        // Botão Novo Projeto
        findViewById(R.id.btn_novo_projeto).setOnClickListener(v -> {
            String novo = "Projeto_" + System.currentTimeMillis();
            listaProjetos.add(novo);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Projeto Criado no Ecossistema!", Toast.LENGTH_SHORT).show();
        });
    }

    private void mostrarAba(View aba) {
        layoutChat.setVisibility(View.GONE);
        layoutProjetos.setVisibility(View.GONE);
        aba.setVisibility(View.VISIBLE);
    }
}
