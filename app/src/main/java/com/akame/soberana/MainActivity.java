package com.akame.soberana;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private WebView akameWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        akameWebView = findViewById(R.id.akameWebView);
        WebSettings webSettings = akameWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        akameWebView.setWebViewClient(new WebViewClient());
        akameWebView.loadUrl("http://127.0.0.1:5000");
    }

    @Override
    public void onBackPressed() {
        if (akameWebView.canGoBack()) {
            akameWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}