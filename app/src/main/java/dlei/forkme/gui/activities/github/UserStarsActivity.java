package dlei.forkme.gui.activities.github;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.adapters.RepositoryRecyclerViewAdapter;
import dlei.forkme.model.Repository;
import dlei.forkme.state.AppSettings;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserStarsActivity extends BaseActivity {

    private ProgressBar mProgressBarSpinner;
    private RecyclerView mRecyclerViewRepositories;
    private RepositoryRecyclerViewAdapter mAdapterRepositories;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Repository> mRepositories = new ArrayList<Repository>();

    private AppCompatImageView mAvatarIconImage;
    private AppCompatTextView mUserNameText;
    private AppCompatTextView mViewStatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("UserStarsActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_stars);
        super.inflateNavDrawer(savedInstanceState, UserStarsActivity.class.getSimpleName());
        Log.d("UserStarsActivity: ", "created");
        // setTitle("Starred Repositories");

        mProgressBarSpinner = (ProgressBar)findViewById(R.id.progress_bar_spinner);

        Intent i = getIntent();
        String userLogin = i.getStringExtra("userLogin");
        String userName = i.getStringExtra("userName");
        String userAvatarUrl = i.getStringExtra("userAvatarUrl");

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

        // Set up header view.
        mAvatarIconImage = (AppCompatImageView) findViewById(R.id.avatarIconImage);
        mUserNameText = (AppCompatTextView) findViewById(R.id.userNameText);
        mViewStatusText = (AppCompatTextView) findViewById(R.id.viewStatusText);

        mViewStatusText.setText(getResources().getText(R.string.starred_repositories));
        mUserNameText.setText(userName + " (" + userLogin + ")");
        if (userAvatarUrl != null && !userAvatarUrl.equals("")) {
            Picasso.with(this).load(userAvatarUrl).into(mAvatarIconImage);
        }

        this.getGithubStars(userLogin);
    }

    // Note: Response from Github API won't have subscribers_count even though the docs said it does.
    // TODO: Double check that having the field @SerializedName("subscribers_count") in Repository
    // won't break anything if it doesn't exist in the json response. Pretty sure it doensn't as it just returns 0 now.
    public void getGithubStars(String user) {
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
        Call<List<Repository>> call = endpoint.getStarredRepositories(user);

        call.enqueue(new Callback<List<Repository>>() {
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
                    Log.w("UserStarsActivity: ", String.format(
                            "getGithubStars: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("UserStarsActivity: ", "getGithubStars: Failed: " + t.getMessage());
            }
        });
    }

}
