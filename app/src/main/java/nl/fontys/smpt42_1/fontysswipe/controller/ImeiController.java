package nl.fontys.smpt42_1.fontysswipe.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Merik on 16/06/2017.
 */

public class ImeiController {

    private Activity activity;
    private static final int REQUEST_CODE = 0;

    public ImeiController(Activity activity){
        this.activity = activity;
    }

    public String getImei(){
        if (ActivityCompat.checkSelfPermission(activity, READ_PHONE_STATE) != PERMISSION_GRANTED &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{READ_PHONE_STATE}, REQUEST_CODE);
        }

        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
}
