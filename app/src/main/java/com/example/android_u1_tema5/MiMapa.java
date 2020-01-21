package com.example.android_u1_tema5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MiMapa extends FragmentActivity
    implements OnMapReadyCallback {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mi_mapa);
// Obtenemos el mapa de forma asíncrona (notificará cuando esté listo)
    SupportMapFragment mapFragment = (SupportMapFragment)
        getSupportFragmentManager().findFragmentById(R.id.mapa);
    mapFragment.getMapAsync(this);
  }
  @Override
  public void onMapReady(GoogleMap googleMap) {
    GoogleMap mapa = googleMap;
    LatLng Tacna = new LatLng(-18.011737, -70.253529); //Nos ubicamos en el centro de TAcna
    mapa.addMarker(new MarkerOptions().position(Tacna).title("Marcador Tacna"));
    mapa.moveCamera(CameraUpdateFactory.newLatLng(Tacna));
  }
}
