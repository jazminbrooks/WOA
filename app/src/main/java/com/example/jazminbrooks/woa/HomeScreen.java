package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeScreen extends AppCompatActivity implements android.view.View.OnClickListener{

    private TextView textGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button launchChatRoom = (Button)(findViewById(R.id.ChatRoomButton));
        launchChatRoom.setOnClickListener(this);
        Button launchExerciseDescriptions = (Button)(findViewById(R.id.ExerciseDescriptionsButton));
        launchExerciseDescriptions.setOnClickListener(this);
        Button launchLocationButton = (Button)(findViewById(R.id.LocationsButton));
        launchLocationButton.setOnClickListener(this);
        Button launchBuildAWorkoutButton = (Button)(findViewById(R.id.BuildAWorkoutButton));
        launchBuildAWorkoutButton.setOnClickListener(this);
        Button launchMyAccount = (Button)(findViewById(R.id.MyAccountButton));
        launchMyAccount.setOnClickListener(this);
        Button logout = (Button)(findViewById(R.id.LogoutButton));
        logout.setOnClickListener(this);

        textGreeting = (TextView)findViewById(R.id.GreetingText);
        SharedPreferences prefs = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);
        String username  = prefs.getString("username", "user");
        textGreeting.setText("Hello " + username + "! You have 2000 points!");


    }

    public void onResume(){
        super.onResume();
        textGreeting = (TextView)findViewById(R.id.GreetingText);
        SharedPreferences prefs = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);
        String username  = prefs.getString("username", "user");
        textGreeting.setText("Hello " + username + "! You have 2000 points!");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LogoutButton:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.MyAccountButton:
                startActivity(new Intent(this, MyAccount.class));
                break;
            case R.id.ChatRoomButton:
                startActivity(new Intent(this, ChatRooms.class));
                break;
            case R.id.BuildAWorkoutButton:
                startActivity(new Intent(this, BuildAWorkout.class));
                break;
            case R.id.LocationsButton:
                startActivity(new Intent(this, Locations.class));
                break;
            case R.id.ExerciseDescriptionsButton:
                startActivity(new Intent(this, ExerciseDescriptions.class));
                break;
        }
    }
}
