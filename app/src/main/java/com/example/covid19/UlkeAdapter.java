package com.example.covid19;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class UlkeAdapter extends RecyclerView.Adapter<UlkeAdapter.MyViewHolder> implements Filterable  {
   public ArrayList<Ulke> ulkeArrayList,filterList;
    Context context;
    private FilterStat filter;
    private onItemClickListener listener;


    public UlkeAdapter(ArrayList<Ulke> ulkeArrayList, Context context) {
        this.ulkeArrayList = ulkeArrayList;
        this.context = context;
        this.filterList = ulkeArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_recyclerview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ulke ulke = ulkeArrayList.get(position);
        holder.txtUlkeAdi.setText(ulke.getUlkeAdi());


        //Glide
        Glide.with(context).load(ulke.getUlkeResmi()).apply(new RequestOptions().override(240,160)).into(holder.UlkeImageView);
    }

    @Override
    public int getItemCount() {
        return ulkeArrayList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new FilterStat(this,filterList);
        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView UlkeImageView;
        private TextView txtUlkeAdi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUlkeAdi = itemView.findViewById(R.id.txtUlkeAdi);
            UlkeImageView = itemView.findViewById(R.id.ulkelerImageView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (listener != null){
                        listener.onItemClick(ulkeArrayList.get(position),position);
                    }
                }
            });
        }
    }

    public interface onItemClickListener{
        void onItemClick(Ulke ulke,int position);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }
}
