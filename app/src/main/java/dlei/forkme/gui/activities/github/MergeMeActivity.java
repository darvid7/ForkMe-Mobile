package dlei.forkme.gui.activities.github;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.activities.SettingsActivity;
import dlei.forkme.helpers.LocationHelper;
import dlei.forkme.state.AppSettings;

public class MergeMeActivity extends BaseActivity {
    private ListViewCompat mMergeMeListView;
    private AppCompatTextView mMainTextView;
    private AppCompatButton mMainButton;

    @Override
    public void onResume() {
        super.onResume();
        boolean locationPermissionsDisabledForever = !shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION);;
        boolean hasLocationPermissions = LocationHelper.hasLocationPermissions(this);

        Log.d("MergeMeActivity: ", "locationsDisabledForever: " + locationPermissionsDisabledForever);
        Log.d("MergeMeActivity: ", "hasLocationPermissions: " + hasLocationPermissions);
        if (!hasLocationPermissions) {
            // Remove list view.
            mMergeMeListView.setVisibility(View.GONE);

            if (locationPermissionsDisabledForever) {
                // Have to give permissions externally.
                mMainTextView.setText(R.string.give_perms_externally_text);
                mMainButton.setText(R.string.give_perms_externally_button);
                final Context context = this;
                mMainButton.setOnClickListener(
                        // Sends user to permissions page outside of application.
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent();
                                i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + context.getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                context.startActivity(i);
                            }
                        }
                );
            } else {
                // Can give permissions in app.
                mMainTextView.setText(R.string.give_perms_in_app_text);
                mMainButton.setText(R.string.give_perms_in_app_button);
                final Context context = this;
                mMainButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent i = new Intent(context, SettingsActivity.class);
                                startActivity(i);
                            }
                        }
                );
            }
        } else {
            // Remove views to handle not having permissions.
            mMainTextView.setVisibility(View.GONE);
            mMainButton.setVisibility(View.GONE);
            // Set up merge me view.
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MergeMeActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_me);
        super.inflateNavDrawer(savedInstanceState, MergeMeActivity.class.getSimpleName());
        Log.d("MergeMeActivity: ", "created");

        // Set up UI elements.
        mMergeMeListView = (ListViewCompat) findViewById(R.id.mergeMeListView);
        mMainButton = (AppCompatButton) findViewById(R.id.mergeMeMainButton);
        mMainTextView = (AppCompatTextView) findViewById(R.id.mergeMeMainText);



//        if (!functionalityAbled) {
//            // https://stackoverflow.com/questions/32822101/how-to-programmatically-open-the-permission-screen-for-a-specific-app-on-android.
//            Context context = this;
//            final Intent i = new Intent();
//            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            i.addCategory(Intent.CATEGORY_DEFAULT);
//            i.setData(Uri.parse("package:" + context.getPackageName()));
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(i);
//
//        } else {
//
//        }
    }
}
