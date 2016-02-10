package com.hotelquickly.niroshan.hotelquickly;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.hotelquickly.niroshan.hotelquickly.utils.AppUtil;

/**
 * com.hotelquickly.niroshan.hotelquickly
 * <p/>
 * Created by Niroshan Rathnayake.
 */

public class WebviewActivity extends ActionBarActivity {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private Toolbar toolbar;
    private WebView webViewContent;
    private TextView notificationText;

    String URL = null;
    String CacheEnable = null;
    String PageTitle = null;

    ProgressBar progressBar;
    private static final String TAG = WebviewActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        InitialView();
        GetDataFromIntent();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(PageTitle);
        }

        LoadWebView();


    }

    private void LoadWebView() {

        if(CacheEnable.equals("true")){

            if(AppUtil.checkNetworkConnection(getApplicationContext())){

                LoadWebViewWithCache();

            } else{

                webViewContent.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK );
                LoadWebViewWithCache();
            }


        } else{

            LoadWebViewWithOutCache();

        }
    }

    private void LoadWebViewWithOutCache() {

        webViewContent.setWebViewClient(new WebClient());
        webViewContent.getSettings().setAppCacheEnabled(false);
        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl(URL);
    }

    private void LoadWebViewWithCache() {

        webViewContent.setWebViewClient(new WebClient());
        webViewContent.getSettings().setDomStorageEnabled(true);
        webViewContent.getSettings().setAppCacheEnabled(true);
        webViewContent.getSettings().setAppCacheMaxSize(1024*1024*8);
        webViewContent.getSettings().setJavaScriptEnabled(true);
        webViewContent.loadUrl(URL);

    }

    private void GetDataFromIntent() {
        URL = getIntent().getStringExtra(ARG_PARAM1);
        CacheEnable = getIntent().getStringExtra(ARG_PARAM2);
        PageTitle = getIntent().getStringExtra(ARG_PARAM3);
    }

    private void InitialView() {

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.webViewContent = (WebView) findViewById(R.id.webViewContent);
        this.notificationText = (TextView) findViewById(R.id.textView);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public class WebClient extends WebViewClient
    {
        public void onPageStarted(WebView view, String url) {
            super.onPageStarted(view, url, null);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webViewContent.canGoBack()) {
            webViewContent.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
