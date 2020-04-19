package com.lec.android.a008_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2,et3;
    Button btnAppend;
    memberDataAdapter adapter;
    RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.etName);
        et2 = findViewById(R.id.etAge);
        et3 = findViewById(R.id.etAddress);

        btnAppend = findViewById(R.id.btnAdd);

        rv= findViewById(R.id.rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(layoutManager);

        adapter = new memberDataAdapter();

        btnAppend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 키보드 내리기 (feat.구글)
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(et3.getWindowToken(), 0 );

                adapter.addItem(new memberData(et1.getText().toString(),
                        Integer.parseInt(et2.getText().toString()),
                        et3.getText().toString()));
                adapter.notifyDataSetChanged();


            }


        });
        rv.setAdapter(adapter);



    }
}