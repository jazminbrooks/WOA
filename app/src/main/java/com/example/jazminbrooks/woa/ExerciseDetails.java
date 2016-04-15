package com.example.jazminbrooks.woa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.jazminbrooks.woa.Data.ExerciseContent;

public class ExerciseDetails extends AppCompatActivity {

    public static ExerciseContent.Exercise mExercise;
    public static Activity mLastActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView nameText = (TextView) findViewById(R.id.name_d);
        nameText.setText(mExercise.getName());

        TextView typeText = (TextView) findViewById(R.id.type_d);
        typeText.setText(mExercise.getType());

        TextView descriptionText = (TextView) findViewById(R.id.description_d);
        descriptionText.setText(mExercise.getDescription());

        TextView setsText = (TextView) findViewById(R.id.sets_d);
        setsText.setText(mExercise.getSets());

        TextView repsText = (TextView) findViewById(R.id.reps_d);
        repsText.setText(mExercise.getReps());

        TextView videoText = (TextView) findViewById(R.id.video_d);
        videoText.setClickable(true);
        videoText.setMovementMethod(LinkMovementMethod.getInstance());
        videoText.setText(Html.fromHtml("<a href=\"" + mExercise.getVideo() + "\">" + mExercise.getName() + "</a>"));
        //videoText.setLinksClickable(true);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), mLastActivity.getClass());
        startActivityForResult(myIntent, 0);
        return true;

    }
}
