package dlei.forkme.gui.activities.github;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import dlei.forkme.R;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.model.User;
import dlei.forkme.endpoints.GithubApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserViewActivity extends BaseActivity {
    // TODO: Move mOAuthToken and mUsername to a singleton to cache.
    private String mOAuthToken;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("UserActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        super.inflateNavDrawer(savedInstanceState);
        Log.i("UserActivity: ", "created");

        // There will always be a repository when this activity is started.
        Intent i = getIntent();
        mUsername = i.getExtras().getString("username");

        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        mOAuthToken = sharedPreferences.getString("oauth_token", null);

        // TODO: Instantiate UI elements.
    }


    /**
     * HTTP request to Github API get a user's data.
     * @param username login of user to get data for.
     */
    public void getGithubUser(final String username) {
        // Goes into the network level of OkHttp.
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                // Manipulate request to add headers.
                // Can't mutate the request but can make a new one.
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        // Add in user access token.
                        .addHeader("Authorization", "token " + mOAuthToken);
                // Pass on our request to execute.
                return chain.proceed(newRequest.build());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApi service = retrofit.create(GithubApi.class);

        Call<User> call = service.getUser(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // Popualte activity UI.
                // TODO: Handle error from this call?
                Log.i("GOT USER: ", " SUCCESS: " + response.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("UserActivity: ", "getGithubUser: Failed: " + t.getMessage());

            }
        });
    }
}
