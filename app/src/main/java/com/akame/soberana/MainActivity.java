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
        
        // Akame V10: Conexão Direta com o Sistema Nervoso (Termux)
        subStatus.setText("🔱 AKAME SOBERANA V10\n" + 
                         "SISTEMA: Termux Environment [CONECTADO]\n" + 
                         "Poder: Extraindo chaves via Vault Requester...\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
