package com.example.th2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.th2.R;
import com.example.th2.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> list;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<Item> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public Item getItem(int position){
        return list.get(position);
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item=list.get(position);
        holder.sach.setText(item.getSach());
        holder.tomtat.setText(item.getTomtat());
        holder.tacgia.setText(item.getTacgia());
        holder.nxb.setText(item.getNxb());

        holder.favourite.setText(item.getFavourite());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView sach,tomtat,tacgia,nxb,favourite;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            sach=view.findViewById(R.id.tvSach);
            tomtat=view.findViewById(R.id.tvTomtat);
            tacgia=view.findViewById(R.id.tvTacGia);
            nxb=view.findViewById(R.id.tvNxb);
            favourite=view.findViewById(R.id.tvFavourite);
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(itemListener!=null){
                itemListener.onItemClick(view,getAdapterPosition());
            }

        }
    }
    public interface ItemListener{
        void onItemClick(View view,int position);

    }

}