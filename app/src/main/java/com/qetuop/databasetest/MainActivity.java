package com.qetuop.databasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    //private ExerciseDAO mExerciseDAO;
    private ExerciseDbAdapter mExerciseDbAdapter;
    private UserDbAdapter mUserDbAdapter;
    private WorkoutDbAdapter mWorkoutDbAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Log.d(LOG, "onCreate");


        mUserDbAdapter = new UserDbAdapter(this);
        try{
            mUserDbAdapter.open();
        }
        catch(SQLException e) {
            Log.e(LOG, "user table open error");
        }

        mExerciseDbAdapter = new ExerciseDbAdapter(this);
        try{
            mExerciseDbAdapter.open();
        }
        catch(SQLException e) {
            Log.e(LOG, "exercise table open error");
        }

        mWorkoutDbAdapter = new WorkoutDbAdapter(this);
        try{
            mWorkoutDbAdapter.open();
        }
        catch(SQLException e) {
            Log.e(LOG, "workout table open error");
        }



        //--------------------USER-----------------------//

        mUserDbAdapter.removeAllUsers();

        // Creating User
        User user = new User("John", "Smith", "Beefcake");
        User user2 = new User("Bob", "MacNamara", "Brutus");

        // Inserting user in db
        long user_id = mUserDbAdapter.createUser(user);
        long user_id2 = mUserDbAdapter.createUser(user2);

        // Read user
        User userOut = mUserDbAdapter.getUser(user_id);
        Log.d(LOG, "user= " + user.getUserName());

        // all users
        List<User> users = mUserDbAdapter.getAllUsers();
        for ( User u : users ) {
            Log.d(LOG, u.toString());
        }

        // delete User
        mUserDbAdapter.removeUser("Brutus");


        // all users
        HashMap<Long, String> userIdMap = new HashMap<>();
        users = mUserDbAdapter.getAllUsers();
        for ( User u : users ) {
            Log.d(LOG, u.toString());
            userIdMap.put(user.getId(), user.getUserName());
        }


        // update user
        user.setFirstName("JoeyJoJo");
        mUserDbAdapter.updateUser(user_id, user);

        // all users
        users = mUserDbAdapter.getAllUsers();
        for ( User u : users ) {
            Log.d(LOG, u.toString());
        }

        //--------------------EXERCISE-----------------------//

        // Create Exercise
        Exercise ex1 = new Exercise("unDuctor", "Tricep", user_id);
        Exercise ex2 = new Exercise("Bench Press", "Chest", user_id);

        long ex_id1 = mExerciseDbAdapter.createExercise(ex1);
        long ex_id2 = mExerciseDbAdapter.createExercise(ex2);

        // Create Workout
        Workout w1 = new Workout(System.currentTimeMillis(), ex_id1);
        Workout w2 = new Workout(System.currentTimeMillis(), ex_id2);

        w1.setReps("10,8,6");
        w1.setSets(3);
        w1.setWeight("80,90,100");


        long w_id1 = mWorkoutDbAdapter.createWorkout(w1);
        long w_id2 = mWorkoutDbAdapter.createWorkout(w2);


        //Log.d("Tag Count", "Tag Count: " + db.getAllTags().size());
/*
        // Creating ToDos
        Todo todo1 = new Todo("iPhone 5S", 0);
        Todo todo2 = new Todo("Galaxy Note II", 0);
        Todo todo3 = new Todo("Whiteboard", 0);

        Todo todo4 = new Todo("Riddick", 0);
        Todo todo5 = new Todo("Prisoners", 0);
        Todo todo6 = new Todo("The Croods", 0);
        Todo todo7 = new Todo("Insidious: Chapter 2", 0);

        Todo todo8 = new Todo("Don't forget to call MOM", 0);
        Todo todo9 = new Todo("Collect money from John", 0);

        Todo todo10 = new Todo("Post new Article", 0);
        Todo todo11 = new Todo("Take database backup", 0);

        // Inserting todos in db
        // Inserting todos under "Shopping" Tag
        long todo1_id = db.createToDo(todo1, new long[] { tag1_id });
        long todo2_id = db.createToDo(todo2, new long[] { tag1_id });
        long todo3_id = db.createToDo(todo3, new long[] { tag1_id });

        // Inserting todos under "Watchlist" Tag
        long todo4_id = db.createToDo(todo4, new long[] { tag3_id });
        long todo5_id = db.createToDo(todo5, new long[] { tag3_id });
        long todo6_id = db.createToDo(todo6, new long[] { tag3_id });
        long todo7_id = db.createToDo(todo7, new long[] { tag3_id });

        // Inserting todos under "Important" Tag
        long todo8_id = db.createToDo(todo8, new long[] { tag2_id });
        long todo9_id = db.createToDo(todo9, new long[] { tag2_id });

        // Inserting todos under "Androidhive" Tag
        long todo10_id = db.createToDo(todo10, new long[] { tag4_id });
        long todo11_id = db.createToDo(todo11, new long[] { tag4_id });

        Log.e("Todo Count", "Todo count: " + db.getToDoCount());*/

        mExerciseDbAdapter.close();
        mUserDbAdapter.close();
        mWorkoutDbAdapter.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
