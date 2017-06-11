package dlei.forkme.state;

import android.util.Log;

import dlei.forkme.datastore.SqlSyntax;

/**
 * Static setting class to use rather than repeated writes to database/access to shared preferences (slow).
 */
public class AppSettings {
    // Setting attributes.
    public static String sLanguage = "All";
    public static String sSortBy = "Stars";
    public static String sTimeframe = "1 Month";
    public static String sUserLogin = "";
    public static int sLocationDisabledForever = 0;
    public static String sFindPeopleMessage = "N/A";
    public static int sShowPrivateRepositories = 0;
    public static String sOAuthToken = "";
    public static String sUserName = "";
    public static String sUserAvatarUrl = "";

    // Database attributes.
    public static final String TABLE_NAME = "settings";
    public static final String TIMEFRAME_COL = "timeframe";
    public static final String SORT_BY_COL = "sort_by";
    public static final String LANGUAGE_COL = "language";
    public static final String LOCATION_DISABLED_FOREVER_COL = "location_disabled_forever";
    public static final String FIND_PEOPLE_MESSAGE_COL = "message";
    public static final String PRIVATE_REPOS_COL = "private_repos";
    public static final String USER_ID_COL = "user";

    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE " +
            TABLE_NAME + " (" +
            USER_ID_COL + SqlSyntax.TYPE_STRING + SqlSyntax.PRIMARY_KEY + ", " +
            TIMEFRAME_COL + SqlSyntax.TYPE_STRING + SqlSyntax.NOT_NULL + ", " +
            SORT_BY_COL + SqlSyntax.TYPE_STRING + SqlSyntax.NOT_NULL + ", " +
            LANGUAGE_COL + SqlSyntax.TYPE_STRING + SqlSyntax.NOT_NULL + ", " +
            LOCATION_DISABLED_FOREVER_COL + SqlSyntax.TYPE_INTEGER + SqlSyntax.NOT_NULL + ", " +
            FIND_PEOPLE_MESSAGE_COL + SqlSyntax.TYPE_STRING + SqlSyntax.NOT_NULL + ", " +
            PRIVATE_REPOS_COL + SqlSyntax.TYPE_INTEGER + SqlSyntax.NOT_NULL +
            ")";

    public static void setLanguage(String language) {
        sLanguage = language;
    }

    public static void setSortBy(String sortBy) {
        sSortBy = sortBy;
    }

    public static void setTimeframe(String timeframe) {
        sTimeframe = timeframe;
    }

    public static void setLocationDisabledForever(int i) {
        if (i > 1 || i < 0) {
            Log.wtf("AppSettings: ", "setShowPrivateRepositories: Invalid value " + i);
            return;
        }
        sLocationDisabledForever = i;
    }

    public static void setFindPeopleAllowedMessage(String findPeopleAllowedMessage) {
        sFindPeopleMessage = findPeopleAllowedMessage;
    }

    public static void setShowPrivateRepositories(int i) {
        if (i > 1 || i < 0) {
            Log.wtf("AppSettings: ", "setShowPrivateRepositories: Invalid value " + i);
            return;
        }
        sShowPrivateRepositories = i;
    }

    public static void updateLanguage(String language) {
        sLanguage = language;
        Log.i("AppSettings: ", "updateLanguage(): " + language);
    }

    public static void updateTimeframe(String timeframe) {
        sTimeframe = timeframe;
        Log.i("AppSettings: ", "updateTimeframe(): " + timeframe);
    }

    public static void updateSortBy(String sortBy) {
        sSortBy = sortBy;
        Log.i("AppSettings: ", "updateSortBy(): " + sortBy);
    }

    public static void updateLocationDisabledForever(int i) {
        if (i > 1 || i < 0) {
            Log.wtf("AppSettings: ", "updateLocationDisabledForever(): Invalid value " + i);
            return;
        }
        Log.i("AppSettings: ", "updateLocationDisabledForever(): " + i);
        sLocationDisabledForever = i;
    }

    public static void setOAuthToken(String token) {
        sOAuthToken = token;
    }

    public static void setsUserLogin(String userLogin) {
        sUserLogin = userLogin;
    }

    public static void setsUserName(String userName) {
        sUserName = userName;
    }

    public static void setsUserAvatarUrl(String userAvatarUrl) {
        sUserAvatarUrl  = userAvatarUrl;
    }


}
