package dlei.forkme.gui.activities.github;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.squareup.picasso.Picasso;

import org.commonmark.ext.autolink.AutolinkExtension;
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.ins.InsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import dlei.forkme.R;
import dlei.forkme.endpoints.GithubApi;
import dlei.forkme.gui.activities.BaseActivity;
import dlei.forkme.model.Readme;
import dlei.forkme.model.Repository;
import dlei.forkme.helpers.LanguageColor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static dlei.forkme.R.id.markdownHtmlTextView;

public class RepositoryViewActivity extends BaseActivity {
    private static Parser sParser;
    private static HtmlRenderer sRender;
    private Repository mRepository;
    private AppCompatTextView mRepoFullNameText;
    private String mOAuthToken;

    private HtmlTextView mMarkdownHtmlTextView;
    private AppCompatTextView mLanguageText;
    private AppCompatImageView mLanguageCircleImage;
    private AppCompatTextView mWatchCountText;
    private AppCompatTextView mForkCountText;
    private AppCompatTextView mStarCountText;
    private AppCompatTextView mRepoDescriptionText;
    private AppCompatImageView mIconImageView;

    private static void createParser() {
        // TODO: Move to singleton.
        sParser = Parser.builder()
                .build();

        sRender = HtmlRenderer.builder()
                .extensions(
                        Arrays.asList(
                                // Allows short hand autolink used in github markdowns.
                                AutolinkExtension.create(),
                                TablesExtension.create(),
                                InsExtension.create(),
                                StrikethroughExtension.create(),
                                HeadingAnchorExtension.create()
                        )
                )
                .escapeHtml(false)
                .build();
    }

    static {
        // Gets called as soon as app exists, only once.
        // This will happen before anything in this activity including constructor.
        createParser();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("RepositoryActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_view);
        super.inflateNavDrawer(savedInstanceState);
        Log.i("RepositoryActivity: ", "created");

        // There will always be a repository when this activity is started.
        Intent i = getIntent();
        mRepository = i.getParcelableExtra("repository");

        SharedPreferences sharedPreferences = getSharedPreferences("github_prefs", 0);
        mOAuthToken = sharedPreferences.getString("oauth_token", null);

        Log.w("Access token: ", mOAuthToken);

        mRepoFullNameText = (AppCompatTextView) findViewById(R.id.repositoryViewFullNameText);
        mRepoFullNameText.setText(mRepository.getFullName());

        String repoName = mRepository.getRepoName();
        String ownerName = mRepository.getOwnerName();

        // Set up UI elements.
        mLanguageText = (AppCompatTextView) findViewById(R.id.languageText);
        mLanguageCircleImage = (AppCompatImageView) findViewById(R.id.languageCircleImageView);
        mWatchCountText = (AppCompatTextView) findViewById(R.id.watchCountText);
        mForkCountText = (AppCompatTextView) findViewById(R.id.forkCountText);
        mStarCountText = (AppCompatTextView) findViewById(R.id.starCountText);
        mRepoDescriptionText = (AppCompatTextView) findViewById(R.id.repositoryDescriptionText);
        mIconImageView = (AppCompatImageView) findViewById(R.id.iconImageView);

        // Picasso caches url and image automatically from SwipeDeckAdapter.
        Picasso.with(getBaseContext()).load(mRepository.getAvatarUrl()).into(mIconImageView);

        Log.w("RepositoryActivity: ", "watchers: "  + mRepository.getSubscribersCount());
        Log.w("RepositoryActivity: ", mRepository.toString());

        mWatchCountText.setText(String.format(Locale.getDefault(), "%d", mRepository.getSubscribersCount()));
        mForkCountText.setText(String.format(Locale.getDefault(), "%d", mRepository.getForkCount()));
        mStarCountText.setText(String.format(Locale.getDefault(), "%d", mRepository.getStargazerCount()));
        mRepoDescriptionText.setText(mRepository.getDescription());

        String language = mRepository.getLanguage();
        if (language != null) {
            mLanguageText.setText(language);
            String languageColorAsHex = LanguageColor.getColor(mRepository.getLanguage());
            if (languageColorAsHex != null) {
                int languageColorAsInt = Color.parseColor(languageColorAsHex);
                // TODO: Only draw on the inside of the circle.
                mLanguageCircleImage.setColorFilter(languageColorAsInt);
            }
        } else {
            mLanguageText.setText(R.string.none);
        }



        // TODO: Fix common mark parsing of code blocks in <pre> tags or swap to a web view.
        mMarkdownHtmlTextView = (HtmlTextView) findViewById(markdownHtmlTextView);

        // Load README.md
        this.getReadme(ownerName, repoName);
    }

    // Might be out of scope for assignment.
    // TODO: Handle http request start, rotate screen, activity dies, can result in errors.
    // TODO: Async callbacks that manipulates view might end up with null views when rotating screen.
    // TODO: Move Http requests to a service, live independently to activities, do this using AIDL, intents or data fragments.
    // Thank you @patrickshaw, head of android, rundl.

    /**
     * Gets and renders README.md of the repository.
     * Issue: Html <pre></pre> is not supported by any Html android libraries, leads to messy formatting
     * for code blocks. TODO: Find fix, worst case use a webview but this is a lot of overhead.
     * @param owner owner of the repository.
     * @param repo name of the repository.
     */
    public void getReadme(final String owner, final String repo) {
        // TODO: Move http boilerplate code to singleton (however Josh said this is fine).
        // Goes into the network level of OkHttp.
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()
                        //.addHeader("Authorization", "token " + mOAuthToken);
                        .addHeader("Authorization", "token " + mOAuthToken)
                        .addHeader("Content-Length", "0");
                return chain.proceed(newRequest.build());
            }
        });

        final Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GithubApi endpoint = retrofit.create(GithubApi.class);
        Call<Readme> call = endpoint.getReadme(owner, repo);

        call.enqueue(new Callback<Readme>() {
            @Override
            public void onResponse(Call<Readme> call, Response<Readme> response) {
                if (response.code() == 200 && response.isSuccessful()) {
                    Log.d("RepositoryActivity: ", String.format("getReadme for owner: %s, repo %s", owner, repo));
                    Readme readme = response.body();
                    String decodedMarkdown  = readme.getDecodedContent();
                    Node document = sParser.parse(decodedMarkdown);
                    String html = sRender.render(document);
                    Log.i("HTML: ", html);
                    try {
                        mMarkdownHtmlTextView.setHtml(html, new HtmlHttpImageGetter(mMarkdownHtmlTextView));
                    } catch (IndexOutOfBoundsException e) {
                        Log.w("RepositoryActivity: ", String.format(
                                "getReadme: IndexOutOfBoundsException - len of html: %s, message: %s",
                                html.length(), e.getMessage()));
                        html = "<h1>Error Loading readme</h3><p>url: <a href=" + readme.getHtmlUrl() + ">" + readme.getHtmlUrl() + "</a>" +
                                " could not be rendered properly :(</p><br/>" +
                                "<img src=" + readme.renderFailedImage() +
                                "></img>";
                        mMarkdownHtmlTextView.setHtml(html, new HtmlHttpImageGetter(mMarkdownHtmlTextView));
                    }
                } else {
                    Log.w("RepositoryActivity: ", String.format(
                            "getReadme: Error Response: Status code: %d, successful: %s, body: %s, headers: %s",
                            response.code(), response.isSuccessful(), response.body(), response.headers())
                    );

                    Log.w("RepositoryActivity: ", String.format(
                            "getReadme: Error Request: headers: %s, request: %s",
                            call.request().headers().toString(), call.request().toString())
                    );
                }
            }

            @Override
            public void onFailure(Call<Readme> call, Throwable t) {
                // Failure to connect to endpoint.
                Log.i("RepositoryActivity: ", "getReadme: Failed: " + t.getMessage());

            }
        });
    }
}
