package dlei.forkme.gui.activities.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;

public class SettingsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("SettingsActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.inflateNavDrawer(savedInstanceState, SettingsActivity.class.getSimpleName());
        Log.d("SettingsActivity: ", "created");

    }
}
