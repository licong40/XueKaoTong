package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;

public class SkipUrl extends AppCompatActivity {
    private WebView wView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_coupon);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ((TextView) findViewById(R.id.title)).setText(title);
        String url = intent.getStringExtra("url");
        wView = ((WebView) findViewById(R.id.webview));
        wView.loadUrl(url);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
