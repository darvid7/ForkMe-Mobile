package dlei.forkme.gui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dlei.forkme.R;

/**
 * Custom web view activity to allow in application browsing.
 */
public class InAppWebView extends BaseActivity {

    private WebView webview;

    /**
     * Custom WebViewClient that allows web view navigation to all occur in the application.
     */
    private class InAppWebViewClient extends WebViewClient {

        // Allows links to be processed in app instead of opening an extrnal browser.
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest url) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("WebViewActivity: ", "creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_app_web_view);
        super.inflateNavDrawer(savedInstanceState);
        Log.d("WebViewActivity: ", "created");
        Intent i = getIntent();
        String url = i.getExtras().getString("url");
        Log.d("InAppView: ", "In app view created");
        webview = (WebView) findViewById(R.id.inAppWebView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.setWebViewClient(new InAppWebViewClient());
        webview.loadUrl(url);
    }

    /**
     * Allows navigation to go back a page to occur in app using the default back button.
     */
    @Override
    public void onBackPressed() {
        Log.d("WebViewActivity: ", "Back pressed");
        Log.d("WebViewActivity: ", "canGoBack: " + webview.canGoBack());
        // Prioritize navDrawerFirst.
        if (mNavDrawer.isDrawerOpen()) {
            super.onBackPressed();
        } else if (webview.canGoBack()) {
            webview.goBack();
        } else {
            super.onBackPressed();
        }

    }

}
