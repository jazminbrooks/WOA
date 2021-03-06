package com.example.jazminbrooks.woa;

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

public class ChangeEmail extends AppCompatActivity implements android.view.View.OnClickListener{

    SharedPreferences mSharedPreferences;
    EditText mOld;
    EditText mNew;
    EditText mConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSharedPreferences = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);
        Button change = (Button)findViewById(R.id.changeButton);
        change.setOnClickListener(this);
        mOld = (EditText)findViewById(R.id.currentEmailField);
        mNew = (EditText)findViewById(R.id.newEmailField);
        mConfirm = (EditText)findViewById(R.id.confirmEmailField);


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
        String oldSavedValue = mSharedPreferences.getString("email", "no email set");
        if(!oldSavedValue.equals("no email set")){
            if(!mOld.getText().toString().equals("") && oldSavedValue.equals(mOld.getText().toString())){
                if(!mNew.getText().toString().equals("") && mNew.getText().toString().equals(mConfirm.getText().toString())){
                    SharedPreferences.Editor editor = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE).edit();
                    editor.remove("email");
                    editor.putString("email", mNew.getText().toString());
                    editor.commit();
                    Toast.makeText(this, "email Updated", Toast.LENGTH_SHORT).show();
                    mNew.setText("");
                    mOld.setText("");
                    mConfirm.setText("");
                } else {
                    Toast.makeText(this, "email do not Match!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Current email Doesn't Match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No Saved Account, please create account first!", Toast.LENGTH_SHORT).show();
        }
    }
}
