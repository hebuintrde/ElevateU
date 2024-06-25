package com.example.navigation_drawer.Maps;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.navigation_drawer.ProfileAndNavigation.AboutUsActivity;
import com.example.navigation_drawer.ProfileAndNavigation.AccountActivity;
import com.example.navigation_drawer.ProfileAndNavigation.ChatActivity;
import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private DrawerLayout drawer;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Location mylocation;
    double currentLatitude, currentLongitude;
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private Marker currentLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawer = findViewById(R.id.drawer_background);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        // Initialize GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        // Connect GoogleApiClient
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable location layer if permissions are granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID_MULTIPLE_PERMISSIONS);
        }

        // Set the initial location to Graz and add markers for clinics
        LatLng graz = new LatLng(47.0707, 15.4395);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(graz, 12));

        // Add markers for the clinics
        List<Clinic> clinics = getClinics();

        for (Clinic clinic : clinics) {
            // Customize markers based on whether it's the current location or a clinic
            if (clinic.getName().equals(getString(R.string.your_current_location))) {
                // This is the marker for current location
                currentLocationMarker = mMap.addMarker(new MarkerOptions()
                        .position(clinic.getLocation())
                        .title(clinic.getName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))); // Use green color for current location
            } else {
                // This is a marker for a clinic
                mMap.addMarker(new MarkerOptions()
                        .position(clinic.getLocation())
                        .title(clinic.getName())
                        .snippet(clinic.getDescription())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))); // Set color to pink for clinics
            }
        }
    }
    /*
    In this method I defined the clinics in Graz
    In the future, other places can be added
     */

    private List<Clinic> getClinics() {
        List<Clinic> clinics = new ArrayList<>();
        // Coordinates, names, and descriptions of the clinics in Graz
        clinics.add(new Clinic(new LatLng(47.0717455, 15.3552233), getString(R.string.m1_med_beauty_graz), getString(R.string.botox_and_fillers)));
        clinics.add(new Clinic(new LatLng(47.0345404, 15.3492954), getString(R.string.graz_clinic_aesthetic_surgery), getString(R.string.rhinoplasty_and_liposuction)));
        clinics.add(new Clinic(new LatLng(47.070473, 15.3633478), getString(R.string.plastic_surgeon_dr_martin_grohmann), getString(R.string.breast_augmentation2)));
        clinics.add(new Clinic(new LatLng(47.0754391, 15.3524217), getString(R.string.surgeon_practice_dr_thomas_rappl), getString(R.string.nose_reconstruction2)));
        clinics.add(new Clinic(new LatLng(47.0754105, 15.3524195), getString(R.string.ma_ra_medical_aesthetic_research_academy), getString(R.string.liposuction_and_breast_reduction)));
        clinics.add(new Clinic(new LatLng(47.0772466, 15.3932702), getString(R.string.center_plastic_surgery_univ_doz_dr_franz_maria_haas), getString(R.string.aesthetic_surgery_for_men)));
        clinics.add(new Clinic(new LatLng(47.0690047, 15.3667976), getString(R.string.surgeon_practice_priv_doz_ddr_raimund_winter), getString(R.string.dermatology_and_hyaluron_filler)));

        // We can add more clinics as needed
        //clinics in Belgrade
        //clinics in Istanbul
        //clinics in London

        return clinics;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        checkPermission();
    }

    private void checkPermission() {
        int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermission = new ArrayList<>();

        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermission.add(Manifest.permission.ACCESS_FINE_LOCATION);
            if (!listPermission.isEmpty()) {
                ActivityCompat.requestPermissions(this, listPermission.toArray(new String[listPermission.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        } else {
            getMyLocation();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        // Handle suspended connection if needed
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // Handle connection failure if needed
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mylocation = location;

        if (mylocation != null) {
            currentLatitude = location.getLatitude();
            currentLongitude = location.getLongitude();

            // Remove the old marker if it exists
            if (currentLocationMarker != null) {
                currentLocationMarker.remove();
            }

            // Add the new marker for current location with green color
            LatLng currentLatLng = new LatLng(currentLatitude, currentLongitude);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(currentLatLng)
                    .title(getString(R.string.your_current_location))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)); // Use green color
            currentLocationMarker = mMap.addMarker(markerOptions);

            // Move the camera
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15.0f));
        }
    }



    private void getMyLocation() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
                try {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, createLocationRequest(), this);
                    checkLocationSettings();
                } catch (SecurityException e) {
                    e.printStackTrace(); // Log the exception or handle it appropriately
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID_MULTIPLE_PERMISSIONS);
            }
        }
    }

    private LocationRequest createLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000); // 10 seconds
        locationRequest.setFastestInterval(5000); // 5 seconds
        return locationRequest;
    }

    private void checkLocationSettings() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(createLocationRequest());
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. Show the user a dialog
                        try {
                            status.startResolutionForResult(MapsActivity.this, REQUEST_CHECK_SETTINGS_GPS);
                        } catch (IntentSender.SendIntentException e) {
                            e.printStackTrace();
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied and cannot be fixed here
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_ID_MULTIPLE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyLocation();
            } else {
                checkPermission();
            }
        }
    }

    // navigation drawer methods
    public void MenuClick(View view) {

        MainActivity.openthedrawer(drawer);
    }

    public void LogoClick(View view) {

        MainActivity.closethedrawer(drawer);
    }

    public void HomePageClick(View view) {
        Intent homepage = new Intent(MapsActivity.this, MainActivity.class);
        startActivity(homepage);
    }

    public void AccountClick(View view) {
        Intent account = new Intent(MapsActivity.this, AccountActivity.class);
        startActivity(account);
    }

    public void ChatClick(View view) {
        Intent chat = new Intent(MapsActivity.this, ChatActivity.class);
        startActivity(chat);
    }

    public void AboutUsClick(View view) {
        Intent aboutUs = new Intent(MapsActivity.this, AboutUsActivity.class);
        startActivity(aboutUs);
    }

    public void MapsClick(View view) {

        recreate();
    }

    public void ExitClick(View view) {
        AlertDialog.Builder warningwindow = new AlertDialog.Builder(MapsActivity.this);
        warningwindow.setTitle(getString(R.string.exit_title));
        warningwindow.setMessage(getString(R.string.exit_message));
        warningwindow.setPositiveButton(getString(R.string.exit_yes), (dialogInterface, i) -> {
            finishAffinity();
            System.exit(0);
        });
        warningwindow.setNegativeButton(getString(R.string.exit_no), (dialogInterface, i) -> dialogInterface.dismiss());
        warningwindow.show();
    }


    @Override
    protected void onPause() {
        MainActivity.closethedrawer(drawer);
        super.onPause();
    }
}
/**
 * We wanted to enhance the maps activity with Google Places API and we tried to implement it several times, but it didn't function properly.
 * Anyways, we have left two classes in form of java files (DownloadUrl and GetNearbyPlacesData), another methods from MapsActivity
 * have been deleted. When needed, we can provide AndroidStudio project with these functionalities (not-functioning)
 */

