package dlei.forkme.datastore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dlei.forkme.state.Settings;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper sDbInstance = null;

    public static final String sDatabaseName = "Settings";
    public static final int sDatabaseVersion = 1;

    private DatabaseHelper(Context context) {
        super(context, sDatabaseName, null, sDatabaseVersion);
    }

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
            sqLiteDatabase.execSQL(Settings.CREATE_TABLE_SQL);
            Log.d("DatabaseHelper: ", "onCreate() created db");
        } catch (SQLException e) {
            Log.wtf("Error: ", String.format("Create Table Statement: %s\nException: %s",
                    Settings.CREATE_TABLE_SQL, e));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Settings.TABLE_NAME); // Drop table.
            onCreate(sqLiteDatabase); // Re make table.
        } catch (SQLException e) {
            // Shouldn't happen, if it does log what a terrible message.
            Log.wtf("Error: ", String.format("onUpdate Error, Exception %s", e));
        }
        // db.close();  // Close database connection.
    }

    public void insertSettings() {
        if (Settings.sUserLogin.equals("")) {
            Log.wtf("Error: ", "DatabaseHelper.insertSettings(): user log in is null");
            return;
        }
        Log.i("DatabaseHelper: ", "insertSettings(): inserting settings");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.USER_ID_COL, Settings.sUserLogin);
        values.put(Settings.TIMEFRAME_COL, Settings.sTimeframe);
        values.put(Settings.SORT_BY_COL, Settings.sSortBy);
        values.put(Settings.LANGUAGE_COL, Settings.sLanguage);
        values.put(Settings.FIND_PEOPLE_ALLOWED_COL, Settings.sFindPeopleAllowed);
        values.put(Settings.FIND_PEOPLE_MESSAGE_COL, Settings.sFindPeopleMessage);
        values.put(Settings.PRIVATE_REPOS_COL, Settings.sShowPrivateRepositories);
        long result = db.insert(Settings.TABLE_NAME, null, values);
        if (result == -1) {
            Log.w("Error: ", String.format("Adding settings for user %s", Settings.sUserLogin));
        }

    }

    public void updateLanguage() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.LANGUAGE_COL, Settings.sLanguage);
        db.update(Settings.TABLE_NAME, values, Settings.USER_ID_COL + "='" + Settings.sUserLogin +"'",
                null);
        db.close();
    }

    public void updateTimeframe() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.TIMEFRAME_COL, Settings.sTimeframe);
        db.update(Settings.TABLE_NAME, values, Settings.USER_ID_COL + "='" + Settings.sUserLogin +"'",
                null);
        db.close();
    }

    public void updateSortBy() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Settings.SORT_BY_COL, Settings.sSortBy);
        db.update(Settings.TABLE_NAME, values, Settings.USER_ID_COL + "='" + Settings.sUserLogin +"'",
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
                Settings.TIMEFRAME_COL, Settings.SORT_BY_COL, Settings.LANGUAGE_COL,
                Settings.FIND_PEOPLE_ALLOWED_COL, Settings.FIND_PEOPLE_MESSAGE_COL, Settings.PRIVATE_REPOS_COL,
                Settings.TABLE_NAME,
                Settings.USER_ID_COL);
        Log.i("UserLogin: ", Settings.sUserLogin);

        Cursor cursor = db.rawQuery(query, new String[]{Settings.sUserLogin});

        cursor.moveToFirst();
        int rows = cursor.getCount();
        if (rows == 0) {
            cursor.close();
            db.close();
            throw new NoDataException(String.format(
                    "User with login %s does not exist", Settings.sUserLogin));
        } else if (rows > 1) {
            cursor.close();
            db.close();
            throw new TooMuchDataException(String.format(
                    "Only 1 row should be returned for user %s", Settings.sUserLogin));
        } else {
            // Update settings for a user.
            Log.i("Check has data worked: ", "Language: " + cursor.getString(2));
            Log.i("Check has data worked: ", "Timeframe: " + cursor.getString(1));

            Settings.setTimeframe(cursor.getString(0));
            Settings.setSortBy(cursor.getString(1));
            Settings.setLanguage(cursor.getString(2));
            Settings.setFindPeopleAllowed(cursor.getInt(3));
            Settings.setFindPeopleAllowedMessage(cursor.getString(4));
            Settings.setShowPrivateRepositories(cursor.getInt(5));
            cursor.close();
            db.close();
        }

    }
}
