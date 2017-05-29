package dlei.forkme.gui.activities;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import dlei.forkme.R;

/**
 * BaseActivity for that application with a navigation drawer, all activities that require a navigation
 * drawer should extend BaseActivity.
 */
public class BaseActivity extends AppCompatActivity {

    // Subclasses have access to navDrawer.
    protected Drawer navDrawer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Log.d("BaseActivity: ", "created");
    }

    public void inflateNavDrawer(Bundle savedInstanceState) {
        Log.d("BaseActivity: ", "inflateNavDrawer: called");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbart);
        setSupportActionBar(toolbar);

        PrimaryDrawerItem stars = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Stars")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_grade_48px));

        PrimaryDrawerItem trending = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Trending")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_trending_up_48px));

        PrimaryDrawerItem findPeople = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Find people")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_group_48px));

        PrimaryDrawerItem yourRepositories = new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName("Your repositories")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_folder_48px));

        PrimaryDrawerItem settings = new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName("Settings")
                .withIcon(ActivityCompat.getDrawable(this, R.drawable.ic_settings_48px));


        navDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withFullscreen(true)
                .addDrawerItems(
                        new SectionDrawerItem().withName(R.string.drawer_section_github),
                        stars,
                        trending,
                        new SectionDrawerItem().withName(R.string.drawer_section_people),
                        findPeople,
                        yourRepositories,
                        new DividerDrawerItem(),
                        new SectionDrawerItem().withName(R.string.action_settings),
                        settings
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Log.d("Menu clicked: ", "position: " + position + " item: " + drawerItem);
                        return true;
                    }
                })

                .withSavedInstance(savedInstanceState)
                .build();
        Log.d("BaseActivity: ", "inflateNavDrawer: " + navDrawer);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("BaseActivity: ", "onSavedInstanceState: called");
        // Add the values which need to be saved from the drawer to the bundle.
        outState = navDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        Log.d("BaseActivity: ", "Back");
        if (navDrawer != null) {
            Log.d("BaseActivity: ", "isDrawerOpen: " + navDrawer.isDrawerOpen());
            Log.d("BaseActivity: ", "navDrawer: " + navDrawer);
        }
        // Prioritize closing drawer.
        if (navDrawer != null && navDrawer.isDrawerOpen()) {
            navDrawer.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
