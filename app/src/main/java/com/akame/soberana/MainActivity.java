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
        subStatus.setText("🧬 GitHub: OK | 🧠 Supabase: OK\n🧠 HF: Reconfigurando... | 🎨 Civitai: Reconfigurando...");
    }
}
