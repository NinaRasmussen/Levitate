package dk.levitate.test.levitate;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Boost boost) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, boost.title);
        contentValue.put(DatabaseHelper.DESCRIPTION, boost.description);
        contentValue.put(DatabaseHelper.CATEGORY, boost.category.toString());
        contentValue.put(String.valueOf(DatabaseHelper.RANK), 1);
        contentValue.put(String.valueOf(DatabaseHelper.ACTIVE), true);
        contentValue.put(String.valueOf(DatabaseHelper.TIME), boost.time);

        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper._ID, DatabaseHelper.DESCRIPTION, DatabaseHelper.TITLE};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(Boost boost) {

        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TITLE, boost.title);
        contentValue.put(DatabaseHelper.DESCRIPTION, boost.description);
        contentValue.put(DatabaseHelper.CATEGORY, boost.category.toString());
        contentValue.put(String.valueOf(DatabaseHelper.RANK), 1);
        contentValue.put(String.valueOf(DatabaseHelper.ACTIVE), true);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValue, DatabaseHelper._ID + " = " + boost.id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

    public int getAllIds(){
        //TODO: Write real code
        return database.rawQuery("SELECT * FROM BOOSTS;",null).getCount();
    }


    public List<Boost> getAllBoosts(){
        //TODO: Write real code
        Cursor output =  database.rawQuery("SELECT * FROM BOOSTS;",null);


        List<Boost> boosts = new ArrayList<Boost>();

        try {
            int titleColIndex = output.getColumnIndex(DatabaseHelper.TITLE);
            int descriptionColIndex = output.getColumnIndex(DatabaseHelper.DESCRIPTION);
            int categoryColIndex = output.getColumnIndex(DatabaseHelper.CATEGORY);
            int rankColIndex = output.getColumnIndex(DatabaseHelper.RANK);
            int timeColIndex = output.getColumnIndex(DatabaseHelper.TIME);
            int activeColIndex = output.getColumnIndex(DatabaseHelper.ACTIVE);

            while (output.moveToNext()) {
                boosts.add( new Boost(
                        output.getString(descriptionColIndex),
                        output.getString(titleColIndex),
                        output.getInt(timeColIndex),
                        Category.valueOf(output.getString(categoryColIndex)),
                        output.getInt(activeColIndex)==1
                ));
            }
        } finally {
            output.close();
        }
        return boosts;
    }

}