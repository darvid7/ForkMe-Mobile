package dlei.forkme.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dlei.forkme.state.AppSettings;

/**
 * Helper to access data persistence layer.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper sDbInstance = null;

    public static final String sDatabaseName = "AppSettings";
    public static final int sDatabaseVersion = 1;

    private DatabaseHelper(Context context) {
        super(context, sDatabaseName, null, sDatabaseVersion);
    }

    /**
     * Singleton pattern so only 1 object of DatabaseHelper exists.
     * @param context activity context.
     * @return instance of DatabaseHelper.
     */
    public static synchronized DatabaseHelper getDbInstance(Context context) {
        if (sDbInstance == null) {
            sDbInstance = new DatabaseHelper(context);
            Log.d("DatabaseHelper: ", "getDbInstance() created new instance");
        }
        return sDbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(AppSettings.CREATE_TABLE_SQL);
            Log.d("DatabaseHelper: ", "onCreate() created db");
        } catch (SQLException e) {
            Log.wtf("Error: ", String.format("Create Table Statement: %s\nException: %s",
                    AppSettings.CREATE_TABLE_SQL, e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppSettings.TABLE_NAME); // Drop table.
            onCreate(sqLiteDatabase); // Re make table.
        } catch (SQLException e) {
            // Shouldn't happen, if it does log what a terrible message.
            Log.wtf("Error: ", String.format("onUpdate Error, Exception %s", e));
        }
        // db.close();  // Close database connection.
    }

    /**
     * Insert settings for a user into persistant storage.
     */
    public void insertSettings() {
        if (AppSettings.sUserLogin.equals("")) {
            Log.wtf("Error: ", "DatabaseHelper.insertSettings(): user log in is null");
            return;
        }
        Log.i("DatabaseHelper: ", "insertSettings(): inserting settings");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppSettings.USER_ID_COL, AppSettings.sUserLogin);
        values.put(AppSettings.TIMEFRAME_COL, AppSettings.sTimeframe);
        values.put(AppSettings.SORT_BY_COL, AppSettings.sSortBy);
        values.put(AppSettings.LANGUAGE_COL, AppSettings.sLanguage);
        values.put(AppSettings.LOCATION_DISABLED_FOREVER_COL, AppSettings.sLocationDisabledForever);
        values.put(AppSettings.FIND_PEOPLE_MESSAGE_COL, AppSettings.sFindPeopleMessage);
        values.put(AppSettings.PRIVATE_REPOS_COL, AppSettings.sShowPrivateRepositories);
        long result = db.insert(AppSettings.TABLE_NAME, null, values);
        if (result == -1) {
            Log.w("Error: ", String.format("Adding settings for user %s", AppSettings.sUserLogin));
        }
    }

    /**
     * Update language for a user in persistant storage.
     */
    public void updateLanguage() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppSettings.LANGUAGE_COL, AppSettings.sLanguage);
        db.update(AppSettings.TABLE_NAME, values, AppSettings.USER_ID_COL + "='" + AppSettings.sUserLogin +"'",
                null);
        db.close();
    }

    /**
     * Update timeframe preference for a user in persistant storage.
     */
    public void updateTimeframe() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppSettings.TIMEFRAME_COL, AppSettings.sTimeframe);
        db.update(AppSettings.TABLE_NAME, values, AppSettings.USER_ID_COL + "='" + AppSettings.sUserLogin +"'",
                null);
        db.close();
    }

    /**
     * Update stort by preference for a user in persistant storage.
     */
    public void updateSortBy() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AppSettings.SORT_BY_COL, AppSettings.sSortBy);
        db.update(AppSettings.TABLE_NAME, values, AppSettings.USER_ID_COL + "='" + AppSettings.sUserLogin +"'",
                null);
        db.close();
    }

    /**
     * Loads settings for a user from SQLite db.
     * @throws NoDataException Thrown when no data returned.
     * @throws TooMuchDataException Thrown when too much data returned.
     */
    public void loadSettings() throws NoDataException, TooMuchDataException{
        SQLiteDatabase db = this.getWritableDatabase();

        String baseQuery = "SELECT %s, %s, %s, %s, %s, %s from %s WHERE %s=?";

        String query = String.format(baseQuery,
                AppSettings.TIMEFRAME_COL, AppSettings.SORT_BY_COL, AppSettings.LANGUAGE_COL,
                AppSettings.LOCATION_DISABLED_FOREVER_COL, AppSettings.FIND_PEOPLE_MESSAGE_COL, AppSettings.PRIVATE_REPOS_COL,
                AppSettings.TABLE_NAME,
                AppSettings.USER_ID_COL);
        Log.i("UserLogin: ", AppSettings.sUserLogin);

        Cursor cursor = db.rawQuery(query, new String[]{AppSettings.sUserLogin});

        cursor.moveToFirst();
        int rows = cursor.getCount();
        if (rows == 0) {
            cursor.close();
            db.close();
            throw new NoDataException(String.format(
                    "User with login %s does not exist", AppSettings.sUserLogin));
        } else if (rows > 1) {
            cursor.close();
            db.close();
            throw new TooMuchDataException(String.format(
                    "Only 1 row should be returned for user %s", AppSettings.sUserLogin));
        } else {
            // Update settings for a user.
            Log.i("Check has data worked: ", "Language: " + cursor.getString(2));
            Log.i("Check has data worked: ", "Timeframe: " + cursor.getString(1));

            AppSettings.setTimeframe(cursor.getString(0));
            AppSettings.setSortBy(cursor.getString(1));
            AppSettings.setLanguage(cursor.getString(2));
            AppSettings.setLocationDisabledForever(cursor.getInt(3));
            AppSettings.setFindPeopleAllowedMessage(cursor.getString(4));
            AppSettings.setShowPrivateRepositories(cursor.getInt(5));
            cursor.close();
            db.close();
        }

    }
}
