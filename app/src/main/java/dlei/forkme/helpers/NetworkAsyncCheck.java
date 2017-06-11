package dlei.forkme.helpers;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.net.InetAddress;
import java.util.Locale;

import dlei.forkme.R;

/**
 * Asynchronously checks for internet connection.
 */
public class NetworkAsyncCheck extends AsyncTask<Void, Void, Boolean> {
    private View view;

    public NetworkAsyncCheck(View view) {
        this.view = view;
        Log.i("NetworkAsyncCheck: ", "Starting");
    }

    protected Boolean doInBackground(Void... params) {
        Log.i("NetworkAsyncCheck: ", "Doing");
        try {
            InetAddress ipAddress = InetAddress.getByName("www.google.com");
            return !ipAddress.equals("") ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    protected void onPostExecute(Boolean result) {
        Log.i("NetWorkASyncCheck: ", " Done");
        if (result == Boolean.FALSE) {
            Snackbar.make(view, String.format(Locale.getDefault(), NetworkHelper.noConnectionMessage, "the internet"), Snackbar.LENGTH_INDEFINITE)
                    .setAction("CLOSE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {}
                    })
                    .setActionTextColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent))
                    .show();
        }
    }

}

