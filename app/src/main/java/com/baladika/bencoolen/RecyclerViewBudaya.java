package com.baladika.bencoolen;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class RecyclerViewBudaya extends RecyclerView.Adapter<RecyclerViewBudaya.CardViewBudayaHolder> {

    private ArrayList<Adapter> listBudaya;
    AlertDialog alertDialog;

    public RecyclerViewBudaya(ArrayList<Adapter> list) {
        this.listBudaya = list;
    }


    @NonNull
    @Override
    public CardViewBudayaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_budaya, viewGroup, false);
        return new CardViewBudayaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewBudayaHolder holder, final int position) {

        final Adapter adapterBdy = listBudaya.get(position);
        Glide.with(holder.itemView.getContext())
                .load(adapterBdy.getImgBudaya())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgBudaya);

        holder.tvBudaya.setText(adapterBdy.getNamaBudaya());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View viewBdy = inflater.inflate(R.layout.budaya_detail, null);

                alertDialog = new AlertDialog.Builder(holder.itemView.getContext())
                        .setView(viewBdy)
                        .create();
                TextView detBdy = (TextView) viewBdy.findViewById(R.id.txtBudayaDetail);
                TextView texBdy = (TextView) viewBdy.findViewById(R.id.judulBdy);
                // untuk video
                VideoView videoView = (VideoView) viewBdy.findViewById(R.id.video_bdy);

                String videoPath ="";
                if (adapterBdy.getNamaBudaya().toString().equalsIgnoreCase("Festival Tabot Bengkulu")){
                    videoPath = "android.resource://"
                            + viewBdy.getContext().getPackageName() +"/"
                            + R.raw.tabot_final;
                    detBdy.setText(adapterBdy.getDetailBudaya());
                }else {
                    videoPath = "android.resource://"
                            + viewBdy.getContext().getPackageName() +"/"
                            + R.raw.pakaian_adat;
                    detBdy.setText(adapterBdy.getDetailBudaya());
                }
                Uri uri = Uri.parse(videoPath);
                videoView.setVideoURI(uri);

                MediaController mediaController = new MediaController(videoView.getContext());
                mediaController.setMediaPlayer(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();

                texBdy.setText(adapterBdy.getNamaBudaya());
                alertDialog.show();
                alertDialog.setCancelable(true);
                alertDialog.setCanceledOnTouchOutside(true);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listBudaya.size();
    }

    public class CardViewBudayaHolder extends RecyclerView.ViewHolder {
        ImageView imgBudaya;
        TextView tvBudaya;

        CardViewBudayaHolder(View itemView) {
            super(itemView);
            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData

            imgBudaya = itemView.findViewById(R.id.img_photo_budaya);
            tvBudaya = itemView.findViewById(R.id.tv_name_budaya);


        }
    }
}
