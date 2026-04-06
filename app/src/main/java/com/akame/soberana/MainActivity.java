package com.akame.soberana;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText input = findViewById(R.id.input_ordem);
        final TextView status = findViewById(R.id.status);
        findViewById(R.id.btn_enviar).setOnClickListener(v -> {
            String ordem = input.getText().toString();
            if(!ordem.isEmpty()){
                try {
                    File file = new File("/data/data/com.termux/files/home/Akame_Omni/comando.txt");
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(ordem.getBytes());
                    fos.close();
                    status.setText("Sinal enviado à Akame!");
                    input.setText("");
                } catch (Exception e) {
                    status.setText("Erro: " + e.getMessage());
                }
            }
        });
    }
}
