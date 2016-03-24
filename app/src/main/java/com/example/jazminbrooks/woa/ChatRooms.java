package com.example.jazminbrooks.woa;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jazminbrooks.woa.Data.Chat;
import com.example.jazminbrooks.woa.Data.ChatListAdapter;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ChatRooms extends ListActivity implements android.view.View.OnClickListener{

    private TextView ETUsername;
    private TextView mChatRoomID;
    private EditText ETMessageInput;
    private Button BSendButton;
    private String mUsername;
    private String mChatRoomName;
    private Firebase mFirebase;
    private ChatListAdapter mChatListAdapter;
    private ValueEventListener mConnectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);

        BSendButton = (Button)findViewById(R.id.SendButton);
        BSendButton.setOnClickListener(this);
        ETMessageInput = (EditText)findViewById(R.id.MessageInput);


        ETUsername = (TextView)findViewById(R.id.usernameGreeting);
        mChatRoomID = (TextView)findViewById(R.id.chatRoomName);
        SharedPreferences prefs = getSharedPreferences("com.example.jazminbrooks.woa", MODE_PRIVATE);
        mUsername  = prefs.getString("username", "user");
        mChatRoomName = prefs.getString("chatroom", "Room3");
        ETUsername.setText("Visible ID: " + mUsername);
        mChatRoomID.setText("Chat Room: " + mChatRoomName);

        mFirebase = new Firebase("https://vivid-inferno-8916.firebaseio.com/").child("Chatrooms").child(mChatRoomName);



    }

    @Override
    public void onStart(){
        super.onStart();

        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        mChatListAdapter = new ChatListAdapter(mFirebase.limit(50), this, R.layout.chat_message, mUsername);
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = mFirebase.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(ChatRooms.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatRooms.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });

    }


    private void sendMessage(){

        String input = ETMessageInput.getText().toString();
        String author = mUsername;

        if (!input.equals("")){
            Chat chat = new Chat(input, author);

            mFirebase.push().setValue(chat);
            ETMessageInput.setText("");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SendButton:
                sendMessage();
                break;
        }
    }
}
