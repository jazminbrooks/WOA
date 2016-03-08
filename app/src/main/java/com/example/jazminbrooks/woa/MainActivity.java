package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.jazminbrooks.woa.Data.ExerciseContent;
import com.firebase.client.Firebase;

// Max Testing Git Commit...

public class MainActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    private Button button;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Firebase.setAndroidContext(this);
        Firebase myFirebaseRef= new Firebase("https://vivid-inferno-8916.firebaseio.com/");

        ExerciseContent.updateItems(myFirebaseRef);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button launchSignup = (Button)(findViewById(R.id.signupbutton));
        launchSignup.setOnClickListener(this);
        Button launchLogin = (Button)(findViewById(R.id.loginbutton));
        launchLogin.setOnClickListener(this);
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


    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.loginbutton:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.signupbutton:
                startActivity(new Intent(this, Signup.class));
                break;
        }
    }
}
