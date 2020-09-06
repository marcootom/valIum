package com.example.valium;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

<<<<<<< HEAD
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;
    public RecyclerViewAdapter (ArrayList<String> myValues){
        this.myValues= myValues;
=======
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public ArrayList<String> myValues;

    public RecyclerViewAdapter(ArrayList<String> myValues) {
        this.myValues = myValues;
>>>>>>> 59767ea69a67938f05989329833268bc4451e26c
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem;
        listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.myTextView.setText(myValues.get(position));
    }


    @Override
    public int getItemCount() {
        return myValues.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView myTextView;
<<<<<<< HEAD
        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView)itemView.findViewById(R.id.text_cardview);
=======

        public MyViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.text_cardview);
>>>>>>> 59767ea69a67938f05989329833268bc4451e26c
        }
    }
}
