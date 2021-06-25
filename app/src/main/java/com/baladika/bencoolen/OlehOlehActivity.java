package com.baladika.bencoolen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class OlehOlehActivity extends AppCompatActivity {

    private ArrayList<Adapter> listMkn = new ArrayList<>();
    private ArrayList<Adapter> listBrg = new ArrayList<>();
    private ArrayList<Adapter> listBdy = new ArrayList<>();
    RecyclerView recyclerViewMkn;
    RecyclerView recyclerViewBrg;
    RecyclerView recyclerViewBdy;
    RecyclerViewMakanan recyclerViewMakanan;
    RecyclerViewBarang recyclerViewBarang;
    RecyclerViewBudaya recyclerViewBudaya;
    OnSwipeTouchListener onSwipeTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oleholeh);

        changeColor(R.color.hijauCerah);


        onSwipeTouchListener = new OnSwipeTouchListener(OlehOlehActivity.this, findViewById(R.id.activityOlehOleh));

        recyclerViewMkn = findViewById(R.id.recyclerview_makanan);
        recyclerViewBrg = findViewById(R.id.recyclerview_barang);
        recyclerViewBdy = findViewById(R.id.recyclerview_budaya);


        recyclerViewMkn.setHasFixedSize(true);
        recyclerViewBrg.setHasFixedSize(true);
        recyclerViewBdy.setHasFixedSize(true);

        listMkn.addAll(Data.getListDataMkn());
        listBrg.addAll(Data.getListDataBrg());
        listBdy.addAll(Data.getListBdy());
        showRecyclerCardView();
    }

    private void showRecyclerCardView(){
        recyclerViewMkn.setLayoutManager(new LinearLayoutManager(
                OlehOlehActivity.this, LinearLayoutManager.HORIZONTAL, false
        ));
        recyclerViewMakanan = new RecyclerViewMakanan(listMkn);
        recyclerViewMkn.setAdapter(recyclerViewMakanan);

        recyclerViewBrg.setLayoutManager(new LinearLayoutManager(
                OlehOlehActivity.this, LinearLayoutManager.HORIZONTAL, false
        ));
        recyclerViewBarang = new RecyclerViewBarang(listBrg);
        recyclerViewBrg.setAdapter(recyclerViewBarang);

        recyclerViewBdy.setLayoutManager(new LinearLayoutManager(OlehOlehActivity.this
        ));
        recyclerViewBudaya = new RecyclerViewBudaya(listBdy);
        recyclerViewBdy.setAdapter(recyclerViewBudaya);
    }

    public static class OnSwipeTouchListener implements View.OnTouchListener {
        private final GestureDetector gestureDetector;
        Context context;
        OnSwipeTouchListener(Context ctx, View mainView) {
            gestureDetector = new GestureDetector(ctx, new OnSwipeTouchListener.GestureListener());
            mainView.setOnTouchListener(this);
            context = ctx;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
        public class GestureListener extends
                GestureDetector.SimpleOnGestureListener {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }
        void onSwipeLeft() {
            Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
            context.startActivity(intent);
            ((Activity) context).finish();
            ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    //merubah warna status bar
    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,resourseColor));
        }
    }
}
