package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    //1.등장인물 나열
    CheckBox cb1,cb2,cb3,cb4;
    TextView tvResult;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //초기화 할 때
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

//        //2.버튼 꺼내오기
//        cb1 = findViewById(R.id.cb1);
//        cb2 = findViewById(R.id.cb2);
//        cb3 = findViewById(R.id.cb3);
//        cb4 = findViewById(R.id.cb4);
//        tvResult = findViewById(R.id.tvResult);
//        btnComplete = findViewById(R.id.btnComplete);
        //====================================================
        //버튼이 클릭되면 체크된 내용만 결과 출력하기
        //3. 버튼이 클릭되면~
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = "";
                if(cb1.isChecked()){result += cb1.getText(); }
                if(cb2.isChecked()){result += cb2.getText(); }
                if(cb3.isChecked()){result += cb3.getText(); }
                if(cb4.isChecked()){result += cb4.getText(); }
                tvResult.setText("선택결과: "+result+" ");
            }
        });

        cb1.setOnCheckedChangeListener(new CbListener());
        cb2.setOnCheckedChangeListener(new CbListener());
        cb3.setOnCheckedChangeListener(new CbListener());
        cb4.setOnCheckedChangeListener(new CbListener());



    }
    //상태값에 따라 바로바로 결과출력
    class CbListener implements CompoundButton.OnCheckedChangeListener{

        //checkbox의 '상태'가 변할때마다 호출되는 메소드
        //isChecked-> true : check 상태 , ->false : uncheck 상태
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //check여부에 따라 count수 증가
            int count = 0;
            if(cb1.isChecked()) count++;
            if(cb2.isChecked()) count++;
            if(cb3.isChecked()) count++;
            if(cb4.isChecked()) count++;
            tvResult.setText(count + "개 선택");
        }
    }
}
