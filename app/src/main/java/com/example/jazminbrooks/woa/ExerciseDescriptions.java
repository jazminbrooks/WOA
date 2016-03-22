package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jazminbrooks.woa.Data.ExerciseContent;

public class ExerciseDescriptions extends AppCompatActivity implements ExerciseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_descriptions);

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.list, new ExerciseFragment())
                    .commit();
        }
        */
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      setSupportActionBar(toolbar);


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
        //intent.putExtra(ExerciseDetails.NAME,exercise.getName());
        ExerciseDetails.mExercise = exercise;
        startActivity(intent);

    }

}
