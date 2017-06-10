package dlei.forkme.state;

import android.util.Log;

import java.util.Locale;

import dlei.forkme.datastore.DatabaseHelper;
import dlei.forkme.datastore.SqlSyntax;

// Can either use this or shared preferences
public class Settings {
    // Setting attributes.
    public static String sLanguage = "All";
    public static String sSortBy = "Stars";
    public static String sTimeframe = "1 Month";
    public static String sUserLogin = "";
    public static int sFindPeopleAllowed = 0;
    public static String sFindPeopleMessage = "N/A";
    public static int sShowPrivateRepositories = 0;

    // Database attributes.
    public static final String TABLE_NAME = "settings";
    public static final String TIMEFRAME_COL = "timeframe";
    public static final String SORT_BY_COL = "sort_by";
    public static final String LANGUAGE_COL = "language";
    public static final String FIND_PEOPLE_ALLOWED_COL = "allowed";
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
            FIND_PEOPLE_ALLOWED_COL + SqlSyntax.TYPE_INTEGER + SqlSyntax.NOT_NULL + ", " +
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

    public static void setsUserLogin(String userLogin) {
        sUserLogin = userLogin;
    }

    public static void setFindPeopleAllowed(int i) {
        if (i > 1 || i < 0) {
            Log.wtf("Settings: ", "setShowPrivateRepositories: Invalid value " + i);
            return;
        }
        sFindPeopleAllowed = i;
    }

    public static void setFindPeopleAllowedMessage(String findPeopleAllowedMessage) {
        //if (sFindPeopleAllowed == 1) {
        sFindPeopleMessage = findPeopleAllowedMessage;
//        } else {
//            Log.wtf("Settings: ", String.format(Locale.getDefault(),
//                    "setFindPeopleAllowedMessage: Tried to set " +
//                    "sFindPeopleMessage (%s) when sFindPeopleAllowed == 0",
//                    findPeopleAllowedMessage));
//        }
    }

    public static void setShowPrivateRepositories(int i) {
        if (i > 1 || i < 0) {
            Log.wtf("Settings: ", "setShowPrivateRepositories: Invalid value " + i);
            return;
        }
        sShowPrivateRepositories = i;
    }

    public static void updateLanguage(String language) {
        sLanguage = language;
        Log.i("Settings: ", "updateLanguage(): " + language);
    }

    public static void updateTimeframe(String timeframe) {
        sTimeframe = timeframe;
        Log.i("Settings: ", "updateTimeframe(): " + timeframe);
    }

    public static void updateSortBy(String sortBy) {
        sSortBy = sortBy;
        Log.i("Settings: ", "updateSortBy(): " + sortBy);

    }


}
