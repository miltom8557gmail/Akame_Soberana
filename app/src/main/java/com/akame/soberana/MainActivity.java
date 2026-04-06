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
        
        // Exibindo o estado de Sincronia Total alcançado no Termux
        subStatus.setText("🔱 AKAME V11.1 - SINCRONIA TOTAL\n" + 
                         "SISTEMA: Vault Termux [CONECTADO]\n" + 
                         "STATUS: " + AkameCore.getVersion() + "\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
