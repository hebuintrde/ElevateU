package com.example.navigation_drawer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import com.google.android.gms.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private DrawerLayout drawer;
    //handles API requests to Google Play services
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Location mylocation;
    double currentLatitude, currentLongitude;
    //request codes for permissions and settings
    private final static int REQUEST_ID_MULTIPLE_PERMISSIONS = 0x2;
    private final static int REQUEST_CHECK_SETTINGS_GPS = 0x1;
    private Marker currentLocationMarker;
    private SearchView mapSearchView;
    //tag for logging purposes
    private static final String TAG = "MapsActivity";

    /*
    onCreate Method:
    Initializes the activity and sets the content view.
    Sets up the navigation drawer and search view.
    Initializes the GoogleApiClient for location services.
    Connects the GoogleApiClient.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        drawer = findViewById(R.id.drawer_background); // Assuming you have a DrawerLayout in your layout XML
        mapSearchView = findViewById(R.id.mapSearch);
        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null && !location.isEmpty()) {
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        Log.e(TAG, "Geocoder IOException", e);
                    }

                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    } else {
                        Toast.makeText(MapsActivity.this, "Location not found", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

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

    /*
    Called when the map is ready to be used.
    Enables the location layer if permissions are granted, otherwise requests permissions.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ID_MULTIPLE_PERMISSIONS);
        }
    }

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
        warningwindow.setTitle("Exit");
        warningwindow.setMessage("Are you sure you want to sign out?");
        warningwindow.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
                System.exit(0);
            }
        });
        warningwindow.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        warningwindow.show();
    }
    /*
    onPause: Closes the drawer when the activity is paused.
    onConnected: Called when the GoogleApiClient is connected, checks for location permissions.
    checkPermission: Checks and requests location permissions.
    onConnectionSuspended, onConnectionFailed: Handle connection suspension and failure.
    onRequestPermissionsResult: Handles the result of permission requests.
     */

    @Override
    protected void onPause() {
        MainActivity.closethedrawer(drawer);
        super.onPause();
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

    /*
    onLocationChanged: Called when the location changes, updates the current location marker, and finds nearby hospitals.
    getMyLocation: Requests location updates and checks location settings.
    createLocationRequest: Creates a LocationRequest object.
    checkLocationSettings: Checks and prompts the user to enable location settings if necessary.
     */
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

            // Resize the icon
            BitmapDescriptor icon = getResizedBitmapDescriptor(R.drawable.pin_map, 100, 100); // Adjust width and height as needed

            // Add the new marker
            LatLng currentLatLng = new LatLng(currentLatitude, currentLongitude);
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(currentLatLng)
                    .title("Your current location")
                    .icon(icon);
            currentLocationMarker = mMap.addMarker(markerOptions);

            // Move the camera
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15.0f));

            // Find nearby hospitals
            getNearbyHospitals();
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

    /*
    getResizedBitmapDescriptor: Resizes a bitmap to use as a marker icon.
    getNearbyHospitals: Constructs a URL to request nearby hospitals from the Google Places API and executes the request using GetNearbyPlacesData.
     */

    private BitmapDescriptor getResizedBitmapDescriptor(int drawableResId, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), drawableResId);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, width, height, false);
        return BitmapDescriptorFactory.fromBitmap(resizedBitmap);
    }


    private void getNearbyHospitals() {
        StringBuilder stringBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        stringBuilder.append("location=" + currentLatitude + "," + currentLongitude);
        stringBuilder.append("&radius=5000"); // Adjust radius as needed
        stringBuilder.append("&type=hospital");
        stringBuilder.append("&key=" + getResources().getString(R.string.my_map_api_key)); // Replace with your actual API key

        String url = stringBuilder.toString();

        Object[] dataTransfer = new Object[2];
        dataTransfer[0] = mMap;
        dataTransfer[1] = url;

        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        getNearbyPlacesData.execute(dataTransfer);
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

}
