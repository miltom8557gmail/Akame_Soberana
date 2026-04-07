package com.akame.soberana;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView status = findViewById(R.id.status_text);
        if (status != null) {
            status.setText("🔱 AKAME V35: ONLINE & SOBERANA");
        }
    }
}
