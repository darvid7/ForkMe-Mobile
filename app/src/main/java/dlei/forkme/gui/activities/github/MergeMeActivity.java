package dlei.forkme.gui.activities.github;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.state.AppSettings;

public class MergeMeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MergeMeActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_me);
        super.inflateNavDrawer(savedInstanceState, MergeMeActivity.class.getSimpleName());
        Log.d("MergeMeActivity: ", "created");

        boolean functionalityAbled = AppSettings.sLocationDisabledForever == 1;

        if (!functionalityAbled) {
            // https://stackoverflow.com/questions/32822101/how-to-programmatically-open-the-permission-screen-for-a-specific-app-on-android.
            Context context = this;
            final Intent i = new Intent();
            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            i.addCategory(Intent.CATEGORY_DEFAULT);
            i.setData(Uri.parse("package:" + context.getPackageName()));
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(i);

        } else {

        }
    }
}
