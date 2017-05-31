package dlei.forkme.gui.activities.github;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;

public class MergeMeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MergeMeActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_me);
        super.inflateNavDrawer(savedInstanceState, MergeMeActivity.class.getSimpleName());
        Log.d("MergeMeActivity: ", "created");
    }
}
