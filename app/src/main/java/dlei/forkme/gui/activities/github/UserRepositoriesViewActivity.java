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
import java.util.Locale;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.adapters.RepositoryRecyclerViewAdapter;
import dlei.forkme.helpers.NetworkAsyncCheck;
import dlei.forkme.helpers.NetworkHelper;
import dlei.forkme.model.Repository;
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

/**
 * Shows a user info (name, username, bio) and their repositories, can click on star to view starred repositories.
 */
public class UserRepositoriesViewActivity extends BaseActivity {
    private ProgressBar mProgressBarSpinner;
    private RecyclerView mRecyclerViewRepositories;
    private RepositoryRecyclerViewAdapter mAdapterRepositories;
    private LinearLayoutManager mLayoutManager;
    private ArrayList<Repository> mRepositories = new ArrayList<Repository>();

    private AppCompatImageView mAvatarIconImage;
    private AppCompatTextView mUserNameText;
    private AppCompatTextView mUserBioText;
    private AppCompatTextView mUserFollowerCountText;
    private AppCompatTextView mUserFollowingCountText;
    private AppCompatTextView mUserRepositoryCountText;
    private AppCompatImageView mUserStarIconImage;
    private AppCompatImageView mRepositoriyIconImage;
    private AppCompatTextView mFollowingText;
    private AppCompatTextView mFollowersText;
    private View mDivider;
    private ArrayList<View> mHeaderViews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("UserReposViewActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repositories_view);
        super.inflateNavDrawer(savedInstanceState, UserRepositoriesViewActivity.class.getSimpleName());
        Log.d("UserReposViewActivity: ", "created");
        mProgressBarSpinner = (ProgressBar)findViewById(R.id.progress_bar_spinner);

        Intent i = getIntent();
        String userLogin = i.getStringExtra("userLogin");

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

        // Set up UI elements.
        mAvatarIconImage = (AppCompatImageView) findViewById(R.id.userAvatarIconImage);
        mUserNameText = (AppCompatTextView) findViewById(R.id.userNameLoginText);
        mUserBioText = (AppCompatTextView) findViewById(R.id.userBioText);
        mUserFollowerCountText = (AppCompatTextView) findViewById(R.id.followersCountText);
        mUserFollowingCountText = (AppCompatTextView) findViewById(R.id.followingCountText);
        mUserRepositoryCountText = (AppCompatTextView) findViewById(R.id.repositoryCountText);
        mUserStarIconImage = (AppCompatImageView) findViewById(R.id.userStarredIconImage);
        mDivider = findViewById(R.id.viewHeaderSeparator);
        mRepositoriyIconImage = (AppCompatImageView) findViewById(R.id.userRepositoryIconImage);
        mFollowersText = (AppCompatTextView) findViewById(R.id.userFollowerText);
        mFollowingText = (AppCompatTextView) findViewById(R.id.userFollowingText);


        mHeaderViews.add(mAvatarIconImage);
        mHeaderViews.add(mUserNameText);
        mHeaderViews.add(mUserBioText);
        mHeaderViews.add(mUserFollowerCountText);
        mHeaderViews.add(mUserFollowingCountText);
        mHeaderViews.add(mUserRepositoryCountText);
        mHeaderViews.add(mUserStarIconImage);
        mHeaderViews.add(mDivider);
        mHeaderViews.add(mRepositoriyIconImage);
        mHeaderViews.add(mFollowersText);
        mHeaderViews.add(mFollowingText);
        this.getGithubUser(userLogin);
        toogleViews(mHeaderViews, View.GONE);
    }

    /**
     * Used to hide and unhide views when waiting for HTTP calls.
     * @param views List of views to change visibility of.
     * @param visability to set for ech view in views.
     */
    private void toogleViews(List<View> views, int visability) {
        for (View v: views) {
            v.setVisibility(visability);
        }
    }

    /**
     * HTTP request to Github API get a user's data.
     * @param userLogin login of user to get data for.
     */
    public void getGithubUser(final String userLogin) {
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

        GithubApi service = retrofit.create(GithubApi.class);

        Call<User> call = service.getUser(userLogin);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    // Populate activity UI.
                    User u = response.body();

                    mUserBioText.setText(u.getBio());
                    mUserFollowerCountText.setText(String.format(Locale.getDefault(), "%d", u.getFollowers()));
                    mUserFollowingCountText.setText(String.format(Locale.getDefault(), "%d", u.getFollowing()));
                    mUserRepositoryCountText.setText(String.format(Locale.getDefault(), "%d", u.getPublicRepositories()));
                    final String userAvatarUrl = u.getAvatarUrl();
                    if (userAvatarUrl != null && !userAvatarUrl.equals("")) {
                        Picasso.with(mAvatarIconImage.getContext()).load(userAvatarUrl).into(mAvatarIconImage);
                    }
                    final String name = u.getName();
                    mUserNameText.setText(name + " (" + userLogin + ")");
                    mUserStarIconImage.setOnClickListener(
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // To starred repository view for user.
                                    Intent intent = new Intent(getApplicationContext(), UserStarsActivity.class);
                                    intent.putExtra("userLogin", userLogin);
                                    intent.putExtra("userName", name);
                                    intent.putExtra("userAvatarUrl", userAvatarUrl);
                                    startActivity(intent);
                                }
                            }
                    );
                    toogleViews(mHeaderViews, View.VISIBLE);

                    getUserRepositories(userLogin);
                } else {
                    Log.w("UserReposViewActivity: ", String.format(
                            "getGithubUser: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("UserActivity: ", "getGithubUser: Failed: " + t.getMessage());
                NetworkAsyncCheck n = NetworkHelper.checkNetworkConnection(mAvatarIconImage);
                if (n != null) {
                    n.execute();
                }

            }
        });
    }

    /**
     * Get a user's GitHub repositories (restricted to public if the user is not the authenticated user.
     * @param userLogin GitHub login for the user.
     */
    public void getUserRepositories(final String userLogin) {
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
        endpoint.getMyRepositories(userLogin).enqueue(new Callback<List<Repository>>() {
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
                    Log.w("UserReposViewActivity: ", String.format(
                            "getUserRepositories: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                    Log.w("UserReposViewActivity: ", String.format(
                            "getUserRepositories: Request: %s",
                            call.request())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("UserReposViewActivity: ", "getGithubStars: Failed: " + t.getMessage());
                NetworkAsyncCheck n = NetworkHelper.checkNetworkConnection(mAvatarIconImage);
                if (n != null) {
                    n.execute();
                }
            }
        });

    }

}
