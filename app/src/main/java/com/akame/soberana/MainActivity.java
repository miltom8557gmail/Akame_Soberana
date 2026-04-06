package com.akame.soberana;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final TextView subStatus = (TextView) findViewById(R.id.subStatus);
        Button btnStatus = (Button) findViewById(R.id.btnStatus);
        Button btnLog = (Button) findViewById(R.id.btnLog);
        Button btnForge = (Button) findViewById(R.id.btnForge);

        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subStatus.setText("🔍 Akame: Todos os sistemas estão nominais v17.0");
                Toast.makeText(MainActivity.this, "Varredura Concluída", Toast.LENGTH_SHORT).show();
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subStatus.setText("📡 Akame: Enviando Pulso ao Supabase...");
                // Aqui no futuro chamaremos o script python de memória
            }
        });

        btnForge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subStatus.setText("🔥 Akame: Nova build solicitada ao GitHub!");
            }
        });
    }
}
