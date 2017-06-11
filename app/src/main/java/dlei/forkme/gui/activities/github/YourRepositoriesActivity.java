package dlei.forkme.gui.activities.github;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.adapter.RepositoryRecyclerViewAdapter;
import dlei.forkme.model.Repository;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YourRepositoriesActivity extends BaseActivity {
    private String mOAuthToken;
    private ProgressBar mProgressBarSpinner;
    private RecyclerView mRecyclerViewRepositories;
    private RepositoryRecyclerViewAdapter mAdapterRepositories;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Repository> mRepositories = new ArrayList<Repository>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("YourReposActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_repositories);
        super.inflateNavDrawer(savedInstanceState, YourRepositoriesActivity.class.getSimpleName());
        Log.d("YourReposActivity: ", "created");

        mProgressBarSpinner = (ProgressBar)findViewById(R.id.progress_bar_spinner);

        // TODO: Refactor this out.
        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        mOAuthToken = sharedPreferences.getString("oauth_token", null);

        // Set up components of RecyclerView.
        mRecyclerViewRepositories = (RecyclerView) findViewById(R.id.repositoryRecyclerView);
        mAdapterRepositories = new RepositoryRecyclerViewAdapter(mRepositories);
        mLayoutManager = new LinearLayoutManager(this);

        // Set up RecyclerView.
        mRecyclerViewRepositories.setLayoutManager(mLayoutManager);
        mRecyclerViewRepositories.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewRepositories.setAdapter(mAdapterRepositories);

        // Set up lines between items in list.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerViewRepositories.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerViewRepositories.addItemDecoration(dividerItemDecoration);

        this.getMyRepositories();
    }

    public void getMyRepositories() {
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
                .baseUrl(BaseUrls.githubApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApi endpoint = retrofit.create(GithubApi.class);
        endpoint.getMyRepositories().enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                if (response.code() == 200 && response.isSuccessful()) {
                    ArrayList<Repository> repositories = (ArrayList<Repository>) response.body();

                    // Add starred repositories to array.
                    for (Repository r: repositories) {
                        mRepositories.add(r);
                    }
                    // Notify data set changed.
                    mAdapterRepositories.notifyDataSetChanged();

                    mProgressBarSpinner.setVisibility(View.GONE);

                } else {
                    Log.w("YourReposActivity: ", String.format(
                            "getMyRepositories: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("YourReposActivity: ", "getGithubStars: Failed: " + t.getMessage());
            }
        });

    }

}
