package dlei.forkme.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class LocationHelper {

    /**
     * Checks if location permissions has been granted to application.
     * @param context Activity context.
     * @return true if has location permissions, else false.
     */
    public static boolean hasLocationPermissions(Context context) {

        boolean hasLocationPermissions = ContextCompat
                .checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED;
        return hasLocationPermissions;
    }

}
