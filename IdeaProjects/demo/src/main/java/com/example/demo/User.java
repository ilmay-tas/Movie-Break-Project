import java.lang.ModuleLayer.Controller;
import java.util.ArrayList;

public class User implements Search{
    Controller controller;
    String userName;
    String password;
    String userID;
    ArrayList<String> favMoviesIDs;
    ArrayList<String> friendsIDs;
    ArrayList<String> chatIDs;
    ArrayList<String> sessionIDs;

    public User(String userName, String password, String userID)
    {
        setUserName(userName);
        setPassword(password);
        setID(userID);
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getName()
    {
        return userName;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }

    public void setID(String userID)
    {
        this.userID = userID;
    }

    public String getID()
    {
        return userID;
    }

    public ArrayList<String> getFriendsIDs()
    {
        return this.friendsIDs;
    }

    public void addFriendID(String userID)
    {
        friendsIDs.add(userID);
    }

    public void addFriend(String userID)
    {
        addFriendID(userID);
        controller.createChat();
    }

    public void removeFriend(String userID)
    {
        friendsIDs.remove(userID);
    }

    public ArrayList<String> getFavMoviesIDs()
    {
        return this.favMoviesIDs;
    }

    public void addFavMovie(String movieID)
    {
        favMoviesIDs.add(movieID);
    }

    public void removeFavMovie(String movieID)
    {
        favMoviesIDs.remove(movieID);
    }

    public ArrayList<String> getChatIDs()
    {
        return this.chatIDs;
    }

    public ArrayList<String> getSessionIDs()
    {
        return this.sessionIDs;
    }

    public void changeUserName (String newUserName)
    {
        setUserName(newUserName);
    }

    public void changePassword (String newPassword)
    {
        setPassword(newPassword);
    }

    public ArrayList<Integer> search(String input)
    {
        return;
    }
}