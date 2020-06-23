package com.d2w.dahada.data.activity_main.fragment_main;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.d2w.dahada.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {
    private static final String TAG = "MapActivity";
    private GoogleMap map;
    private CameraPosition cameraPosition;

    private ConstraintLayout courseEdit;
    private Button courseButton, saveButton, cancelButton;

    private FusedLocationProviderClient fusedLocationProviderClient;

    private LatLng defaultLocation = new LatLng(37.56, 126.97); // 서울
    private LatLng beforeLocation;

    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints = new ArrayList<>();

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab1, fab2;

    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int DEFAULT_ZOOM = 15;
    private boolean locationPermissionGranted;

    private Location lastKnownLocation;

    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) { // 인스턴스 상태로 저장된 위치와 카메라 위치 되돌리기
            lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.main_exercise_map);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        courseEdit = findViewById(R.id.courseEdit);
        courseButton = findViewById(R.id.courseButton);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        courseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab1.setVisibility(View.VISIBLE);
                courseEdit.setVisibility(View.VISIBLE);
                courseButton.setVisibility(View.GONE);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab1.setVisibility(View.INVISIBLE);
                fab2.startAnimation(fab_close);
                fab2.setClickable(false);
                isFabOpen = false;
                courseEdit.setVisibility(View.GONE);
                courseButton.setVisibility(View.VISIBLE);
                map.clear();
                arrayPoints.clear();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab1.setVisibility(View.INVISIBLE);
                fab2.startAnimation(fab_close);
                fab2.setClickable(false);
                isFabOpen = false;
                courseEdit.setVisibility(View.GONE);
                courseButton.setVisibility(View.VISIBLE);
                map.clear();
                arrayPoints.clear();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        getLocationPermission(); // 권한 확인

        updateLocationUI(); //

        getDeviceLocation();

        map.setOnMapClickListener(this);
    }

    private void getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // 현재 위치로 지도의 카메라 위치를 변경
                            lastKnownLocation = task.getResult();
                            if (lastKnownLocation != null) {
                                map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                        new LatLng(lastKnownLocation.getLatitude(),
                                                lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                    updateLocationUI();
                }
            }
        }
    }

    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.fab1:
                anim();
                break;
            case R.id.fab2:
                anim();
                map.clear();
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(beforeLocation);
                map.addMarker(markerOptions);

                for (int i = 0; i < arrayPoints.size(); i++) {
                    markerOptions.position(arrayPoints.get(i));
                    markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.round_location_on_black_24dp));
                    map.addMarker(markerOptions);
                }

                polylineOptions = new PolylineOptions();
                polylineOptions.color(Color.RED);
                polylineOptions.width(5);
                arrayPoints.add(beforeLocation);
                polylineOptions.addAll(arrayPoints);
                polylineOptions.add(arrayPoints.get(0));
                map.addPolyline(polylineOptions);

                Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            fab2.startAnimation(fab_close);
            fab2.setClickable(false);
            isFabOpen = false;
        } else {
            fab2.startAnimation(fab_open);
            fab2.setClickable(true);
            isFabOpen = true;
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (courseEdit.getVisibility() == View.VISIBLE) {
            beforeLocation = latLng;
            Log.d("onMapClick", beforeLocation + "");
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            map.clear();

            map.addMarker(markerOptions);

            polylineOptions = new PolylineOptions();
            polylineOptions.color(Color.RED);
            polylineOptions.width(5);
            polylineOptions.addAll(arrayPoints);
            if (arrayPoints.size() > 0) {
                polylineOptions.add(arrayPoints.get(0));
            }
            map.addPolyline(polylineOptions);

            for (int i = 0; i < arrayPoints.size(); i++) {
                markerOptions.position(arrayPoints.get(i));
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.round_location_on_black_24dp));
                map.addMarker(markerOptions);
            }
        }
    }
}
