package nl.fontys.smpt42_1.fontysswipe.controller;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import static android.Manifest.permission.READ_PHONE_STATE;
import static android.content.Context.TELEPHONY_SERVICE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;


/**
 * @author SMPT42-1
 */
class ImeiController {

    private Activity activity;
    private static final int REQUEST_CODE = 0;

    ImeiController(Activity activity) {
        this.activity = activity;
    }

    String getImei() {
        if (ActivityCompat.checkSelfPermission(activity, READ_PHONE_STATE) != PERMISSION_GRANTED &&
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(new String[]{READ_PHONE_STATE}, REQUEST_CODE);
            return getImei();
        }

        TelephonyManager telephonyManager = (TelephonyManager) activity.getSystemService(TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

}
