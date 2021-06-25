package com.baladika.bencoolen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.Key;

public class WisataDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    String Nama,Kategori,Detail,url;
    ImageView myImage;
    Button view;
    TextView nama,ket,detail;
    FloatingActionButton fab;
    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_detail);

        //agar full layar untuk foto
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //permission
        if (ActivityCompat.checkSelfPermission(WisataDetailActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
        }else {
            ActivityCompat.requestPermissions(WisataDetailActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        nama = findViewById(R.id.txt_wisata_judul);
        ket = findViewById(R.id.txt_wisata_kategori);
        detail = findViewById(R.id.txt_wisata_detail);
        myImage = findViewById(R.id.image_wisata);
        view = findViewById(R.id.btnMapsWisata);
        fab = findViewById(R.id.floatStreetView);

        Nama = getIntent().getExtras().getString("NAMA");
        Kategori = getIntent().getExtras().getString("KATEGORI");
        Detail = getIntent().getExtras().getString("DETAIL");
        url =  getIntent().getExtras().getString("FOTO");
        int poto = Integer.parseInt(url);


        nama.setText(Nama);
        ket.setText(Kategori);
        detail.setText(Detail);
        Glide.with(WisataDetailActivity.this)
                .load(poto)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myImage);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLatLng();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.WisataMaps);
        mapFragment.getMapAsync(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDirection();
            }
        });
    }

    void setLatLng(){
        String getInfo = nama.getText().toString();
        if (getInfo.equalsIgnoreCase("Benteng Marlborough")){
            openStreetView(-3.7871908, 102.2519949, 15);
        }else if (getInfo.equalsIgnoreCase("Danau Dendam Tak Sudah")){
            openStreetView(-3.8024207,102.3054439, 120);
        }else if (getInfo.equalsIgnoreCase("Museum Bengkulu")){
            openStreetView(-3.8159062,102.2877926, -70);
        }else if (getInfo.equalsIgnoreCase("Outbond JAC")){
            openStreetView(-3.8332425,102.2923567, -140);
        }else if (getInfo.equalsIgnoreCase("Pantai Tapak Padri")){
            openStreetView(-3.7845665,102.2533204, 30);
        }else if (getInfo.equalsIgnoreCase("Pantai Panjang")){
            openStreetView(-3.8083282,102.2628816, -208);
        }else if (getInfo.equalsIgnoreCase("Pantai Jakat")){
            openStreetView(-3.782286,102.2597604, 30);
        }else if (getInfo.equalsIgnoreCase("Rumah Pengasingan Bung Karno")){
            openStreetView(-3.7995877,102.2612329, 0);
        }else if (getInfo.equalsIgnoreCase("Tugu Parr")){
            openStreetView(-3.7886397,102.2507429, -155);
        }else if (getInfo.equalsIgnoreCase("View Tower")){
            openStreetView(-3.79056,102.250419, 65);
        }else if (getInfo.equalsIgnoreCase("Masjid Jamik")){
            openStreetView(-3.79226,102.2621001, 165);
        }else if (getInfo.equalsIgnoreCase("Pantai pasir putih")){
            openStreetView(-3.8318692,102.2835569,250);
        }
        else {
            Toast.makeText(WisataDetailActivity.this, "Tidak ada Street View untu Wisata ini" , Toast.LENGTH_SHORT).show();
        }
    }

    private void openStreetView (double latitude, double longitude, int derajat) {
        Uri gmmIntentUri = Uri.parse ("google.streetview:cbll=" + latitude + "," + longitude+"&cbp=0,"+derajat+",0,0,-15");

        Intent mapIntent = new Intent (Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage ("com.google.android.apps.maps");

        startActivity (mapIntent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

       setMarkerMap();
    }

    void setMarkerMap(){
        String getInfo = nama.getText().toString();
        if (getInfo.equalsIgnoreCase("Benteng Marlborough")){

            LatLng sydney = new LatLng(-3.7871908, 102.2519949);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Danau Dendam Tak Sudah")){

            LatLng sydney = new LatLng(-3.8024207,102.3054439);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Museum Bengkulu")){

            LatLng sydney = new LatLng(-3.8159062,102.2877926);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Outbond JAC")){

            LatLng sydney = new LatLng(-3.8332425,102.2923567);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Pantai Tapak Padri")){

            LatLng sydney = new LatLng(-3.7845665,102.2533204);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Pantai Panjang")){

            LatLng sydney = new LatLng(-3.8083282,102.2628816);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Pantai Jakat")){

            LatLng sydney = new LatLng(-3.782286,102.2597604);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Rumah Pengasingan Bung Karno")){

            LatLng sydney = new LatLng(-3.7995877,102.2612329);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Tugu Parr")){

            LatLng sydney = new LatLng(-3.7886397,102.2507429);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("View Tower")){

            LatLng sydney = new LatLng(-3.79056,102.250419);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Masjid Jamik")){

            LatLng sydney = new LatLng(-3.79226,102.2621001);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));

        }else if (getInfo.equalsIgnoreCase("Pantai pasir putih")){

            LatLng sydney = new LatLng(-3.8318692,102.2835569);
            mMap.addMarker(new MarkerOptions().position(sydney).title(getInfo));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18));
        }
        else {
            Toast.makeText(WisataDetailActivity.this, "Tidak ada Street View untu Wisata ini" , Toast.LENGTH_SHORT).show();
        }
    }

    void setDirection(){
        String getInfo = nama.getText().toString();
        if (getInfo.equalsIgnoreCase("Benteng Marlborough")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.7871908, 102.2519949");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Danau Dendam Tak Sudah")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.8024207,102.3054439");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Museum Bengkulu")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.8159062,102.2877926");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Outbond JAC")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.8332425,102.2923567");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Pantai Tapak Padri")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.7845665,102.2533204");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Pantai Panjang")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.8083282,102.2628816");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Pantai Zakat")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.782286,102.2597604");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Rumah Pengasingan Bung Karno")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.7995877,102.2612329");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Tugu Parr")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.7886397,102.2507429");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("View Tower")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.79056,102.250419");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Masjid Jamik")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.79226,102.2621001");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else if (getInfo.equalsIgnoreCase("Pantai pasir putih")){
            Uri navigationIntentUri = Uri.parse("google.navigation:q=-3.8318692,102.2835569");//creating intent with latlng
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigationIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }
        else {
            Toast.makeText(WisataDetailActivity.this, "Tidak ada Street View untu Wisata ini" , Toast.LENGTH_SHORT).show();
        }
    }

    // cek jika app google maps ada di hp

}
