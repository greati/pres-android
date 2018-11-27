package com.example.vitorgreati.presapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.User;

import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.MapTileProviderBasic;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class DashMapFragment extends Fragment {

    private MapView mapView;

    private MyLocationNewOverlay myLocationNewOverlay;

    public static Fragment newInstance() {
        Fragment instance = new DashMapFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //mapView = new MapView(getContext());
        final View v = inflater.inflate(R.layout.dash_map_fragment, container, false);

        mapView = v.findViewById(R.id.mapview);

        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(false);

        //org.osmdroid.views.MapView.LayoutParams mapParams = new org.osmdroid.views.MapView.LayoutParams(
        //        org.osmdroid.views.MapView.LayoutParams.MATCH_PARENT,
        //       org.osmdroid.views.MapView.LayoutParams.MATCH_PARENT,
        //       null, 0, 0, 0);
        //mapView.setLayoutParams(mapParams);

        mapView.getController().setZoom(11.0);

        final GpsMyLocationProvider myLocationProvider = new GpsMyLocationProvider(getContext());
        this.myLocationNewOverlay = new MyLocationNewOverlay(myLocationProvider, mapView);
        this.myLocationNewOverlay.enableMyLocation();
        mapView.getOverlays().add(this.myLocationNewOverlay);

        mapView.addOnFirstLayoutListener(new MapView.OnFirstLayoutListener() {
            @Override
            public void onFirstLayout(View v, int left, int top, int right, int bottom) {
                LocationManager locationManager = (LocationManager)
                        getActivity().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                
                Location l = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                ((MapView) v).getController().setCenter(new GeoPoint(l.getLatitude(), l.getLongitude()));
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
