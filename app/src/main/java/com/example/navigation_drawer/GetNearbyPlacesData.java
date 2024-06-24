package com.example.navigation_drawer;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    String googlePlacesData;
    GoogleMap googleMap;
    String url;

    /*
    Takes the GoogleMap object and URL as parameters.
    Uses DownloadUrl to read data from the provided URL.
    Returns the data as a string.
     */
    @Override
    protected String doInBackground(Object... objects) {
        googleMap = (GoogleMap) objects[0];
        url = (String) objects[1];

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlacesData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;
    }

    /*
    Parses the JSON response.
    Extracts location and name information for each result.
    Adds markers to the GoogleMap object for each location.
     */
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject parentObject = new JSONObject(s);
            JSONArray resultArray = parentObject.getJSONArray("results");

            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject jsonObject = resultArray.getJSONObject(i);
                JSONObject locationObj = jsonObject.getJSONObject("geometry").getJSONObject("location");

                double lat = locationObj.getDouble("lat");
                double lng = locationObj.getDouble("lng");
                String placeName = jsonObject.getString("name");

                LatLng latLng = new LatLng(lat, lng);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(placeName);

                googleMap.addMarker(markerOptions);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
