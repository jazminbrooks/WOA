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
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public void onResume(){
        super.onResume();
        System.out.println("Resuming Signup");
        EditText emailInput = (EditText)findViewById(R.id.emailInput);
        EditText usernameInput = (EditText)findViewById(R.id.usernameInput);
        EditText passwordInput = (EditText)findViewById(R.id.passwordInput);
        EditText passwordConfirmInput = (EditText)findViewById(R.id.passwordConfirmInput);

        emailInput.setText("");
        usernameInput.setText("");
        passwordInput.setText("");
        passwordConfirmInput.setText("");
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
            editor.putString("password", generate_hashed_password(password));
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

    private String generate_hashed_password(String password_to_hash){
        String passwordToHash = password_to_hash;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

        return generatedPassword;
        //System.out.println(generatedPassword);
    }
}
