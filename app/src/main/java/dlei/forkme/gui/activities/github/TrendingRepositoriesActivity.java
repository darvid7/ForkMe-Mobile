package dlei.forkme.gui.activities.github;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dlei.forkme.R;
import dlei.forkme.endpoints.BaseUrls;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.gui.adapters.SwipeDeckAdapter;
import dlei.forkme.gui.fragments.StarNotificationDialog;
import dlei.forkme.model.Repository;
import dlei.forkme.model.RepositoryResponse;
import dlei.forkme.endpoints.ForkMeBackendApi;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.model.User;
import dlei.forkme.state.AppSettings;
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

// TODO: Fix bug, sometimes crashing when registering touch when swiping. https://github.com/flschweiger/SwipeStack/issues/46
// TODO: Fix landscape view, maybe only support portrait.
/**
 * Activity for displaying trending Github repositories retrieved from my backend in a SwipeStack.
 */
public class TrendingRepositoriesActivity extends BaseActivity implements SwipeStack.SwipeStackListener {
    private SwipeStack mSwipeDeck;
    private SwipeDeckAdapter mSwipeDeckAdapter;
    private List<Repository> mDeck = new ArrayList<Repository>();
    private boolean mSwipeIsTouch = false;
    private Toast mToast = null;  // Note: Toasts stay on screen even when app is exited, maybe change to snackbar.
    private ProgressBar mProgressBarSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TrendingActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_repositories);
        super.inflateNavDrawer(savedInstanceState, TrendingRepositoriesActivity.class.getSimpleName());
        Log.d("TrendingActivity: ", "created");



        mSwipeDeck = (SwipeStack) findViewById(R.id.swipeStack);
        mSwipeDeckAdapter = new SwipeDeckAdapter(mDeck);
        mSwipeDeck.setListener(this);

        SwipeStack.SwipeProgressListener swipeProgressListener = new SwipeStack.SwipeProgressListener() {

            // Called when user starts interacting with the repository card.
            @Override
            public void onSwipeStart(int position) { mSwipeIsTouch = true; }

            // Called when user moves the repository card.
            @Override
            public void onSwipeProgress(int position, float progress) { mSwipeIsTouch = false; }

            // Called when user stops interacting with the repository card.
            @Override
            public void onSwipeEnd(int position) {
                if (mSwipeIsTouch) {
                    onTouch(position);
                }
            }
        };

        mSwipeDeck.setSwipeProgressListener(swipeProgressListener);

        mSwipeDeck.setAdapter(mSwipeDeckAdapter);

        mProgressBarSpinner = (ProgressBar) findViewById(R.id.progress_bar_spinner);

        // Uncomment for first default repository to display to user.
//        Repository r = new Repository();
//        Owner o = new Owner();
//        o.setAvatarUrl("https://avatars0.githubusercontent.com/u/11433468?v=3&s=400");
//        r.setOwner(o);
//        r.setDescription("Are you ready?");
//        r.setFullname("Lets Go!");
//        r.setStargazerCount(999);
//        mDeck.add(r);

        // TODO: Double check sharedPreferences are stored on disk and it is better to read once and store in memory.
        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        AppSettings.setOAuthToken(sharedPreferences.getString("oauth_token", null));

        // TODO: Move this to login?
        // TODO: getAuthenticatedUser needs to be a precondition for this.
        this.getAuthenticatedUser();
        // this.getTrendingRepositoriesArray();
    }

    /**
     * Emulates View.OnTouch() method, starts RepositoryViewActivity.
     * link.fls.swipestack.SwipeHelper implements View.OnTouchListener() which is used to interact with
     * onSwipeStart/Progress/End() methods in SwipeStack.SwipeProgressListener().
     * By checking the top level view is not moved, instead of associating the gesture with just
     * left, right or nothing we can implement a method for onTouch() when no movement is detected.
     * @param position index of card/repository.
     */
    public void onTouch(int position) {
        Repository repository = mSwipeDeckAdapter.getItem(position);
        Intent i = new Intent(this, RepositoryViewActivity.class);
        Log.w("onTouch: ", repository.toString());
        Log.w("onTouch: ", "" + repository.getSubscribersCount());
        i.putExtra("repository", repository);
        startActivity(i);
    }

    /**
     * Makes Toast pop up, also assigns mToast to the new Toast instance.
     * Prevents spamming resulting in many delayed Toasts.
     * @param message String, the message to display.
     * @param prevToast Toast, previous Toast instance, can be null.
     * @return Toast, created Toast instance.
     */
    private void makeToast(String message, Toast prevToast) {
        if (prevToast != null) {
            prevToast.cancel();
        }
        Toast toastie = Toast.makeText(
                this, message, Toast.LENGTH_SHORT
        );
        toastie.show();
        mToast = toastie;
    }

    // SwipeDeck methods.

    /**
     * Called when mDeck becomes empty.
     */
    @Override
    public void onStackEmpty() {
        // TODO: Display better message to user, allow getting older trending repositories.
        this.makeToast("No more repositories! Come back again soon.", mToast);
        mProgressBarSpinner.setVisibility(View.VISIBLE);
        // TODO: Load new info, remove progressBarSpinner after.
    }

    /**
     * Called when a card is swiped left, dismiss the repository.
     * @param position index of card/repository.
     */
    @Override
    public void onViewSwipedToLeft(int position) {
        if (position > mDeck.size()) {
            // Shouldn't happen.
            Log.w("TrendingActivity: ", "onViewSwipedToLeft: position out of range: " + position);
            return;
        }

        Repository repo = mSwipeDeckAdapter.getItem(position);
        String msg = repo.getFullName() + " dismissed!";
        this.makeToast(msg, mToast);
    }

    /**
     * Called when a card is swiped right, star the repository.
     * @param position index of card/repository.
     */
    @Override
    public void onViewSwipedToRight(int position) {
        if (position > mDeck.size()) {
            // Shouldn't happen.
            Log.w("TrendingActivity: ", "onViewSwipedToRight: position out of range: " + position);
            return;
        }

        Repository repo = mSwipeDeckAdapter.getItem(position);
        String user = repo.getUserName();
        String repoName = repo.getRepoName();
        // Note: 91 character limit for repo.getFullName() before overflow.
        StarNotificationDialog s = StarNotificationDialog.newInstance(repo.getFullName());
        s.show(getFragmentManager(), "StarNotificationDialog-Tag");

        starGithubRepo(user, repoName);
    }

    // HTTP request methods.
    // TODO: Maybe move off Heroku to something that is faster in Australia.
    // TODO: Check network status on failures and notify user instead of just logging.

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
                    Log.d("TrendingActivity: ", "getAuthenticatedUser: " + u.getLogin());
                    getTrendingRepositoriesArray();
                } else {
                    Log.wtf("TrendingActivity: ", String.format(Locale.getDefault(),
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

    /**
     * HTTP request to backend hosted on Heroku which returns an array of repositories.
     */
    public void getTrendingRepositoriesArray() {
        OkHttpClient client = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BaseUrls.forkMeBackendApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForkMeBackendApi endpoint = retrofit.create(ForkMeBackendApi.class);

        Call<List<Repository>> call = endpoint.getRepositoriesArray();
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                ArrayList<Repository> repositories = (ArrayList<Repository>) response.body();
                // Add repositories to SwipeDeck.
                for (Repository r: repositories) {
                    mDeck.add(r);
                }

                // Let the adapter know data has changed.
                mSwipeDeckAdapter.notifyDataSetChanged();

                // Get rid of mProgressBarSpinner.
                mProgressBarSpinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                Log.i("TrendingActivity: ", "getTrendingRepositoriesArray: Failed: " + t.getMessage());
            }
        });
    }

    /**
     * HTTP request to backend hosted on Heroku which returns repositories in a json format
     * parsable by RepositoryResponse.
     */
    public void getTrendingRepositories() {

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BaseUrls.forkMeBackendApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ForkMeBackendApi endpoint = retrofit.create(ForkMeBackendApi.class);

        Call<RepositoryResponse> call = endpoint.getAllRepositories();
        call.enqueue(new Callback<RepositoryResponse>() {
            @Override
            public void onResponse(Call<RepositoryResponse> call, Response<RepositoryResponse> response) {
                RepositoryResponse repositories = response.body();
                // Add repositories to SwipeDeck.
                for (int i = 0; i < repositories.getSize(); i++) {
                    mDeck.add(repositories.getRepository(i));
                }
                // Let the adapter know data has changed.
                mSwipeDeckAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RepositoryResponse> call, Throwable t) {
                Log.i("TrendingActivity: ", "getTrendingRepositories: Failed: " + t.getMessage());
            }
        });
    }

    /**
     * HTTP request to Github API to try star a repository.
     * @param repoUserName owner of repository to be stared.
     * @param repoName name of repository to be stared.
     */
    public void starGithubRepo(final String repoUserName, final String repoName) {
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
                        .addHeader("Authorization", "token " + AppSettings.sOAuthToken)
                        .addHeader("Content-Length", "0");
                // Pass on our request to execute.
                return chain.proceed(newRequest.build());
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl(BaseUrls.githubApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Note: ResponseBody will bypass parsing the response so don't need to make a json serializable class.
        // http://stackoverflow.com/questions/33228126/how-can-i-handle-empty-response-body-with-retrofit-2

        GithubApi endpoint = retrofit.create(GithubApi.class);
        Call<ResponseBody> call = endpoint.starRepository(repoUserName, repoName);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Successfully connect to endpoint, response may still be a fail meaning repository not starred.
                // If successful response code 204, if unsuccessful response code is 401 (unauthorized).

                String msg = repoUserName + "/" + repoName;
                if (response.code() == 204 && response.isSuccessful()) {
                    msg = msg + " successfully starred!!";
                } else {
                    msg = msg + " failed to star :(";
                    Log.w("TrendingActivity: ", String.format(
                            "starGithubRepo: Error: Status code: %d, successful: %s," + "headers: %s",
                            response.code(), response.isSuccessful(), response.headers())
                    );
                }
                makeToast(msg, mToast);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("TrendingActivity: ", "starGithubRepo: Failed: " + t.getMessage());

            }
        });
    }

}
