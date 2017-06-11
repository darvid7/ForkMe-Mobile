package dlei.forkme.gui.activities;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import dlei.forkme.R;
import dlei.forkme.gui.activities.github.MergeMeActivity;
import dlei.forkme.gui.activities.github.TrendingRepositoriesActivity;
import dlei.forkme.gui.activities.github.UserRepositoriesViewActivity;
import dlei.forkme.gui.activities.github.UserStarsActivity;
import dlei.forkme.helpers.NetworkAsyncCheck;
import dlei.forkme.helpers.NetworkHelper;
import dlei.forkme.state.AppSettings;

// TODO: Move network checks to onFail() Http calls.
/**
 * BaseActivity for that application with a navigation drawer, all activities that require a navigation
 * drawer should extend BaseActivity.
 */
public class BaseActivity extends AppCompatActivity {

    // Subclasses have access to mNavDrawer.
    protected Drawer mNavDrawer = null;

    private PrimaryDrawerItem mStars;
    private PrimaryDrawerItem mTrending;
    private PrimaryDrawerItem mFindPeople;
    private PrimaryDrawerItem mYourRepositories;
    private PrimaryDrawerItem mSettings;
    private PrimaryDrawerItem mAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Log.d("BaseActivity: ", "created");

        mStars = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Stars")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_grade_48px));

        mTrending = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Trending")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_trending_up_48px));

        mFindPeople = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Find people")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_group_48px));

        mYourRepositories = new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName("Your repositories")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_folder_48px));

        mSettings = new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName("Settings")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_settings_48px));

        mAbout = new PrimaryDrawerItem()
                .withIdentifier(6)
                .withName("About")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_info_outline_48px));

    }

    public void inflateNavDrawer(Bundle savedInstanceState) {
        Log.d("BaseActivity: ", "inflateNavDrawer: called");
        Toolbar toolbar = (Toolbar) findViewById(R.id.nav_drawer_toolbar);
        setSupportActionBar(toolbar);
        NetworkAsyncCheck n = NetworkHelper.checkNetworkConnection(toolbar);
        if (n != null) {
            n.execute();
        }
        mNavDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.drawer_section_github),
                        mStars,
                        mTrending,
                        mYourRepositories,
                        new SectionDrawerItem().withName(R.string.drawer_section_people),
                        mFindPeople,
                        new SectionDrawerItem().withName(R.string.drawer_settings),
                        mSettings,
                        new SectionDrawerItem().withName(R.string.drawer_about),
                        mAbout
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem == mStars) {
                            Intent intent = new Intent(getApplicationContext(), UserStarsActivity.class);
                            intent.putExtra("userLogin", AppSettings.sUserLogin);
                            intent.putExtra("userName", AppSettings.sUserName);
                            intent.putExtra("userAvatarUrl", AppSettings.sUserAvatarUrl);
                            startActivity(intent);
                        } else if (drawerItem == mTrending) {
                            Intent intent = new Intent(getApplicationContext(), TrendingRepositoriesActivity.class);
                            startActivity(intent);

                        } else if (drawerItem == mFindPeople) {
                            Intent intent = new Intent(getApplicationContext(), MergeMeActivity.class);
                            startActivity(intent);

                        } else if (drawerItem == mYourRepositories) {
                            Intent intent = new Intent(getApplicationContext(), UserRepositoriesViewActivity.class);
                            intent.putExtra("userLogin", AppSettings.sUserLogin);
                            startActivity(intent);

                        } else if (drawerItem == mSettings) {
                            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(intent);

                        } else if (drawerItem == mAbout) {
                            Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                            startActivity(intent);
                        }
                        Log.d("Menu clicked: ", "position: " + position + " item: " + drawerItem);
                        return true;
                    }
                })

                .withSavedInstance(savedInstanceState)
                .build();
        Log.d("BaseActivity: ", "inflateNavDrawer: " + mNavDrawer);
    }


    public void inflateNavDrawer(Bundle savedInstanceState, String currentActivity) {
        Log.d("BaseActivity: ", "inflateNavDrawer: called, currentActivity: " + currentActivity);
        this.inflateNavDrawer(savedInstanceState);

        // Disable clicking on current activity.
        if (currentActivity.equals(UserStarsActivity.class.getSimpleName())) {
            mStars.withSelectable(false);
            mNavDrawer.setSelection(mStars, false);
        } else if (currentActivity.equals(TrendingRepositoriesActivity.class.getSimpleName())) {
            mTrending.withSelectable(false);
            mNavDrawer.setSelection(mTrending, false);
        } else if (currentActivity.equals(MergeMeActivity.class.getSimpleName())) {
            mFindPeople.withSelectable(false);
            mNavDrawer.setSelection(mFindPeople, false);
        } else if (currentActivity.equals(UserRepositoriesViewActivity.class.getSimpleName())) {
            mYourRepositories.withSelectable(false);
            mNavDrawer.setSelection(mYourRepositories, false);
        } else if (currentActivity.equals(AppSettings.class.getSimpleName())) {
            mSettings.withSelectable(false);
            mNavDrawer.setSelection(mSettings, false);
        } else if (currentActivity.equals(AboutActivity.class.getSimpleName())) {
            mAbout.withSelectable(false);
            mNavDrawer.setSelection(mAbout, false);
        }
    }

    public void hideNavBar() {
        Log.wtf("HERE: ", "" + mNavDrawer);
        mNavDrawer.closeDrawer();
        Log.wtf("THERE: ", "" + mNavDrawer);

    }

    public void showNavBar() {
        Log.wtf("HAI: ", "" + mNavDrawer);

        //mNavDrawer.getContent().setVisibility(View.VISIBLE);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("BaseActivity: ", "onSavedInstanceState: called");
        // Add the values which need to be saved from the drawer to the bundle.
        outState = mNavDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Log.d("BaseActivity: ", "Back");
        if (mNavDrawer != null) {
            Log.d("BaseActivity: ", "isDrawerOpen: " + mNavDrawer.isDrawerOpen());
            Log.d("BaseActivity: ", "mNavDrawer: " + mNavDrawer);
        }
        // Prioritize closing drawer.
        if (mNavDrawer != null && mNavDrawer.isDrawerOpen()) {
            mNavDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
