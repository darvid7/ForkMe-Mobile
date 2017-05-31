package dlei.forkme.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.net.InetAddress;
import java.util.Locale;

import dlei.forkme.R;

public class NetworkHelper {

    static String noConnectionMessage = "Please connect to %s, this app requires internet access.";

    /**
     * Return null if not connected to a network, else returns an async task to check the network has internet connection.
     * @param view any view in the activity that called this, used to make snackbar.
     * @return null or NetworkHelper.NetworkAsyncCheck.
     */
    public static NetworkAsyncCheck checkNetworkConnection(View view) {
        Context context = view.getContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnectedToNetwork = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnectedToNetwork) {
            Snackbar.make(view, String.format(Locale.getDefault(), noConnectionMessage, "a network"), Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {}
                    })
                    .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                    .show();
            return null;
        }
        return new NetworkAsyncCheck(view);
    }

}

