package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jazminbrooks.woa.Data.ExerciseContent;

public class ExerciseDescriptions extends AppCompatActivity implements ExerciseFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_descriptions);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

    }

    public void onListFragmentInteraction(ExerciseContent.Exercise exercise) {
        Intent intent = new Intent(this, ExerciseDetails.class);
        ExerciseDetails.mExercise = exercise;
        startActivity(intent);

    }

}
