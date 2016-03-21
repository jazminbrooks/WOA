package com.example.jazminbrooks.woa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyAccount extends AppCompatActivity implements android.view.View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button changeUsername = (Button)(findViewById(R.id.changeUsernameButton));
        changeUsername.setOnClickListener(this);
        Button changePassword = (Button)(findViewById(R.id.changePasswordButton));
        changePassword.setOnClickListener(this);
        Button changeEmail = (Button)(findViewById(R.id.changeEmailButton));
        changeEmail.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changeEmailButton:
                startActivity(new Intent(this, ChangeEmail.class));
                break;
            case R.id.changePasswordButton:
                startActivity(new Intent(this, ChangePassword.class));
                break;
            case R.id.changeUsernameButton:
                startActivity(new Intent(this, ChangeUsername.class));
                break;

        }
    }

}
