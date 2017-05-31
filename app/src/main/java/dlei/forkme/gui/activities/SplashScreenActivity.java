package dlei.forkme.gui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dlei.forkme.R;

// Note: R.id.x is like a namespace that allows you to search for ids in the app,
// however it will return null if the referenced resource is not instantiated.
// Having two activities with resources with the same name will never conflict, findViewById()
// will only look in the view tree for the current activity.

/**
 * Splash screen for application.
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_splash_screen);
        // TODO: Might move some network calls here.
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
