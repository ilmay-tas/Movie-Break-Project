import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle.Control;

public class Session implements Search {
    Controller controller;
    Chat chat;
    Date start;
    Date finish;
    int sessionID;
    int quota;
    Movie movie;
    String name;
    TimeTask timer;

    public ArrayList<Integer> search(String input)
    {
        int length = input.length();
        if (name.substring(length).equals(input))
        {
            
        }
    }
}
