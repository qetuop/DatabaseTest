package com.qetuop.databasetest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 10/11/15.
 */
public class ExerciseDAO {
    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private String[] allColumns = {
            DBHelper.COLUMN_ID,
            DBHelper.COLUMN_EXERCISE_NAME,
            DBHelper.COLUMN_EXERCISE_TYPE_ID
    };

    public ExerciseDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Exercise createExercise(Exercise exercise) {
        ContentValues values = new ContentValues();

        values.put(DBHelper.COLUMN_EXERCISE_NAME, exercise.getExerciseName());
        values.put(DBHelper.COLUMN_EXERCISE_TYPE_ID, exercise.getExerciseTypeId());

        long insertId = database.insert(DBHelper.TABLE_EXERCISE, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_EXERCISE,
                allColumns,
                DBHelper.COLUMN_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Exercise newExercise = cursorToExercise(cursor);
        cursor.close();

        return newExercise;
    }

    public void deleteExercise(Exercise Exercise) {
        long id = Exercise.getId();
        System.out.println("Exercise deleted with id: " + id);
        database.delete(DBHelper.TABLE_EXERCISE, DBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Exercise> getAllExercises() {
        List<Exercise> Exercises = new ArrayList<Exercise>();

        Cursor cursor = database.query(DBHelper.TABLE_EXERCISE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            Exercises.add(exercise);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return Exercises;
    }

    // ?convert a "ptr/cursor" of a table entry to an Exercise Object
    private Exercise cursorToExercise(Cursor cursor) {
        Exercise Exercise = new Exercise();
        Exercise.setId(cursor.getLong(0));
        Exercise.setExerciseName(cursor.getString(1));
        Exercise.setExerciseTypeId(cursor.getLong(2));

        return Exercise;
    }

    public void clear() {
        for (Exercise exercise : getAllExercises()) {
            deleteExercise(exercise);
        }
    }

}
