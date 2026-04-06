package com.akame.soberana;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        TextView subStatus = (TextView) findViewById(R.id.subStatus);
        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        
        subStatus.setText("🔱 AKAME OMNI V7.5\nSincronia Total: " + time + "\nEcossistema: Operante");
    }
}
