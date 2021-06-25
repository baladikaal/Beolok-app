package com.baladika.bencoolen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.juliomarcos.ImageViewPopUpHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerViewBarang extends RecyclerView.Adapter<RecyclerViewBarang.RecyclerViewBarangHolder> {

    private ArrayList<Adapter> listBarang;
    AlertDialog alertDialog;

    public RecyclerViewBarang(ArrayList<Adapter> list) {
        this.listBarang = list;
    }


    @NonNull
    @Override
    public RecyclerViewBarangHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview_oleholeh, viewGroup, false);
        return new RecyclerViewBarangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewBarangHolder holder, final int position) {

        final Adapter adapterOL = listBarang.get(position);
        Glide.with(holder.itemView.getContext())
                .load(adapterOL.getImgBarang())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgBarang);

        holder.tvBarang.setText(adapterOL.getNameBarang());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View viewBrg = inflater.inflate(R.layout.oleh_oleh_detail, null);

                alertDialog = new AlertDialog.Builder(holder.itemView.getContext())
                        .setView(viewBrg)
                        .create();
                TextView tex = (TextView) viewBrg.findViewById(R.id.txtOlehDetail);
                ImageView img = (ImageView) viewBrg.findViewById(R.id.imgOlehDetail);

                tex.setText(adapterOL.getNameBarang());
                Glide.with(viewBrg.getContext())
                        .load(adapterOL.getImgBarang())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(img);
                alertDialog.show();
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class RecyclerViewBarangHolder extends RecyclerView.ViewHolder {
        ImageView imgBarang;
        TextView tvBarang;

        RecyclerViewBarangHolder(View itemView) {
            super(itemView);
            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData

            imgBarang = itemView.findViewById(R.id.imgOlehOleh);
            tvBarang = itemView.findViewById(R.id.txtOlehOleh);
        }
    }
}
