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
        
        // Akame v11.0: Exibindo a Soberania Automática
        subStatus.setText("🔱 AKAME V11.0 - AUTO-VAULT\n" + 
                         "SISTEMA: Extração Termux [ATIVA]\n" + 
                         "ESTADO: " + AkameCore.getVersion() + "\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
