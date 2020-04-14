package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etPassword, etNumber, etEmail;
    TextView tvName, tvPassword, tvNumber, tvEmail;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        etNumber = findViewById(R.id.etNumber);
        etEmail = findViewById(R.id.etEmail);

        tvName = findViewById(R.id.tvName);
        tvPassword = findViewById(R.id.tvPassword);
        tvNumber = findViewById(R.id.tvNumber);
        tvEmail = findViewById(R.id.tvEmail);

        tvResult = findViewById(R.id.tvResult);

        //포커스 변화
        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //hasFoucus: true(포커스 받은 경우) or false(포커스 잃은 경우)
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    ((EditText)v).setBackgroundColor(Color.YELLOW);
                }else{
                    //투명색으로 바꾸기
                    ((EditText)v).setBackgroundColor(Color.parseColor("#00000000"));
                }
            }
        });

        etPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //keyCode : 눌린키의 코드값
                tvResult.setText(((EditText)v).getText().toString());
                return false;
            }
        });

        //값의 변화 (입력 완료)
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                tvResult.setText("입력완료: " + actionId);
                return false;
            }
        });
    }
}
