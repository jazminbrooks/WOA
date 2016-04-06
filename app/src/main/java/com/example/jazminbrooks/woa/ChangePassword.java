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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChangePassword extends AppCompatActivity implements android.view.View.OnClickListener{

    SharedPreferences mSharedPreferences;
    EditText mOld;
    EditText mNew;
    EditText mConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSharedPreferences = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);
        Button change = (Button)findViewById(R.id.changeButton);
        change.setOnClickListener(this);
        mOld = (EditText)findViewById(R.id.currentPasswordField);
        mNew = (EditText)findViewById(R.id.newPasswordField);
        mConfirm = (EditText)findViewById(R.id.confirmPasswordField);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeButton:
                MakeChange();
                break;
        }

    }

    private void MakeChange() {
        String oldSavedValue = mSharedPreferences.getString("password", "no pass set");
        if(!oldSavedValue.equals("no pass set")){
            if(!mOld.getText().toString().equals("") && oldSavedValue.equals(generate_hashed_password(mOld.getText().toString()))){
                if(!mNew.getText().toString().equals("") && mNew.getText().toString().equals(mConfirm.getText().toString())){
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE).edit();
                    editor.remove("password");
                    editor.putString("password", generate_hashed_password(mNew.getText().toString()));
                    editor.commit();
                    Toast.makeText(this, "Password Updated", Toast.LENGTH_SHORT).show();
                    mNew.setText("");
                    mOld.setText("");
                    mConfirm.setText("");
                } else {
                    Toast.makeText(this, "Passwords do not Match!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Current Password Doesn't Match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Saved Account, please create account first!", Toast.LENGTH_SHORT).show();
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
