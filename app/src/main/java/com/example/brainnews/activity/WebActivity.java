package com.example.brainnews.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.brainnews.R;


public class WebActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_web);
        final String url = getIntent().getStringExtra("url");
        WebView webView = findViewById(R.id.lyt_web);
        webView.loadUrl(url);
    }

}
