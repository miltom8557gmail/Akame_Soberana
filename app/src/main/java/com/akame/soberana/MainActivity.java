package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ImageView imgPreview;
    private VideoView videoPreview;
    private ArrayList<String> listaProjetos = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPreview = findViewById(R.id.img_preview);
        videoPreview = findViewById(R.id.video_preview);
        ListView lv = findViewById(R.id.list_projetos);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaProjetos);
        lv.setAdapter(adapter);

        // Ao Clicar no Projeto: Carregar Preview
        lv.setOnItemClickListener((parent, view, position, id) -> {
            String projeto = listaProjetos.get(position);
            // Akame PhD: Lógica para buscar thumb local ou online
            Toast.makeText(this, "Carregando Visual: " + projeto, Toast.LENGTH_SHORT).show();
            // Simulação: Carregar imagem local
            imgPreview.setVisibility(View.VISIBLE);
            videoPreview.setVisibility(View.GONE);
            // imgPreview.setImageResource(R.drawable.exemplo_thumb); // Requer Glide p/ URLs
        });

        // Botão Novo
        findViewById(R.id.btn_novo).setOnClickListener(v -> {
            String novo = "Mídia_Deepfake_" + System.currentTimeMillis();
            listaProjetos.add(novo);
            adapter.notifyDataSetChanged();
        });
    }
}
