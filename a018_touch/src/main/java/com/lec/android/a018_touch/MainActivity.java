package com.lec.android.a018_touch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvResult;
    Button btnTouch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btnTouch = findViewById(R.id.btnTouch);

        btnTouch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tvResult.setText("Action_down 실행");
                        break;
                    case  MotionEvent.ACTION_MOVE:
                        tvResult.setText("Action_move 실행");
                        break;
                    case MotionEvent.ACTION_UP:
                        tvResult.setText("ACTION_UP 실행");
                        break;
                }

                //이벤트 처리를 여기서 완료하고
                //다른곳에 이벤트를 넘기지 않도록
                //리턴값을 true fh wnsek.
                return false;
            }
        });

    }
}
