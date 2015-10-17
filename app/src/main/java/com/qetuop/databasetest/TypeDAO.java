package com.qetuop.databasetest;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by brian on 10/11/15.
 */
public class TypeDAO {

    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public TypeDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}
