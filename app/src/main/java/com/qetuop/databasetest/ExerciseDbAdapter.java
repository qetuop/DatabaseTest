package com.qetuop.databasetest;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by brian on 10/11/15.
 */
public class ExerciseDbAdapter extends AbstractDbAdapter {


    public ExerciseDbAdapter(Context ctx) {
        super(ctx);
    }

    public long createExercise(Exercise exercise) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_EXERCISE_NAME, exercise.getExerciseName());
        args.put(COLUMN_EXERCISE_TYPE_ID, exercise.getExerciseTypeId());

        return mDb.insert(TABLE_EXERCISE, null, args);
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
