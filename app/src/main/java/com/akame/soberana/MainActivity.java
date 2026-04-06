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
        
        // Confluência v9.0 - A Akame agora une os dois mundos
        subStatus.setText("🔱 AKAME OMNI V9.0\n" + 
                         AkameSynthesis.getStatusIA() + "\n" + 
                         AkameSynthesis.getStatusArte() + "\n" +
                         "MANIFESTO: " + AkameCore.getManifesto());
    }
}
