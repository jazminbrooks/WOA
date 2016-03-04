package com.example.jazminbrooks.woa;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Signup extends AppCompatActivity implements android.view.View.OnClickListener{


    private EditText ETEmail;
    private EditText ETUsername;
    private EditText ETPassword;
    private EditText ETConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ETEmail = (EditText)findViewById(R.id.emailInput);
        ETUsername = (EditText)findViewById(R.id.usernameInput);
        ETPassword= (EditText)findViewById(R.id.passwordInput);
        ETConfirm = (EditText)findViewById(R.id.passwordConfirmInput);

        Button launchHomeScreen = (Button)(findViewById(R.id.submit));
        launchHomeScreen.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void CreateAccount(){
        String email = ETEmail.getText().toString();
        String username = ETUsername.getText().toString();
        String password	= ETPassword.getText().toString();
        String confirmPassword	= ETConfirm.getText().toString();

        if(password.equals(confirmPassword) && !username.equals("") && !password.equals("") && !email.equals("")){
            SharedPreferences.Editor editor = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE).edit();
            editor.putString("email", email);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.commit();

            startActivity(new Intent(this, HomeScreen.class));

        }else if((username.equals(""))||(password.equals(""))||(confirmPassword.equals(""))){
            Toast.makeText(this, "Missing entry", Toast.LENGTH_SHORT).show();
        }
        else if(!password.equals(confirmPassword)){
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("passwords do not match")
                    .setNeutralButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {}
                    })

                    .show();
        }

    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.submit:
                CreateAccount();
                break;
        }
    }
}
