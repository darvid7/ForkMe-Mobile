package dlei.forkme.gui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;

import com.hardikgoswami.oauthLibGithub.GithubOauth;

import java.util.ArrayList;
import java.util.Calendar;

import dlei.forkme.R;
import dlei.forkme.helpers.NetworkAsyncCheck;
import dlei.forkme.helpers.NetworkHelper;

// https://github.com/thephpleague/oauth2-github/issues/4
public class  LogInActivity extends AppCompatActivity {

    private AppCompatButton mLoginButton;
    private static String clientId = "d34146c5bec6fe30be05";
    private static String clientSecret = "caad4e8944b4800502f9e2d2e13910825b978ba1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mLoginButton = (AppCompatButton) findViewById(R.id.loginButton);

        NetworkAsyncCheck n = NetworkHelper.checkNetworkConnection(mLoginButton);
        if (n != null) {
            n.execute();
        }

        final ArrayList<String> scopeList = new ArrayList<String>();
        scopeList.add("user");  // Needed to follow people.
        scopeList.add("public_repo");  // Needed to star repositories.

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity: ", "start OAuth login");
                GithubOauth
                        .Builder()
                        .withClientId(clientId)
                        .withClientSecret(clientSecret)
                        .withContext(getApplicationContext())
                        .packageName("dlei.forkme")
                        .nextActivity("dlei.forkme.gui.activities.github.TrendingRepositoriesActivity")
                        .withScopeList(scopeList)
                        .debug(true)
                        .execute();
            }
        });

    }
}
