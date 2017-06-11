package dlei.forkme.gui.activities;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import dlei.forkme.R;
import dlei.forkme.gui.adapters.LicenceInfoRecyclerViewAdpater;
import dlei.forkme.model.Licence;

/**
 * About activity, shows infromation about the application and libraries used to make it.
 */
public class AboutActivity extends BaseActivity {
    ArrayList<Licence> mLicences = new ArrayList<>();
    private RecyclerView mRecyclerViewLicences;
    private LicenceInfoRecyclerViewAdpater mAdapterLicences;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("AboutActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        super.inflateNavDrawer(savedInstanceState, SettingsActivity.class.getSimpleName());
        Log.d("AboutActivity: ", "created");
        this.populateLicences();

        // Set up components of RecyclerView.
        mRecyclerViewLicences = (RecyclerView) findViewById(R.id.licenceRecyclerView);
        mAdapterLicences = new LicenceInfoRecyclerViewAdpater(mLicences);
        mLayoutManager = new LinearLayoutManager(this);

        // Set up RecyclerView.
        mRecyclerViewLicences.setLayoutManager(mLayoutManager);
        mRecyclerViewLicences.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewLicences.setAdapter(mAdapterLicences);

        // Set up lines between items in list.
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerViewLicences.getContext(),
                mLayoutManager.getOrientation());
        mRecyclerViewLicences.addItemDecoration(dividerItemDecoration);
    }

    /**
     * Popualte licence objects.
     */
    public void populateLicences() {
        Licence l = new Licence();
        l.setIconUrl("https://maxcdn.icons8.com/Share/icon/Operating_Systems//android_os1600.png");
        l.setLibraryName("Android Support Library");
        l.setLicence("Creative Commons Attribution 2.5");
        l.setLicenceUrl("https://creativecommons.org/licenses/by/2.5/");
        String supported[] = {"com.android.support:design:25.3.1",
                "com.android.support:cardview-v7:25.3.1",
                "com.android.support:recyclerview-v7:25.3.1",
                "com.android.support:support-annotations:25.3.1",
                "com.android.support.constraint:constraint-layout:1.0.2",
                "com.android.support:appcompat-v7:25.3.1"};
        l.setLibraryUrl("https://developer.android.com/topic/libraries/support-library/index.html");
        l.setAssociated(supported);
        l.setUsedFor("Backwards compatibility with older devices.");
        mLicences.add(l);

        Licence l2 = new Licence();
        l2.setLibraryUrl("https://github.com/mikepenz/MaterialDrawer");
        l2.setIconUrl("https://avatars1.githubusercontent.com/u/1476232?v=3&s=400");
        l2.setLibraryName("mikepenz/MaterialDrawer");
        l2.setLicence("Apache Licence 2.0");
        l2.setLicenceUrl("https://github.com/mikepenz/MaterialDrawer/blob/develop/LICENSE");
        String supported2[] = {"com.mikepenz:materialdrawer:5.9.2@aar",
                "com.mikepenz:materialize:1.0.2@aar",
                "com.mikepenz:iconics-core:2.8.5@aar",
                "com.mikepenz:fastadapter:2.5.3@aar"};
        l2.setAssociated(supported2);
        l2.setUsedFor("Navigation drawer (or hamburger menu).");
        mLicences.add(l2);

        Licence l3 = new Licence();
        l3.setIconUrl("https://avatars1.githubusercontent.com/u/5301029?v=3&s=400");
        l3.setLibraryName("geniushkg/github-oauth");
        l3.setLicence("Apache Licence 2.0");
        l3.setLibraryUrl("https://github.com/geniushkg/github-oauth");
        l3.setLicenceUrl("https://github.com/geniushkg/github-oauth/blob/master/Licence");
        String supported3[] = {"com.github.darvid7:github-oauth:1.0.5"};
        l3.setAssociated(supported3);
        l3.setUsedFor("GitHub OAuth (I modified and kept my own distribution).");
        mLicences.add(l3);

        Licence l4 = new Licence();
        l4.setIconUrl("https://cdn4.iconfinder.com/data/icons/new-google-logo-2015/400/new-google-favicon-512.png");
        l4.setLibraryName("google/gson");
        l4.setLicence("Apache Licence 2.0");
        l4.setLibraryUrl("https://github.com/google/gson");
        l4.setLicenceUrl("https://github.com/google/gson/blob/master/LICENSE");
        String supported4[] = {"com.google.code.gson:gson:2.8.0"};
        l4.setAssociated(supported4);
        l4.setUsedFor("Serializing/Conversion Json.");
        mLicences.add(l4);

        Licence l5 = new Licence();
        l5.setIconUrl("https://avatars2.githubusercontent.com/u/82592?v=3&s=200");
        l5.setLibraryName("square/retrofit");
        l5.setLicence("Apache Licence 2.0");
        l5.setLibraryUrl("https://github.com/square/retrofit");
        l5.setLicenceUrl("https://github.com/square/retrofit/blob/master/LICENSE.txt");
        String supported5[] = {"com.squareup.retrofit2:retrofit:2.1.0",
            "com.squareup.retrofit2:converter-gson:2.1.0"};
        l5.setAssociated(supported5);
        l5.setUsedFor("HTTP calls.");
        mLicences.add(l5);

        Licence l6   = new Licence();
        l6.setIconUrl("https://avatars2.githubusercontent.com/u/82592?v=3&s=200");
        l6.setLibraryName("square/picasso");
        l6.setLicence("Apache Licence 2.0");
        l6.setLibraryUrl("https://github.com/square/picasso");
        l6.setLicenceUrl("https://github.com/square/picasso/blob/master/LICENSE.txt");
        String supported6[] = {"com.squareup.picasso:picasso:2.5.2"};
        l6.setAssociated(supported6);
        l6.setUsedFor("Asynchronous image loads and caching.");
        mLicences.add(l6);

        Licence l7   = new Licence();
        l7.setIconUrl("https://avatars0.githubusercontent.com/u/11478053?v=3&s=400");
        l7.setLibraryName("flschweiger/SwipeStack");
        l7.setLicence("Apache Licence 2.0");
        l7.setLibraryUrl("https://github.com/flschweiger/SwipeStack");
        l7.setLicenceUrl("https://github.com/flschweiger/SwipeStack/blob/master/LICENSE");
        String supported7[] = {"link.fls:swipestack:0.3.0"};
        l7.setAssociated(supported7);
        l7.setUsedFor("Swipe stack cards.");
        mLicences.add(l7);

        Licence l8   = new Licence();
        l8.setIconUrl("https://avatars0.githubusercontent.com/u/11572708?v=3&s=200");
        l8.setLibraryName("SufficientlySecure/html-textview");
        l8.setLicence("Apache Licence 2.0");
        l8.setLicenceUrl("https://github.com/SufficientlySecure/html-textview/blob/master/LICENSE");
        l8.setLibraryUrl("https://github.com/SufficientlySecure");
        String supported8[] = {"org.sufficientlysecure:html-textview:3.4"};
        l8.setAssociated(supported8);
        l8.setUsedFor("HTML Text Views.");
        mLicences.add(l8);

        Licence l9    = new Licence();
        l9.setIconUrl("https://avatars3.githubusercontent.com/u/168166?v=3&s=200");
        l9.setLibraryName("atlassian/commonmark-java");
        l9.setLicence("BSD 2-clause \"Simplified\" Licence");
        l9.setLibraryUrl("https://github.com/atlassian/commonmark-java");
        l9.setLicenceUrl("https://github.com/atlassian/commonmark-java/blob/master/LICENSE.txt");
        String supported9[] = {
                "com.atlassian.commonmark:commonmark:0.9.0",
                "com.atlassian.commonmark:commonmark-ext-autolink:0.9.0",
                "com.atlassian.commonmark:commonmark-ext-gfm-strikethrough:0.9.0",
                "com.atlassian.commonmark:commonmark-ext-gfm-tables:0.9.0",
                "com.atlassian.commonmark:commonmark-ext-heading-anchor:0.9.0",
                "com.atlassian.commonmark:commonmark-ext-ins:0.9.0"

        };
        l9.setAssociated(supported9);
        l9.setUsedFor("Parsing HTML and Markdown.");
        mLicences.add(l9);

    }
}
