package com.qetuop.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Constructor;
import java.sql.SQLException;

/**
 * Created by brian on 10/11/15.
 */
public class TypeDbAdapter extends AbstractDbAdapter {


    public TypeDbAdapter(Context ctx) {
        super(ctx);
    }


/*    public long createType(Type type) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_TYPE_TYPE, type.getType());

        return mDb.insert(TABLE_TYPE, null, args);
    }*/

    public long createType(Type type) {
       /* try {
            SQLiteDatabase db = open();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        ContentValues args = new ContentValues();
        args.put(COLUMN_TYPE_TYPE, type.getType());

        return mDb.insert(TABLE_TYPE, null, args);
    }

    /*
    public boolean delete(long rowId) {
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    public Cursor fetchAll() {
        return mDb.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_SOURCE,
                KEY_DESTINATION}, null, null, null, null, null);
    }

    public Cursor fetch(long rowId) throws SQLException {
        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]{KEY_ROWID,
                                KEY_SOURCE, KEY_DESTINATION}, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }*/
}
