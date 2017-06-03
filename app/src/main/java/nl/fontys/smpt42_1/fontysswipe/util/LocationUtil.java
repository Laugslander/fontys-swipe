package nl.fontys.smpt42_1.fontysswipe.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.fontys.smpt42_1.fontysswipe.domain.enums.City;

/**
 * @author SMPT42-1
 */
public class LocationUtil implements LocationListener {

    private Geocoder geocoder;
    private Activity locationActivity;
    private Location location;

    private static int REQUEST_CODE = 0;
    private static final String GPS_SERVICE = "gps";

    public LocationUtil(Activity locationActivity) {
        this.locationActivity = locationActivity;
    }

    public City getCity() {
        this.location = null;
        Geocoder geocoder = new Geocoder(locationActivity);
        List<Address> addressesList = new ArrayList<>();

        LocationManager manager = (LocationManager) locationActivity.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(locationActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(locationActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                locationActivity.requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.INTERNET},
                        REQUEST_CODE);
            }
        } else {
            manager.requestLocationUpdates(GPS_SERVICE, 0, 0, this);
            location = manager.getLastKnownLocation(GPS_SERVICE);
        }

        try {
            if (location != null) {
                addressesList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } else {
                Log.e("LOCATION NULL", "Location is null.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Stad: " + addressesList.get(0).getLocality());
        switch (addressesList.get(0).getLocality()) {
            case "Eindhoven":
                return City.EINDHOVEN;
            case "Tilburg":
                return City.TILBURG;
            default:
                return null;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
