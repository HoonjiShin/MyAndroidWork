package com.lec.android.a002_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear3);

        int num = 100;
        String str = "hello";
        char ch = 'a';
        short s = 200;
    }
}
