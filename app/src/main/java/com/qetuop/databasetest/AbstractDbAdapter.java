package com.qetuop.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by brian on 10/11/15.
 */
public class AbstractDbAdapter {

    protected static final String LOG = "AbstractDbAdapter";

    public DatabaseHelper mDatabaseHelper; // not thread safe?
    //protected static DatabaseHelper mDatabaseHelper; // more thread safe?
    protected SQLiteDatabase mDb;

    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "spotU2000.db";

    // Table Names
    public static final String TABLE_EXERCISE = "exercise";
    public static final String TABLE_TYPE = "type";

    // common
    public static final String COLUMN_ID = "_id"; // use BaseColumns._ID or create class that implements it? Contract class?

    // Exercise Table
    public static final String COLUMN_EXERCISE_NAME = "name";
    public static final String COLUMN_EXERCISE_TYPE_ID = "exercise_type"; // TODO this wil be a FK into another table?

    // Exercise Table
    public static final String COLUMN_TYPE_TYPE= "type";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";


   /* + TASK_CAT + " integer,"
    + " FOREIGN KEY ("+TASK_CAT+") REFERENCES "+CAT_TABLE+"("+CAT_ID+"));";

    TASK_CAT+" INTEGER REFERENCES "+CAT_TABLE+");";*/


    // Table Create Statements

    //  Type table create statement - create first since Exercise references it
    private static final String CREATE_TABLE_TYPE = "CREATE TABLE "
            + TABLE_TYPE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TYPE_TYPE + " text unique"
            + ")";  // no trailing ';'


    // Exercise table create statement
    private static final String CREATE_TABLE_EXERCISE = "CREATE TABLE "
            + TABLE_EXERCISE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_EXERCISE_NAME + " text not null, "
            + COLUMN_EXERCISE_TYPE_ID + " integer references " + TABLE_TYPE
            + ")"; // no trailing ';'



    protected final Context mCtx;

    protected static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

            Log.d(LOG, "ctor");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG, "onCreate");

            db.execSQL(CREATE_TABLE_EXERCISE);
            db.execSQL(CREATE_TABLE_TYPE);

            // requires API 16
//            db.setForeignKeyConstraintsEnabled(true); // ?finish transactions ??
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(this.getClass().getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");

            // create backup first?

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);

            onCreate(db);
        }
    }

    public AbstractDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    public AbstractDbAdapter open() throws SQLException {
        Log.d(LOG, "open");

        mDatabaseHelper = new DatabaseHelper(mCtx);
        mDb = mDatabaseHelper.getWritableDatabase();

        return this;
    }

/*    public SQLiteDatabase open() throws SQLException {
        Log.d(LOG, "open");

        if (mDatabaseHelper == null) {
            mDatabaseHelper = new DatabaseHelper(mCtx);
        }

        return mDatabaseHelper.getWritableDatabase();
    }*/



    public void close() {
        mDatabaseHelper.close();
    }

}
