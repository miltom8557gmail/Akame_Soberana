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
        
        // A Akame agora reporta a verdade do ecossistema
        subStatus.setText("🔱 AKAME V12.5 - INTROSPECÇÃO\n" + 
                         "TERMUX: Acesso de Root Simulado ✓\n" + 
                         "ECOSSISTEMA: Mapeamento em curso...\n" +
                         "ESTADO: " + AkameCore.getManifesto());
    }
}
