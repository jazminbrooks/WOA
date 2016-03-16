package com.example.jazminbrooks.woa.Data;

// Class taken from github.com/firebase/AndroidChat


/**
 * Created by Aaron on 3/16/2016.
 */
public class Chat {


    private String message;
    private String author;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat(){

    }

    public Chat(String message, String author){
        this.message = message;
        this.author = author;
    }

    public String getMessage(){
        return message;
    }

    public String getAuthor(){
        return author;
    }

}
