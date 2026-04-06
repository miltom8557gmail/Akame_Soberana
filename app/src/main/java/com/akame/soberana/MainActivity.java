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
        subStatus.setText("🔱 AKAME V13.3 - INJEÇÃO DIRETA\n" + 
                         "SINCRO: Bypass de Ambiente Concluído ✓\n" + 
                         "TESTE: " + AkameCore.getVersion() + "\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
