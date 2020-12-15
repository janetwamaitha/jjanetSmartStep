package com.example.smartstep;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.mapboxsdk.Mapbox;

import io.mapwize.mapwizesdk.core.MapwizeConfiguration;
import io.mapwize.mapwizesdk.map.MapwizeMap;
import io.mapwize.mapwizesdk.map.MapwizeView;

public class Location extends AppCompatActivity {
    MapwizeView mapwizeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        MapwizeConfiguration config = new MapwizeConfiguration.Builder(this, "d3423a537f3ad09c").build();
        MapwizeConfiguration.start(config);

        Mapbox.getInstance(this, "pk.eyJ1IjoiamFuZXQtd2FtYWl0aGEiLCJhIjoiY2tpb291bWxlMDVjdDJzcGE5eDViZGFkcSJ9.s7X63VLvTCkXeSD71AlFjA");

        mapwizeView = findViewById(R.id.mapwizeView);
        mapwizeView.onCreate(savedInstanceState);
        mapwizeView.getMapAsync(new MapwizeView.OnMapwizeReadyCallback() {
            @Override
            public void onMapwizeReady(MapwizeMap mapwizeMap) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mapwizeView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapwizeView.onResume();
    }

    @Override
    public void onPause() {
        mapwizeView.onPause();
        super.onPause();
    }

    @Override
    public void onStop() {
        mapwizeView.onStop();
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        mapwizeView.onSaveInstanceState(saveInstanceState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapwizeView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        mapwizeView.onDestroy();
        super.onDestroy();
    }

}


