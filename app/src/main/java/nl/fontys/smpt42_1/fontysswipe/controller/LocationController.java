package nl.fontys.smpt42_1.fontysswipe.controller;

import android.app.Activity;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
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
class LocationController {

    private static final Logger logger = Logger.getLogger(LocationController.class.getSimpleName());

    private static int REQUEST_CODE = 0;
    private static final String GPS_SERVICE = "gps";
    private static final int NUMBER_OF_GEOCODER_RESULTS = 1;
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
            Location location = manager.getLastKnownLocation(GPS_SERVICE);
            city = new Geocoder(activity).getFromLocation(location.getLatitude(), location.getLongitude(), NUMBER_OF_GEOCODER_RESULTS).get(GEOCODER_RESULT_INDEX).getLocality();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An error occurred while retrieving the location.", e);
        }

        for (School school : schools) {
            if (school.getCity().equals(city)) {
                return school;
            }
        }
        return null;
    }

}
