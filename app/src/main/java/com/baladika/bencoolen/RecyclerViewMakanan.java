package com.baladika.bencoolen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.juliomarcos.ImageViewPopUpHelper;

import java.util.ArrayList;

public class RecyclerViewMakanan extends RecyclerView.Adapter<RecyclerViewMakanan.RecyclerViewOlehOlehHolder> {

    private ArrayList<Adapter> listMakanan;
    AlertDialog alertDialog;

    public RecyclerViewMakanan(ArrayList<Adapter> list) {
        this.listMakanan = list;
    }

    @NonNull
    @Override
    public RecyclerViewOlehOlehHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview_oleholeh, viewGroup, false);
        return new RecyclerViewOlehOlehHolder(view);
    }

    @Override
        public void onBindViewHolder(@NonNull final RecyclerViewOlehOlehHolder holder, final int position) {

        final Adapter adapterOL = listMakanan.get(position);
        Glide.with(holder.itemView.getContext())
                .load(adapterOL.getImgMakanan())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgMakanan);

        holder.tvMakanan.setText(adapterOL.getNameMakanan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View viewMkn = inflater.inflate(R.layout.oleh_oleh_detail, null);

                alertDialog = new AlertDialog.Builder(holder.itemView.getContext())
                        .setView(viewMkn)
                        .create();
                TextView texMkn = (TextView) viewMkn.findViewById(R.id.txtOlehDetail);
                ImageView imgMkn = (ImageView) viewMkn.findViewById(R.id.imgOlehDetail);

                texMkn.setText(adapterOL.getNameMakanan());
                Glide.with(viewMkn.getContext())
                        .load(adapterOL.getImgMakanan())
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imgMkn);
                alertDialog.show();
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listMakanan.size();
    }

    public class RecyclerViewOlehOlehHolder extends RecyclerView.ViewHolder {
        ImageView imgMakanan;
        TextView tvMakanan;

        RecyclerViewOlehOlehHolder(View itemView) {
            super(itemView);
            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData

            imgMakanan = itemView.findViewById(R.id.imgOlehOleh);
            tvMakanan = itemView.findViewById(R.id.txtOlehOleh);
        }
    }
}
