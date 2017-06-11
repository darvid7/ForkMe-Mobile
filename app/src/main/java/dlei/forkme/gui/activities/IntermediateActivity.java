package dlei.forkme.gui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.Locale;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.gui.activities.github.TrendingRepositoriesActivity;
import dlei.forkme.model.User;
import dlei.forkme.state.AppSettings;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IntermediateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate);

        // Safer to have OAuth token as static variable.
        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        AppSettings.setOAuthToken(sharedPreferences.getString("oauth_token", null));

        this.getAuthenticatedUser();

    }


    public void getAuthenticatedUser() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                // Manipulate request to add headers.
                // Can't mutate the request but can make a new one.
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        // Add in user access token.
                        .addHeader("Authorization", "token " + AppSettings.sOAuthToken);
                // Pass on our request to execute.
                return chain.proceed(newRequest.build());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl(BaseUrls.githubApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GithubApi endpoint = retrofit.create(GithubApi.class);
        Call<User> call = endpoint.getAuthenticatedUser();

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    User u = response.body();
                    // Initalize user details.
                    AppSettings.setsUserLogin(u.getLogin());
                    AppSettings.setsUserName(u.getName());
                    AppSettings.setsUserAvatarUrl(u.getAvatarUrl());
                    Log.d("IntermediateActivity: ", "getAuthenticatedUser: " + u.getLogin());
                    Intent intent = new Intent(getApplicationContext(), TrendingRepositoriesActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.wtf("IntermediateActivity: ", String.format(Locale.getDefault(),
                            "getAuthenticatedUser: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("TrendingActivity: ", "getAuthenticatedUser: Failed: " + t.getMessage());


            }
        });



    }

}
