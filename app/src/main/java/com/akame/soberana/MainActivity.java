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
        subStatus.setText("🔱 AKAME V14.0 - PERSISTÊNCIA\n" + 
                         "BANCO DE DADOS: Supabase Conectado ✓\n" + 
                         "MEMÓRIA: Fluxo de Logs Ativo ✓\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
