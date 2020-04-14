package com.lec.android.a004_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    EditText et;

    //OnCreate()
    //액티비티(화면 객체)가 생성될때 호출되는 메소드
    //액티비티 초기화 하는 코드 작성.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);

        tvResult = findViewById(R.id.tvResult);
        et = findViewById(R.id.et);
        final LinearLayout ll = findViewById(R.id.ll);

        //방법2 : 익명클래스 사용.
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //클릭되었을 때 호출되는 메소드.
                Log.d("myapp","Button2 was clicked");
                tvResult.setText("Button2 was clicked");
                tvResult.setBackgroundColor(Color.YELLOW);
            }
        });

        //다양한 이벤트, 다양한 LISTENTER 등록 가능
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("myapp","Button2 was long-clicked");
                tvResult.setText("Button2 was long-clicked");
                tvResult.setBackgroundColor(Color.CYAN);
                // return false; //false 리턴하면 이벤트가 click까지 간다.
                return true; // true 리턴하면 이벤트가 long click 으로 소멸
            }
        });

        //방법3 : Lambda = expression 사용하기
        btn3.setOnClickListener((v)->{
            Log.d("myapp","Button3 was clicked");
            tvResult.setText("Button3 was clicked");
            ll.setBackgroundColor(Color.rgb(164,198,57));
        });

        //방법4 : implement 한 클래스 사용
        Button btnA = findViewById(R.id.btnA);
        Button btnB = findViewById(R.id.btnB);
        Button btnC = findViewById(R.id.btnC);
        Button btnD = findViewById(R.id.btnD);
        Button btnE = findViewById(R.id.btnE);
        Button btnF = findViewById(R.id.btnF);

        Button btnInc = findViewById(R.id.btnInc);
        Button btnDec = findViewById(R.id.btnDec);

       btnInc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               float Currnet = tvResult.getTextSize();
               Log.d("myapp","SIZE"+Currnet);
               tvResult.setTextSize(0,Currnet+1);
           }
       });

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float Currnet = tvResult.getTextSize();
                Log.d("myapp","SIZE"+Currnet);
                tvResult.setTextSize(0,Currnet-1);
            }
        });


        class MyListener implements View.OnClickListener{

            String name;
            public MyListener(String name){this.name =name;}
            @Override
            public void onClick(View v) {
                String tag = (String)v.getTag();
                String text = (String)((Button)v).getText();// getText() 는 charSequence 객체리턴
                String msg = String.format("%s button % is click[%s]",name,text,tag);
                Log.d("myapp",msg);
                tvResult.setText(msg);
                et.setText(et.getText().append(name));
            }
        }
        btnA.setOnClickListener(new MyListener("hello1"));
        btnB.setOnClickListener(new MyListener("hello2"));
        btnC.setOnClickListener(new MyListener("hello3"));
        btnD.setOnClickListener(new MyListener("hello4"));
        btnE.setOnClickListener(new MyListener("hello5"));
        btnF.setOnClickListener(new MyListener("hello6"));

        //방법 5 : 액티비티가 implement
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        //연습
        //+,- 버튼 누르면 tvResult 의 글씨가 점점 커지고 /작아지게 하기
        //getTextSize():float값리턴

    }

    //방법1: 레이아웃 xml 의 oncXXXX 속성으로 지정
    public void changeText(View v){
        Log.d("myapp","Button1 was Clicked");
        tvResult.setText("Button1 was Clicked");
    }

    //방법 5 : 액티비티가 implement
//    @Override
//    public void onClick(View v) {
//        Log.d("myapp","clear button was clicked");
//        tvResult.setText("clear button was clicked");
//        et.setText("");
//
//    }
    @Override
    public void onClick(View v) {

    }

}
