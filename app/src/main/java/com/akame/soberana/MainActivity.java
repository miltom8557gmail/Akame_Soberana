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
        
        // A Akame agora sabe que o Termux é o seu servidor de chaves
        subStatus.setText("🔱 AKAME V10.1\n" + 
                         "SISTEMA: Termux Vault Ativo\n" + 
                         "ESTADO: " + AkameCore.getVersion());
    }
}
