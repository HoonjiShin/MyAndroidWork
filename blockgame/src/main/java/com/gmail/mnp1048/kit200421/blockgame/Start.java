package com.gmail.mnp1048.kit200421.blockgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Start extends AppCompatActivity implements View.OnClickListener {

    TextView tvTime; //시간표시
    TextView tvPoint; //score표시

    int time = 30; //게임시간
    int point =0; //초기 점수값

    //블럭이미지 리소스 배열
    int [] img = {R.drawable.block_red,R.drawable.block_green,R.drawable.block_blue};

    //떨어지는 블럭의 imageview 배열객체
    ImageView [] iv = new ImageView[8]; //null로 초기화 상태
    private Vibrator vibrator; //진동
    private SoundPool soundPool; //음향

    int soundID_OK; //음향 id: 블럭을 맞췄을때
    int soundID_Error; //음향 id: 블럭을 못 맞췄을때

    private MediaPlayer mp; //배경음악

    final int DIALOG_TIMEOVER = 1; //다이얼로그 ID

    Handler handler = new Handler(); //시간

    //게임진행 쓰레드
    class GameThread extends Thread{
        @Override
        public void run() {
            //시간은 1초마다 다시 표시(update)
            //Handler 사용하여 화면 UI UPDATE

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(time >= 0){
                        tvTime.setText("Time: "+time);

                        if(time > 0){
                            time--; //1초 감소하고 1초후에 다시 Runnable 수행
                            handler.postDelayed(this,1000);
                        }else{
                            //time = 0 인 경우
                            AlertDialog.Builder builder =
                                    new AlertDialog.Builder(Start.this);
                            builder.setTitle("TIME OVER")
                                    .setMessage("Score: "+point)
                                    .setNegativeButton("LEAVE", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    })
                                    .setPositiveButton("AGAIN", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //RESET 후에 게임 다시하기
                                            time = 30;
                                            point = 0;
                                            tvTime.setText("Time: "+time);
                                            tvPoint.setText("Score: "+point);
                                            new GameThread().start();
                                        }
                                    })
                                    .setCancelable(false);
                            builder.show();
                        }
                    }
                }
            },1000); //1초후에 시간 표시
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        //상태바(status bar) 없애기, 반드시 setContextView() 앞에서 처리
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start);

        //레이아웃 객체(뷰) 초기화
        tvTime = findViewById(R.id.tvTime);
        tvPoint = findViewById(R.id.tvPoint);

        ImageView ivRed =findViewById(R.id.ivRed);
        ImageView ivGreen =findViewById(R.id.ivGreen);
        ImageView ivBlue =findViewById(R.id.ivBlue);

        iv[0] =findViewById(R.id.ivBlock1);
        iv[1] =findViewById(R.id.ivBlock2);
        iv[2] =findViewById(R.id.ivBlock3);
        iv[3] =findViewById(R.id.ivBlock4);
        iv[4] =findViewById(R.id.ivBlock5);
        iv[5] =findViewById(R.id.ivBlock6);
        iv[6] =findViewById(R.id.ivBlock7);
        iv[7] =findViewById(R.id.ivBlock8);

        //게임이 시작되면, 초기화, 랜덤으로 블럭의 색상 지정
        for (int i=0 ;i<iv.length;i++){
            //0,1,2 <- 빨,초,파
            int num = new Random().nextInt(3); //0,1,2 중에 랜덤 점수 뽑기.
            iv[i].setImageResource(img[num]);
            iv[i].setTag(num+""); // 태그를 버튼의 색상 판단하기 위한 값으로 활용
        }

        //점수초기화
        point =0 ;
        tvPoint.setText("Score:" +point);

        //R,G,B 버튼의 이벤트 리스너 등록
        ivRed.setOnClickListener(this); //액티비티가 implement 한경우에는 ivRed.setOnClickListener(new ~~);가 아니라 ivRed.setOnClickListener(this);
        ivGreen.setOnClickListener(this);
        ivBlue.setOnClickListener(this);

        //시간표시, 게임 진행 쓰레드 시작하기
        new GameThread().start();



    }// end onCreate()

    @Override
    public void onClick(View v) {
        //버튼을 눌렀을때 호출되는 콜백
        //블럭과 같은 색깔의 버튼이 눌렀는지 판별, 같은 블럭이면 이미지 블럭 한칸씩 내려오기, 맨위에는 새로운 블럭생성
        boolean isOK = false; //맞추었는지 판별, 결과

        ImageView imageView = (ImageView) v;

        switch (imageView.getId()){
            //맨 아래 블럭 ImageView 의 색상과 일치하는 버튼인지 판정
            case R.id.ivRed: //빨강버튼 클릭시
                if("0".equals(iv[7].getTag().toString())) isOK = true; //빨강 블럭의 tag값 "0"
                break;
            case R.id.ivGreen://초록버튼 클릭시
                if("1".equals(iv[7].getTag().toString())) isOK = true; //초록 블럭의 tag값 "1"
                break;
            case R.id.ivBlue://파랑랑버튼 클릭시
                if("2".equals(iv[7].getTag().toString())) isOK = true; //파랑 블럭의 tag값 "2"
               break;
        }

        if(isOK){ //버튼색깔을 맞췄다면?

            //위의 7개 블럭을 한칸 아래로 이동, iv[i] --> iv[i+1]
            for (int i = iv.length-2 ; i >= 0 ; i--){
                int num = Integer.parseInt(iv[i].getTag().toString()); //0,1,2
                iv[i+1].setImageResource(img[num]); //i 아래쪽 블럭 이미지 업뎃
                iv[i+1].setTag(num+""); //i 아래쪽 블럭 tag 업뎃
            } //end for 맨위에 칸만 비어있는 상태

            //가장 위에 블럭(iv[0]) ImageView 는 랜덤으로 들어오기
            int num = new Random().nextInt(3);//0,1,2
            iv[0].setImageResource(img[num]);
            iv[0].setTag(num+"");


            //진동 & 음향
            vibrator.vibrate(200);
            soundPool.play(soundID_OK,1,1,0,0,1);

            //점수 증가
            point++;
            tvPoint.setText("Score: "+point);
        }else{
            //진동 & 음향
            vibrator.vibrate(new long[] {20,80,20,80,20,80},-1);
            soundPool.play(soundID_Error,1,1,0,0,1);

            //점수 감소
            point--;
            tvPoint.setText("Score: "+point);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //자원 획득

        //vibrator객체 얻어오기
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        //soundpool 객체 얻어오기
        soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        soundID_OK = soundPool.load(Start.this,R.raw.gun3,1);
        soundID_Error = soundPool.load(Start.this,R.raw.error,1);

        mp = MediaPlayer.create(getApplicationContext(),R.raw.bgm);
        mp.setLooping(false);
        mp.start();
    }

    @Override
    protected void onPause() { //사용자의 inturrpt가 끝났을 때
        super.onPause();
        //자원 획득
        if(mp != null){
            mp.stop();
            mp.release();
        }
    }
}
