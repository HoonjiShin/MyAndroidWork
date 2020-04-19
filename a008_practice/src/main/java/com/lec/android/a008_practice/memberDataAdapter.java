package com.lec.android.a008_practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class memberDataAdapter extends RecyclerView.Adapter<memberDataAdapter.ViewHolder>{

    List<memberData> items = new ArrayList<memberData>();

    static memberDataAdapter adapter;
    public memberDataAdapter(){this.adapter = this;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inf = LayoutInflater.from(parent.getContext());
        View itemView = inf.inflate(R.layout.member,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        memberData item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName,tvAge,tvAddress;
        ImageButton btnDelItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);

            btnDelItem=itemView.findViewById(R.id.deletebtn);

            btnDelItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.removeItem(getAdapterPosition());
                    adapter.notifyDataSetChanged();
                }
            });
        }

        public void setItem(memberData item) {
            tvName.setText("이름 : " + item.getName());
            tvAge.setText("나이 : " + item.getAge());
            tvAddress.setText("주소 : " + item.getAddress());
        }
    }

    public void addItem(memberData item) {  items.add(item); }
    public void removeItem(int position){ items.remove(position); }

}