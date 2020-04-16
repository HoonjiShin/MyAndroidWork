package com.lec.android.a004_widget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

//라디오버튼은 라디오 그룹안에 만들어야한다.
//라디오 그룹에 리스너가 붙는다 (checkbox와의 차이점)
public class Main4Activity extends AppCompatActivity {
//1.등장인물 나열
    RadioGroup rg;
    Button btnResult;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //체크박스와는 달리 radiobutton은 각각 선언하는 것이 아니라
        //radiogroup으로 선언하여 단체로 사용
        //리펙터링(ALT+F6)
        rg = findViewById(R.id.rg);
        btnResult =findViewById(R.id.btnResult);
        tvResult = findViewById(R.id.tvResult);

        //선택완료 버튼을 누르면
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //선택된 radiobutton의 id 값 가져오기!!
                int id =rg.getCheckedRadioButtonId(); //라디오버튼에 있는 RadioButtonId get 하기

                //위 id값을 통해 radiobutton 객체 가져오기!!
                RadioButton rb = findViewById(id);

                tvResult.setText("결과: "+rb.getText());
            }
        });

        //라디오 버튼을 선택했을 시에도 바로 결과 보여주기
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { //int checkedId 선택된 라디오버튼의 id
                RadioButton rb = findViewById(checkedId);
                tvResult.setText("결과: "+rb.getText());
            }
        });
    }
}
