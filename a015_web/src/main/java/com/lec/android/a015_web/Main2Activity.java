package com.lec.android.a015_web;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

// #1 WebView 사용하여 웹 페이지 보여주기
// #2 묵시적 Intent 사용하여 웹 브라우져 띄우기
public class Main2Activity extends AppCompatActivity {

    WebView wv;
    EditText etUrl;
    Button btnWebView, btnBrowser;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etUrl = findViewById(R.id.etUrl);
        wv = findViewById(R.id.wv);
        btnWebView = findViewById(R.id.btnWebView);
        btnBrowser = findViewById(R.id.btnBrowser);

        //webView setting
        wv.getSettings().setJavaScriptEnabled(true); //javascript 사용 여부

        btnWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim();
                wv.loadUrl(url); // webpage url 읽어오기
                wv.setWebChromeClient(new WebChromeClient()); //안하면 자바스크립트의 popup 창 안뜸
                wv.setWebViewClient(new WebViewClient()); //안하면 웹페이지 내에서 다른 페이지로 이동이 불가함.

            }
        });

        //browser 가동 , 묵시적 intent 사용
        btnBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = etUrl.getText().toString().trim(); //입력된 url 가져오기
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url)); // Intent intent = new Intent(Intent.ACTION_VIEW, something you want); ex)주소록 ,전화걸기,사진 보기 등등
                startActivity(intent);
            }
        });
    }

    //이전화면 구현
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()){
            wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}