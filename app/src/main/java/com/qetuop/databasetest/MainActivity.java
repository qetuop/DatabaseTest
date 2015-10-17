package com.qetuop.databasetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.sql.SQLException;

import android.util.Log;


public class MainActivity extends AppCompatActivity {

    private static final String LOG = "MainActivity";

    private ExerciseDAO mExerciseDAO;
    private TypeDAO mTypeDAO;

    // Database Helper
    //DBHelper db;
    private TypeDbAdapter mTypeDbAdapter;
    private ExerciseDbAdapter mExerciseDbAdapter;

    AbstractDbAdapter mDbAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Log.d(LOG, "onCreate");

        //db = new DatabaseHelper(getApplicationContext());
        //mExerciseDAO = new ExerciseDAO(this);
       // mTypeDAO = new TypeDAO(this);

        mTypeDbAdapter = new TypeDbAdapter(this);
        //mTypeDbAdapter.open();  // why do other examples not try/catch this?!?
        try{
            mTypeDbAdapter.open();
        }
            catch(SQLException e) {
                Log.e(LOG, "type table open error");
        }

        mExerciseDbAdapter = new ExerciseDbAdapter(this);
        try{
            mExerciseDbAdapter.open();
        }
        catch(SQLException e) {
            Log.e(LOG, "exercise table open error");
        }

/*        mDbAdapter = new AbstractDbAdapter(this);
        try{
            mDbAdapter.open();
        }
        catch(SQLException e) {
            Log.e(LOG, "mDbAdapter table open error");
        }*/

        // Creating Types
        Type type1 = new Type("None");
        Type type2 = new Type("Chest");
/*        Type type3 = new Type("Back");
        Type type4 = new Type("Biceps");
        Type type5 = new Type("Triceps");
        Type type6 = new Type("Arms");
        Type type7 = new Type("Legs");
        Type type8 = new Type("Abs");
        Type type9 = new Type("Core");*/

        // Inserting types in db
        long type1_id = mTypeDbAdapter.createType(type1);
        long type2_id = mTypeDbAdapter.createType(type2);


        Exercise ex1 = new Exercise("unDuctor", type1_id);
        Exercise ex2 = new Exercise("Bench Press", type2_id);

        mExerciseDbAdapter.createExercise(ex1);
        mExerciseDbAdapter.createExercise(ex2);

        Log.d(LOG, String.valueOf(mExerciseDbAdapter.mDatabaseHelper));
        Log.d(LOG, String.valueOf(mTypeDbAdapter.mDatabaseHelper) );


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
