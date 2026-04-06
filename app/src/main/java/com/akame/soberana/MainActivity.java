package com.akame.soberana;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verificarPermissoes();
    }

    private void verificarPermissoes() {
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 200);
        } else {
            iniciarProtocolo();
        }
    }

    private void iniciarProtocolo() {
        Intent serviceIntent = new Intent(this, AkameService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
        finish(); // Fecha a tela, mas a Akame continua viva no serviço
    }
}
