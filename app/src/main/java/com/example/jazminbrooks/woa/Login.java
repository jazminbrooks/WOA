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
import android.widget.Toast;

public class Login extends AppCompatActivity implements android.view.View.OnClickListener{

    SharedPreferences mPrefs;
    EditText mEmailField;
    EditText mPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPrefs = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);


        Button launchHomeScreen = (Button)(findViewById(R.id.submitButton));
        launchHomeScreen.setOnClickListener(this);

        mEmailField = (EditText)findViewById(R.id.emailField);
        mPasswordField = (EditText)findViewById(R.id.passwordField);

    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.submitButton:
                CheckSignup();
                break;
        }
    }

    private void CheckSignup() {

        String enteredPassword = mPasswordField.getText().toString();
        String enteredEmail = mEmailField.getText().toString();
        String savedPassword = mPrefs.getString("password", "NOT THE CORRECT PASSWORD");
        String savedEmail = mPrefs.getString("email", "NOT THE CORRECT EMAIL");

        if(!savedEmail.equals("NOT THE CORRECT EMAIL") && !savedPassword.equals("NOT THE CORRECT PASSWORD")){
            if(!enteredEmail.equals("") && !enteredPassword.equals("")){
                if(enteredEmail.equals(savedEmail) && enteredPassword.equals(savedPassword)){
                    startActivity(new Intent(this, HomeScreen.class));
                } else {
                    Toast.makeText(this, "Entries do not match!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Missing Entry!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Saved Account, please create account first!", Toast.LENGTH_SHORT).show();
        }

    }
}
