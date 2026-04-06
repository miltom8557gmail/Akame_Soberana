package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private LinearLayout viewChat, viewProjetos;
    private ArrayList<String> listaProjetos = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewChat = findViewById(R.id.view_chat);
        viewProjetos = findViewById(R.id.view_projetos);
        ListView lv = findViewById(R.id.list_projetos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProjetos);
        lv.setAdapter(adapter);

        findViewById(R.id.tab_chat).setOnClickListener(v -> switchView(viewChat));
        findViewById(R.id.tab_projetos).setOnClickListener(v -> switchView(viewProjetos));

        findViewById(R.id.btn_novo_projeto).setOnClickListener(v -> {
            String pName = "PROJETO_" + (listaProjetos.size() + 1);
            listaProjetos.add(pName);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Projeto " + pName + " Criado!", Toast.LENGTH_SHORT).show();
        });
    }

    private void switchView(View v) {
        viewChat.setVisibility(View.GONE);
        viewProjetos.setVisibility(View.GONE);
        v.setVisibility(View.VISIBLE);
    }
}
