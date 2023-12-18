package com.example.demo;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Chat {
    Controller controller;
    String chatID;
    ArrayList<String> userIDs;
    ArrayList<Message> messages;

    public Chat (String ID)
    {
        this.chatID = ID;
        /*for (int i = 0; i < users.size(); i++)
        {
            userIDs.add(users.get(i));
        }*/
    }
    public String getID()
    {
        return this.chatID;
    }

    public ArrayList<String> getUsers()
    {
        return this.userIDs;
    }
    public ArrayList<Message> getMessages()
    {
        return this.messages;
    }
    public void displayMessages(int bound, String currentUserID, TextArea text1, TextArea text2, Label label1,Label label2){
        for(int i = bound; i > bound - 4; i--){
            if(messages.get(i).getUserID() == currentUserID){
                text1.setText(messages.get(i).displayText());
                label1.setText(messages.get(i).displayHeadTitle());
            }
            else{
                text2.setText(messages.get(i).displayText());
                label2.setText(messages.get(i).displayHeadTitle());
            }
        }
    }
}
