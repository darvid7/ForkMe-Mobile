package dlei.forkme.gui.activities.github;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;

// Used to post location for now.
public class SettingsActivity extends BaseActivity implements LocationListener {
    LocationManager mLocationManager;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SettingsActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.inflateNavDrawer(savedInstanceState, SettingsActivity.class.getSimpleName());
        Log.d("SettingsActivity: ", "created");

        // Location.
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Log.d("SettingActivity: ", " Locations has permissions: "
                + (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Log.e("SettingActivity: ", " Locations setting up perms dialog");
                    // Don't have permission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.e("SettingActivity: ", " Asking for perms dialog");

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(SettingsActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();
            }  else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission. ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        } else {
            Log.e("SettingActivity: ", " didnt ask for perms");
            getLocation();
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.getLocation();

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

        }

    }
    public void getLocation() {
        try {
            Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            // location time retrieved > time in milliseconds - 2000 (less than 2 days old).
            if (location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
                // Do something with the recent location fix
                //  otherwise wait for the update below
                Log.d("SettingActivity: ", "Location old - lat: " + location.getLatitude() +
                        ", long: " + location.getLongitude() + ", time: " + location.getTime());
            } else {
                // Updates location.
                Log.d("SettingActivity: ", "Set updates");
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            }
        } catch (SecurityException e) {
            Log.d("SettingActivity: ", "Failed " + e.getMessage());

            return;
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            Log.d("SettingActivity: ", "Location update - lat: " + location.getLatitude() +
                    ", long: " + location.getLongitude());
            mLocationManager.removeUpdates(this);   // Stop updates
        }
    }

    // Required functions
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}


//    public void sendPost(String title, String body) {
//        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//
//                if(response.isSuccessful()) {
//                    showResponse(response.body().toString());
//                    Log.i(TAG, "post submitted to API." + response.body().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//                Log.e(TAG, "Unable to submit post to API.");
//            }
//        });
}

// https://stackoverflow.com/questions/40142331/how-to-request-location-permission-on-android-6/40142454