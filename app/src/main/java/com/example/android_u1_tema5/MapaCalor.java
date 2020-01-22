package com.example.android_u1_tema5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.HeatmapTileProvider;

import java.util.ArrayList;
import java.util.List;

public class MapaCalor extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

  TileOverlay mOverlay;


  GoogleMap mapa; List<Marker> markerList = new ArrayList<>(); int id = 0;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mapa_calor);
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    mapa = googleMap;
    mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.013766, -70.255331), 15));
    mapa.setOnMapClickListener(this);
    mapa.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
      @Override
      public boolean onMarkerClick(Marker marker) {
        for (Marker mimarker : markerList)
          if (mimarker.equals(marker)) {
            markerList.remove(mimarker);
            break;
          }
        marker.remove();
        return true;
      }
    });
//desde un punto se desaparecee las marcas
    mapa.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
      @Override
      public void onCameraIdle() {
        CameraPosition position = mapa.getCameraPosition();
        float zoom = position.zoom;
        for(Marker marker:markerList)
          marker.setVisible(zoom>=12);
      }
    });
  }
  @Override
  public void onMapClick(LatLng latLng) {
    Marker Mymarker = mapa.addMarker(new MarkerOptions()
        .position(latLng)
        .title(String.valueOf(id))
    );
    markerList.add(Mymarker);
    id++;
  }
  //mapas de calor
  public void mapacalor(View view) {
    if(!markerList.isEmpty()) {
      HeatmapTileProvider mProvider;
      ArrayList<LatLng> lista = new ArrayList<>();
      for (Marker marker : markerList)
        lista.add(marker.getPosition());
      mProvider = new HeatmapTileProvider.Builder()
          .data(lista)
          .build();
      mOverlay = mapa.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
    }
  }
//eliminar puntos de mapas de calor
  public void limpiar(View view) {

    if(mOverlay != null)
      mOverlay.remove();
  }
}
