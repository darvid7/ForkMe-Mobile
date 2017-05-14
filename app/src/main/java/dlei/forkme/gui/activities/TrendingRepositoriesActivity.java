package dlei.forkme.gui.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dlei.forkme.R;
import dlei.forkme.gui.adapter.SwipeDeckAdapter;
import dlei.forkme.model.Repository;
import dlei.forkme.model.RepositoryResponse;
import dlei.forkme.service.ForkMeBackendApiService;
import dlei.forkme.service.GithubApiService;
import link.fls.swipestack.SwipeStack;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingRepositoriesActivity extends AppCompatActivity {

    private SwipeStack mSwipeDeck;
    private SwipeDeckAdapter mSwipeDeckadapter;
    private List<Repository> mDeck = new ArrayList<Repository>();
    private String OAuthToken;// = mSharedPreferences.getString("oauth_token", null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repositories);

        // Make member attributes
        mSwipeDeck = (SwipeStack) findViewById(R.id.swipeStack);
        mSwipeDeckadapter = new SwipeDeckAdapter(mDeck);
//        SharedPreferences mSharedPreferences = getSharedPreferences("github_prefs", 0);
//        String OAuthToken = mSharedPreferences.getString("oauth_token", null);

        Log.i("\n----HERE", "HERE ----");
        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        OAuthToken = sharedPreferences.getString("oauth_token", null);
        Log.i("OAuth Token: ", OAuthToken);
        Log.i("------", "------\n");
        mSwipeDeck.setAdapter(mSwipeDeckadapter);
        Repository r = new Repository();
        r.setDescription("Are you ready?");
        r.setFullname("Lets Go!");
        r.setStargazerCount(999);
        mDeck.add(r);
  //      mDeck.add("Hello");
 //       mDeck.add("From");
 //       mDeck.add("The");
//        mDeck.add("Other");
//        mDeck.add("Side");
        System.out.println("DOING GOOD THINGS");
        starGithubRepo();
        System.out.println("GOOD THINGS ARE DONE");
        testHttp();
    }

    public void starGithubRepo() {
        // Goes into the network level of OkHttp.
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                // Manipulate request.
                // Add headers.
                // Can't mutate the request but can make a new one.
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        // Add in user access token.
                        .addHeader("Authorization", "token " + OAuthToken)
                        .addHeader("Content-Length", "0");
                // Pass on our request to execute.
                return chain.proceed(newRequest.build());
            }
        });

        // Github base API

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // addConverterFactory() to parse arrays from http get.

        GithubApiService service = retrofit.create(GithubApiService.class);
        Call<ResponseBody> call = service.starRepository();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // http://stackoverflow.com/questions/33228126/how-can-i-handle-empty-response-body-with-retrofit-2
                Log.i("!!!!Success: ", " starred repository!!");
                Log.i("response code: ", "" + response.code());
                Log.i("response success: ", "" + response.isSuccessful());
                // if good
                // 5-15 05:53:24.378 2242-2242/dlei.forkme I/response code:: 204
//                05-15 05:53:24.378 2242-2242/dlei.forkme I/response success:: true

                // if bad is 401 un authorizd and false.
                //Log.i("response headers: ", "" + response.headers());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Use .getMessage on throwables.
                Log.i("Failed: ", " starring a repository: " + t.getMessage());

            }
        });
    }

    public void testHttp() {
        // Do some http get to something.

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://forkme-backend.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Service that allows API calls.
        ForkMeBackendApiService service = retrofit.create(ForkMeBackendApiService.class);

//        int repoId = 1;
//        Call<Repository> call = service.getRepository(repoId);
//        call.enqueue(new Callback<Repository>() {
//            @Override
//            public void onResponse(Call<Repository> call, Response<Repository> response) {
//                // Handle response.
//                Repository repositoryResponse = response.body();
//                Log.i("API call passed: ", "response: " + repositoryResponse.toString());
//            }
//
//            @Override
//            public void onFailure(Call<Repository> call, Throwable t) {
//                Log.d("API call failed: ", " throwable: ", t);
//
//            }
//        });

        Call<RepositoryResponse> call = service.getAllRepositories();
        call.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                RepositoryResponse repositories = response.body();
                Log.i("API call passed: ", " response " + repositories);
                Repository aRepo = repositories.getRepository(1);
                Log.i("A repo returned: ", " repo: " + aRepo);

                for (int i = 0; i < repositories.getSize(); i++) {
                    mDeck.add(repositories.getRepository(i));
                }


                Log.i("SET UP SWIPE ADAPTER", " I THINK?");
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                Log.d("API call failed: ", " throwable: ", t);
            }
        });


    }
}
