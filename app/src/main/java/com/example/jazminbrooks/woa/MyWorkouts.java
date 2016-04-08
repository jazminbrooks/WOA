package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.example.jazminbrooks.woa.Data.WorkoutContent;

public class MyWorkouts extends AppCompatActivity implements WorkoutFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_workouts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyWorkouts.this, BuildWorkout.class);
                startActivity(intent);
            }
        });
    }

    public void onListFragmentInteraction(WorkoutContent.Workout workout) {
        WorkoutContent.setCurrentWorkout(workout);
        Intent intent = new Intent(this, WorkoutDetails.class);
        startActivity(intent);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeScreen.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
