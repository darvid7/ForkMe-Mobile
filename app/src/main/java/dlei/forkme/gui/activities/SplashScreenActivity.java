package dlei.forkme.gui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dlei.forkme.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_splash_screen);
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
