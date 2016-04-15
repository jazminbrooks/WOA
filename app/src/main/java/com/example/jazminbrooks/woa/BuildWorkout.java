package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.example.jazminbrooks.woa.Data.WorkoutContent;

public class BuildWorkout extends AppCompatActivity implements BuildWorkoutFragment.OnListFragmentInteractionListener{

    private EditText mNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_workout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WorkoutContent.initNewWorkout();

        mNameEditText = (EditText) findViewById(R.id.build_workout_name);

        Button createWorkout = (Button) findViewById(R.id.create_workout);
        createWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (WorkoutContent.NEW_EXERCISES.size() > 0) {
                    WorkoutContent.createNewWorkout(mNameEditText.getText().toString());
                    Intent intent = new Intent(BuildWorkout.this, MyWorkouts.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(BuildWorkout.this, "Workout must have at least one exercise.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onListFragmentInteraction(ExerciseContent.Exercise exercise) {
        Intent intent = new Intent(this, ExerciseDetails.class);
        ExerciseDetails.mExercise = exercise;
        ExerciseDetails.mLastActivity = this;
        startActivity(intent);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MyWorkouts.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
