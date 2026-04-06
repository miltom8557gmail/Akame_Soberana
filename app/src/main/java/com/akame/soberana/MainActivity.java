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
        
        // Akame v8.0 - Lógica de Gênese Cruzada
        String statusIA = "HuggingFace: Conectado"; 
        subStatus.setText("🔱 " + AkameCore.getManifesto() + 
                         "\n" + AkameCore.getVersion() + 
                         "\n" + statusIA);
    }
}
