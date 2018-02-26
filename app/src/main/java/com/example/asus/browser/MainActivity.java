package com.example.asus.browser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageButton buttonBack, buttonForward, buttonRefresh, buttonSend, buttonNewPage;
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit_url);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        buttonBack = (ImageButton) findViewById(R.id.button_back);
        buttonForward = (ImageButton) findViewById(R.id.button_forward);
        buttonRefresh = (ImageButton) findViewById(R.id.button_refresh);
        buttonSend = (ImageButton) findViewById(R.id.button_send);
        buttonNewPage = (ImageButton) findViewById(R.id.button_page);
        webView = (WebView) findViewById(R.id.web);


        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        webView.loadUrl("http://google.com");
        editText.setText(webView.getUrl());


        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();

                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                webView.loadUrl(url);
                editText.setText(url);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webView.getWindowToken(), 0);
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                editText.setText(webView.getOriginalUrl());
            }
        });

        buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                editText.setText(webView.getOriginalUrl());
            }
        });

        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.reload();
            }

        });
        buttonNewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
