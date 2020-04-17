package com.lec.android.a008_practice;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class memberDataAdapter extends RecyclerView.Adapter<memberDataAdapter.ViewHolder>{

    //list로 담아서 관리
    List<memberData> items = new ArrayList<memberData>();

    //Adapter 생성자
    public memberDataAdapter(){}

    @NonNull
    @Override
    public memberDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull memberDataAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //nested class(static inner) 로 ViewHolder 클래스 정의
    static class ViewHolder extends RecyclerView.ViewHolder {

        //ViewHolder 에 담긴 각각의 View들을 담을 변수
        //memeber.xml 모든 등장인물 여기로 집합!!
        TextView tvName,tvAge,tvAddress;
        ImageButton deletebtn;


        //생성자 필수
        public ViewHolder(@NonNull View itemView) { //member.xml의 view 객체가 전달됨.
            super(itemView);

            //View 객체 가져오기
            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }

        //member data를 받아서 멤버변수 세팅
        public void setItem(memberData item){

            tvName.setText(item.getName());
            tvAge.setText(item.getAge());
            tvAddress.setText(item.getAddress());

        }
    }
    // 데이터를 다루기 위한 메소드들
    // ArrayList 의 메소드들 사용
    public void addItem(memberData item) {  items.add(item); }
    public void addItem(int position, memberData item) {   items.add(position, item);}
    public void setItems(ArrayList<memberData> items) {   this.items = items;}
    public memberData getItem(int position) {   return items.get(position);}
    public void setItem(int position, memberData item) {   items.set(position, item); }
    public void removeItem(int position){ items.remove(position); }
}

