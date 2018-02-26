package com.example.asus.browser;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by asus on 28.01.2018.
 */

public class MyWebClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
