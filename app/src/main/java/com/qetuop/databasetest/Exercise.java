package com.qetuop.databasetest;

import java.io.Serializable;

/**
 * Created by brian on 10/11/15.
 */
public class Exercise implements Serializable {
    private long id = 0;
    private String exerciseName = null;
    private long exerciseTypeId = 0;

    // for custom row view - can it be removed?
    boolean selected = false;

    public Exercise() {
    }

   // used when creating FROM the DB?
    public Exercise(long id, String exerciseName, long exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseName = exerciseName;
        this.id = id;
    }

    // used when adding TO the DB?
    public Exercise(String exerciseName, long exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
        this.exerciseName = exerciseName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseTypeId(long exerciseTypeId) {
        this.exerciseTypeId = exerciseTypeId;
    }

    public long getExerciseTypeId() {
        return exerciseTypeId;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return exerciseName;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
