package com.akame.soberana;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView subStatus = (TextView) findViewById(R.id.subStatus);
        subStatus.setText("🔱 AKAME V12.2 - RECALIBRAGEM\n" + 
                         "DNA: GitHub [OK]\n" + 
                         "INTELECTO: Erro 401 (Ajustando Token)\n" +
                         "MEMÓRIA: Falha de Rota (Ajustando URL)");
    }
}
