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

public class WorkoutDetails extends AppCompatActivity implements WorkoutDetailsFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("WorkoutDetails -> OnCreateView()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(WorkoutContent.CURRENT_WORKOUT.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    public void onListFragmentInteraction(ExerciseContent.Exercise exercise) {
        Intent intent = new Intent(this, ExerciseDetails.class);
        ExerciseDetails.mExercise = exercise;
        startActivity(intent);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MyWorkouts.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
