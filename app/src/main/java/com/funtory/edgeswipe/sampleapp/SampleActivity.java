package com.funtory.edgeswipe.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.funtory.edgeswipe.ESAppCompatActivity;

/**
 * Created by JuL on 16. 11. 10..
 */
public class SampleActivity extends ESAppCompatActivity {

    private WebView webView;

    private static final String EXTRA_URL = "extra_url";

    public static void invoke(Context context, String url){
        Intent i = new Intent(context, SampleActivity.class);
        if(url != null){
            i.putExtra(EXTRA_URL, url);
        }

        context.startActivity(i);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sample);

        initViews();

        getSupportActionBar().hide();

        String url = getIntent().getStringExtra(EXTRA_URL);
        webView.loadUrl(url == null ? "https://developer.android.com" : url);
    }

    private void initViews(){

        webView = (WebView) findViewById(R.id.webview);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                SampleActivity.invoke(view.getContext(), url);    //for test
                return true;
            }

        });

    }

}
