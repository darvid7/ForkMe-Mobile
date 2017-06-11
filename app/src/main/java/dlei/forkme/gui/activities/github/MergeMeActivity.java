package dlei.forkme.gui.activities.github;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.endpoints.ForkMeBackendApi;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.activities.SettingsActivity;
import dlei.forkme.gui.adapters.DeveloperContactRecyclerViewAdapter;
import dlei.forkme.helpers.LocationHelper;
import dlei.forkme.model.DeveloperContactInfo;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Activity that allows user to contact other developers, must enable location.
 */
public class MergeMeActivity extends BaseActivity {
    private RecyclerView mRecyclerViewDevContactInfo;
    private DeveloperContactRecyclerViewAdapter mAdapterDevContactInfo;
    private AppCompatTextView mMainTextView;
    private AppCompatButton mMainButton;
    private ProgressBar mProgressBarSpinner;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<DeveloperContactInfo> mDeveloperContactInfo = new ArrayList<>();

    /**
     * Get latest information about location permissions.
     */
    @Override
    public void onResume() {
        super.onResume();
        boolean locationPermissionsDisabledForever = !shouldShowRequestPermissionRationale(
                Manifest.permission.ACCESS_FINE_LOCATION);;
        boolean hasLocationPermissions = LocationHelper.hasLocationPermissions(this);
        Log.d("MergeMeActivity: ", "locationsDisabledForever: " + locationPermissionsDisabledForever);
        Log.d("MergeMeActivity: ", "hasLocationPermissions: " + hasLocationPermissions);
        if (!hasLocationPermissions) {
            // Set up views.
            mRecyclerViewDevContactInfo.setVisibility(View.GONE);
            mProgressBarSpinner.setVisibility(View.GONE);
            mMainTextView.setVisibility(View.VISIBLE);
            mMainButton.setVisibility(View.VISIBLE);

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
            // Set up views.
            mMainTextView.setVisibility(View.GONE);
            mMainButton.setVisibility(View.GONE);
            mProgressBarSpinner.setVisibility(View.VISIBLE);
            mRecyclerViewDevContactInfo.setVisibility(View.VISIBLE);
            // If developer list not populated, populate it.
            if (!(mDeveloperContactInfo.size() > 0)) {
                this.getDevelopers();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MergeMeActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_me);
        super.inflateNavDrawer(savedInstanceState, MergeMeActivity.class.getSimpleName());
        Log.d("MergeMeActivity: ", "created");
        setTitle("MergeMe");
        // Set up UI elements.
        mRecyclerViewDevContactInfo = (RecyclerView) findViewById(R.id.mergeMeRecyclerView);
        mMainButton = (AppCompatButton) findViewById(R.id.mergeMeMainButton);
        mMainTextView = (AppCompatTextView) findViewById(R.id.mergeMeMainText);
        mProgressBarSpinner = (ProgressBar) findViewById(R.id.progress_bar_spinner);

        // Set up components of RecyclerView.
        mAdapterDevContactInfo = new DeveloperContactRecyclerViewAdapter(mDeveloperContactInfo);
        mLayoutManager = new LinearLayoutManager(this);

        // Set up RecyclerView.
        mRecyclerViewDevContactInfo.setLayoutManager(mLayoutManager);
        mRecyclerViewDevContactInfo.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewDevContactInfo.setAdapter(mAdapterDevContactInfo);

        // Set up lines between items in list.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerViewDevContactInfo.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerViewDevContactInfo.addItemDecoration(dividerItemDecoration);

    }

    /**
     * Get developers who have been aggregated together based on location.
     */
    public void getDevelopers() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl(BaseUrls.forkMeBackendApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForkMeBackendApi endpoint = retrofit.create(ForkMeBackendApi.class);
        Call<List<DeveloperContactInfo>> call = endpoint.getDevelopers();

        call.enqueue(new Callback<List<DeveloperContactInfo>>() {
            @Override
            public void onResponse(Call<List<DeveloperContactInfo>> call, Response<List<DeveloperContactInfo>> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    ArrayList<DeveloperContactInfo> developerContactInfos = (ArrayList<DeveloperContactInfo>) response.body();

                    // Add starred repositories to array.
                    for (DeveloperContactInfo devInfo: developerContactInfos) {
                        mDeveloperContactInfo.add(devInfo);
                    }
                    // Notify data set changed.
                    mAdapterDevContactInfo.notifyDataSetChanged();

                    mProgressBarSpinner.setVisibility(View.GONE);

                } else {
                    // Should not happen.
                    Log.w("MergeMeActivity: ", String.format(
                            "getDevelopers(): Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<DeveloperContactInfo>> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("MergeMeActivity: ", "getDevelopers(): Failed: " + t.getMessage());
            }
        });
    }
}
