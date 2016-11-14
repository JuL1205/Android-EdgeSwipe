package com.funtory.edgeswipe.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.funtory.edgeswipe.EdgeSwipeDelegate;

/**
 * Created by JuL on 16. 11. 10..
 */
public class SampleActivity2 extends AppCompatActivity {
    private WebView webView;

    private EdgeSwipeDelegate edgeSwipeDelegate = new EdgeSwipeDelegate();

    private static final String EXTRA_URL = "extra_url";

    private ActionMode actionMode;

    public static void invoke(Context context, String url){
        Intent i = new Intent(context, SampleActivity2.class);
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
                SampleActivity2.invoke(view.getContext(), url);    //for test
                return true;
            }

        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(edgeSwipeDelegate.delegate(this, ev)){
            return true;
        } else{
            return super.dispatchTouchEvent(ev);
        }
    }

    /**
     * Store action mode if it is started
     * @param mode
     */
    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        actionMode = mode;
    }

    /**
     * When activity is paused, make sure action mode is ended properly.
     * This check would feel better to have in onDestroy(), but that seems to be
     * too late down the life cycle and the crash keeps on occurring. The drawback
     * of this solution is that the action mode is finished when app is minimized etc.
     */
    @Override
    protected void onPause() {
        super.onPause();
        endActionMode();
    }

    /**
     * Makes sure action mode is ended
     */
    private void endActionMode() {
        if (actionMode != null) {
            actionMode.finish(); /** immediately calls {@link #onActionModeFinished(ActionMode)} */
        }
    }

    /**
     * Clear action mode every time it finishes.
     * @param mode
     */
    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        actionMode = null;
    }
}
