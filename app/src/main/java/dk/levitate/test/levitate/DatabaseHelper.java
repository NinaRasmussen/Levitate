package dk.levitate.test.levitate;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nina on 14/01/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "BOOSTS";

    // Table columns
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String RANK  = "rank";
    public static final String CATEGORY = "category";
    public static final String TIME = "time";
    public static final String ACTIVE = "active";

    // Database Information
    static final String DB_NAME = "BOOST.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE + " TEXT NOT NULL, "
            + DESCRIPTION + " TEXT NOT NULL, "
            + RANK + " INTEGER NOT NULL, "
            + CATEGORY + " TEXT NOT NULL, "
            + TIME + " INTEGER NOT NULL, "
            + ACTIVE + " INTEGER NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
