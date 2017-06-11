package dlei.forkme.gui.activities;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import java.util.Arrays;
import java.util.Calendar;

import dlei.forkme.R;
import dlei.forkme.datastore.DatabaseHelper;
import dlei.forkme.datastore.NoDataException;
import dlei.forkme.datastore.TooMuchDataException;
import dlei.forkme.helpers.LocationHelper;
import dlei.forkme.state.AppSettings;

// Used to post location for now.
public class SettingsActivity extends BaseActivity implements LocationListener {
    private LocationManager mLocationManager;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private AppCompatSpinner mLanguageSpinner;
    private AppCompatSpinner mSortBySpinner;
    private AppCompatSpinner mTimeframeSpinner;
    private SwitchCompat mLocationSwitch;
    private AppCompatTextView mSwitchText;
    private DatabaseHelper mDbHelper;

    /**
     * Allows changes when changing permission of application externally to be reflected.
     */
    @Override
    public void onResume() {
        super.onResume();
        // Set up switch.
        // The switch should reflect is the app has been given location permissions.
        // It is only clickable if it doesn't have location permissions.
        boolean hasLocationPermissions = LocationHelper.hasLocationPermissions(this);

        boolean locationPermissionsDisabledForever = !shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION);
        Log.d("onResume: ", "called");

        mLocationSwitch.setChecked(hasLocationPermissions);

        if (!hasLocationPermissions) {
            // Don't have location permissions.
            if (!locationPermissionsDisabledForever) {
                // Locations permissions not disabled forever, allow user to give them.
                Log.d("Not has perms: ", "Setting up listener");
                mLocationSwitch.setEnabled(true);
                mLocationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            askForLocationPermissions();
                        } else {
                            // This should not happen, should be disabled if perms granted
                            // and only updated again if perms revoked (external to this app).
                            // In which case this activity should be re-made and the listener will be
                            // set to false.
                            Log.wtf("SettingsActivity: ", "Switch.onCheckedChange(): Went to false");
                        }
                    }
                });
            } else {
                Log.d("Not has perms: ", "Disabled forever");
                // Location permissions disabled forever, disable button.
                mLocationSwitch.setEnabled(false);
                mSwitchText.setText(getResources().getText(R.string.disabled));

            }
        } else {
            Log.d("Has perms: ", "setClickable False");
            mLocationSwitch.setEnabled(true); // Allows accent color after disabled and then enabled externally.
            mLocationSwitch.setClickable(false);
            mSwitchText.setText(getResources().getText(R.string.str_true));
        }

    }

    @SuppressWarnings (value="unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SettingsActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.inflateNavDrawer(savedInstanceState, SettingsActivity.class.getSimpleName());
        Log.d("SettingsActivity: ", "created");
        setTitle("Settings");
        mDbHelper = DatabaseHelper.getDbInstance(this);
        try {
            // Loads settings from persistent storage to AppSettings attributes.
            mDbHelper.loadSettings();
        } catch (NoDataException e) {
            Log.d("No data: ", "No data from db");
            if (!AppSettings.sUserLogin.equals("")) {
                mDbHelper.insertSettings();
            } else {
                // TODO: Potential async issue where user data is not loaded.
                Log.wtf("SettingsActivity: ", "AppSettings.sUserLogin has no value");
            }
        } catch (TooMuchDataException e) {
            // Should not ever happen.
            Log.d("Too much data: ", "Too much data from db");
        }
        // Up to here: AppSettings will always have most up to date data.

        // Load what is chosen from settings static attributes.

        // Set up spinners.
        mLanguageSpinner = (AppCompatSpinner) findViewById(R.id.languageSpinner);
        mTimeframeSpinner = (AppCompatSpinner) findViewById(R.id.timeframeSpinner);
        mSortBySpinner = (AppCompatSpinner) findViewById(R.id.sortBySpinner);

        // Sort dropdown items for language spinner.
        String[] dropdownLanguageArray = getResources().getStringArray(R.array.language_array);
        Arrays.sort(dropdownLanguageArray);
        ArrayAdapter<String> languageDataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, dropdownLanguageArray);
        languageDataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mLanguageSpinner.setAdapter(languageDataAdapter);

        // Set selected values from settings.
        mLanguageSpinner.setSelection(languageDataAdapter.getPosition(AppSettings.sLanguage), false);
        mTimeframeSpinner.setSelection(
                ((ArrayAdapter<String>) mTimeframeSpinner.getAdapter())
                        .getPosition(AppSettings.sTimeframe), false);

        mSortBySpinner.setSelection(
                ((ArrayAdapter<String>) mSortBySpinner.getAdapter())
                .getPosition(AppSettings.sSortBy), false);

        // Set up spinner listeners.
        mLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.wtf("Here: ", "Language spinner item selected");
                String item = (String) parent.getItemAtPosition(position);
                AppSettings.updateLanguage(item);
                mDbHelper.updateLanguage();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mTimeframeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                AppSettings.updateTimeframe(item);
                mDbHelper.updateTimeframe();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSortBySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                AppSettings.updateSortBy(item);
                mDbHelper.updateSortBy();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mLocationSwitch = (SwitchCompat) findViewById(R.id.findPeopleAllowedSwitch);
        mSwitchText = (AppCompatTextView) findViewById(R.id.switchStatusText);

        // Set up Location.
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * Ask user to grant application location permissions.
     * precondition: Application doesn't already have location permissions.
     */
    public void askForLocationPermissions() {
        boolean hasLocationPermissions = LocationHelper.hasLocationPermissions(this);
        Log.d("SettingActivity: ", "askForLocationPermissions() has location permissions: "
                + hasLocationPermissions);

        if (!hasLocationPermissions) {
            // Doesn't have permission.
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d("SettingActivity: ", "askForLocationPermissions() Asking for perms dialog");
                // Show an explanation to the user asynchronously don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle(R.string.title_location_permission)
                        .setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Prompt the user once explanation has been shown
                                // Calls onRequestPermissionsResult() with result.
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
            Log.wtf("SettingActivity: ", " askForLocationPermissions() Already have permissions.");
            getLocation();
        }
    }


    /**
     * Called when the user responds to a permission request.
     * @param requestCode code assigned to permission request.
     * @param permissions permissions requested.
     * @param grantResults results of permission requests.
     */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted.
                    Log.d("SettingsActivity: ", "onRequestPermissionsResult() " +
                            "Location Permissions: granted");
                    String isCheckedStr = "True";
                    mSwitchText.setText(isCheckedStr);
                    mLocationSwitch.setClickable(false);
                    // Only enable to turn of locations if locations permissions is revoked from outside.
                    this.getLocation();
                } else {
                    // Permission denied.
                    Log.wtf("SettingsActivity: ", "onRequestPermissionsResult() " +
                            "Location Permissions: denied, test: " + permissions[0]);

                    boolean showRationale = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!showRationale) {
                        // User also selected never show again.
                        Log.wtf("SettingsActivity: ", "onRequestPermissionsResultI() " +
                                "Location Permissions: never show again clicked, disabled forever.");
                        // Disable switch button.
                        mLocationSwitch.setEnabled(false);
                        mSwitchText.setText(getResources().getText(R.string.disabled));
                        // Make LocationDisabledForever true.
                        AppSettings.updateLocationDisabledForever(1);
                    }
                    // Update switch to be false.
                    mLocationSwitch.setChecked(false);
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

    // Might do something with theses later.
    @Override
    public void onPause() { super.onPause(); }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


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