package nl.fontys.smpt42_1.fontysswipe.controller;

import android.app.Activity;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import nl.fontys.smpt42_1.fontysswipe.domain.School;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;
import static android.content.Context.LOCATION_SERVICE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;

/**
 * @author SMPT42-1
 */
class LocationController implements LocationListener {

    private static final Logger logger = Logger.getLogger(LocationController.class.getSimpleName());

    private static final int REQUEST_CODE = 0;
    private static final String GPS_SERVICE = "gps";
    private static final int MIN_GPS_TIME = 0;
    private static final int MIN_GPS_DISTANCE = 0;
    private static final int GEOCODER_RESULTS = 1;
    private static final int GEOCODER_RESULT_INDEX = 0;

    private final Activity activity;
    private final List<School> schools;

    LocationController(Activity activity, List<School> schools) {
        this.activity = activity;
        this.schools = schools;
    }

    School getSchoolLocation() {
        if (ActivityCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION) != PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(activity, ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED &&
                VERSION.SDK_INT >= VERSION_CODES.M) {
            activity.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION, INTERNET}, REQUEST_CODE);
            return getSchoolLocation();
        }

        String city = "";
        try {
            LocationManager manager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
            manager.requestLocationUpdates(GPS_SERVICE, MIN_GPS_TIME, MIN_GPS_DISTANCE, this);
            Location location = manager.getLastKnownLocation(GPS_SERVICE);
            city = new Geocoder(activity).getFromLocation(location.getLatitude(), location.getLongitude(), GEOCODER_RESULTS).get(GEOCODER_RESULT_INDEX).getLocality();
        } catch (IOException | NullPointerException e) {
            logger.log(Level.SEVERE, "An error occurred while retrieving the location.", e);
        }

        for (School school : schools) {
            if (school.getCity().equals(city)) {
                return school;
            }
        }
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Do nothing.
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Do nothing.
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Do nothing.
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Do nothing.
    }

}
