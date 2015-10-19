package com.qetuop.databasetest;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by brian on 10/18/15.
 */
public class WorkoutDbAdapter extends AbstractDbAdapter {


    public WorkoutDbAdapter(Context ctx) {
        super(ctx);
    }

    public long createWorkout(Workout workout) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_WORKOUT_CREATION_DATE, workout.getDate());
        args.put(COLUMN_WORKOUT_EXERCISE_ID, workout.getExercise_id());
        args.put(COLUMN_WORKOUT_SETS, workout.getSets());
        args.put(COLUMN_WORKOUT_REPS, workout.getReps());
        args.put(COLUMN_WORKOUT_WEIGHT, workout.getWeight());

        return mDb.insert(TABLE_WORKOUT, null, args);
    }
}
