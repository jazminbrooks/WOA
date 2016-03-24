package com.example.jazminbrooks.woa;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ChatRoomSelect extends Activity implements android.view.View.OnClickListener{

    Button mStartButton;
    EditText mChatRoomInput;
    ArrayList<String> listOfRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room_select);
        mStartButton = (Button)findViewById(R.id.StartButton);
        mStartButton.setOnClickListener(this);
        mChatRoomInput = (EditText)findViewById(R.id.ChatRoomInput);


        listOfRooms = new ArrayList<String>();

        Firebase firebase = new Firebase("https://vivid-inferno-8916.firebaseio.com/").child("Chatrooms");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("IN ON DATA CHANGE");
                System.out.println(snapshot.getValue());
                for (DataSnapshot entry : snapshot.getChildren()) {
                    System.out.println(entry.getKey());
                    listOfRooms.add(entry.getKey());
                }
                for (String i : listOfRooms) {
                    System.out.println(i);
                }

                String[] toReturn = new String[listOfRooms.size()];
                toReturn = listOfRooms.toArray(toReturn);
                ArrayAdapter adapter = new ArrayAdapter<String>(ChatRoomSelect.this, R.layout.activity_listview, toReturn);
                ListView listView = (ListView) findViewById(R.id.ListOfRooms);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }

        });


    }


    private void startChatRoom(){

        String chatroom = mChatRoomInput.getText().toString();

        if(!chatroom.equals("")){
            SharedPreferences.Editor editor = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE).edit();
            editor.putString("chatroom", chatroom);
            editor.commit();
            startActivity(new Intent(this, ChatRooms.class));
        } else {
            Toast.makeText(this, "Enter a Room Name!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.StartButton:
                startChatRoom();
                break;
        }
    }
}
