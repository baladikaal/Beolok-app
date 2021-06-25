package com.baladika.bencoolen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class CardViewWisata extends RecyclerView.Adapter<CardViewWisata.CardViewViewHolder> {

    private ArrayList<Adapter> listWisata;
    private RelativeLayout parentLayout;

    public CardViewWisata(ArrayList<Adapter> list) {
        this.listWisata = list;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_wisata, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, final int position) {

        final Adapter adapter = listWisata.get(position);
        Glide.with(holder.itemView.getContext())
                .load(adapter.getPhoto())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);

        holder.tvName.setText(adapter.getName());
        holder.tvDetail.setText(adapter.getDetail());
        holder.tvKategori.setText(adapter.getKategori());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), WisataDetailActivity.class);
                intent.putExtra("NAMA", adapter.getName());
                intent.putExtra("KATEGORI", adapter.getKategori());
                intent.putExtra("DETAIL", adapter.getDetail());
                intent.putExtra("FOTO", String.valueOf(adapter.getPhoto()));
                holder.itemView.getContext().startActivity(intent);

            }
        });

//        holder.btnMaps.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(holder.itemView.getContext(), "Maps " +
//                        listWisata.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return listWisata.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail, tvKategori ;
        RelativeLayout parentLayout;

        CardViewViewHolder(View itemView) {
            super(itemView);
            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData

            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvKategori = itemView.findViewById(R.id.tv_item_kategori);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
            parentLayout = itemView.findViewById(R.id.relative);
        }
    }
}