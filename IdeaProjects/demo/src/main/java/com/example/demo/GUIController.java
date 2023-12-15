package com.example.demo;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;


public class GUIController{
    private static int id;
    private Scene scene;
    private Stage stage;
    private Parent root;
    Firebase fb = new Firebase();
    @FXML
    private Button insert;

    @FXML
    private Button backIn;

    @FXML
    private  Button backUp;

    @FXML
    private TextField pass;

    @FXML
    private TextField ar;
    @FXML
    private TextField userN;

    @FXML
    private VBox friends;

    @FXML
    private Label label;

    @FXML
    private Button in;

    @FXML
    private Button up;

    @FXML
    private ImageView profilePhoto ;

    @FXML
    private Label label1;

    @FXML
    private ImageView view1;

    @FXML 
    private Label label2;

    @FXML
    private ImageView view2;

    @FXML 
    private Label label3;

    @FXML
    private ImageView view3;

    @FXML 
    private Label label4;

    @FXML
    private ImageView view4;

    @FXML 
    private Label label5;

    @FXML
    private ImageView view5;

    @FXML 
    private Label label6;

    @FXML
    private ImageView view6;

    @FXML 
    private Label label7;

    @FXML
    private ImageView view7;

    @FXML 
    private Label label8;

    @FXML
    private ImageView view8;

    @FXML 
    private Label label9;

    @FXML
    private ImageView view9;

    @FXML 
    private Label label10;

    @FXML
    private ImageView view10;

    @FXML 
    private Label mslabel1;

    @FXML
    private ImageView msview1;

    @FXML 
    private Label mslabel2;

    @FXML
    private ImageView msview2;

    @FXML
    private Button movierefreshbutton;

    @FXML
    private Button friendrefreshbutton;

    @FXML
    private TextField textFieldSearch;

    @FXML
    private Text message;

    private String user;

    private String[] movieIDs = new String[5];

    public void helperChange(String[] ids){
        CompletableFuture<String> ctitle = new CompletableFuture<>();
        String title = "";
        for (int i = 0; i < ids.length; i++) {
            BufferedImage cposter = loadMoviePoster(ids[i]);
            ctitle = loadMovieName(ids[i]);
            title = ctitle.join();
            if(i==0){
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view1.setImage(posterImage);
                label1.setText(title);
            }
            else if(i==1){
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view2.setImage(posterImage);
                label2.setText(title); 
            }
            else if (i==2){
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view3.setImage(posterImage);
                label3.setText(title); 
            }
            else if (i==3){
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view4.setImage(posterImage);
                label4.setText(title); 
            }
            else {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view5.setImage(posterImage);
                label5.setText(title); 
            }
        }
    } 
    //refreshFriend
    public void refreshMovie() {
        String[] ids = {"155", "240", "238", "8871", "10908"};
        movieIDs = ids;
        helperChange(movieIDs);
    }

    public void displayImage(MouseEvent e){
        String[] ids = {"156022", "298618", "360920", "414906", "385687"};
        movieIDs = ids;
        helperChange(movieIDs);
    }

    public void displaying(){
        label1.setText("dskjfjö");
        label2.setText("vdjvkm");
    }

    public BufferedImage loadMoviePoster(String movieId) {
        BufferedImage img = null;
        String imagePath = "IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\movieImages\\" + movieId + ".jpg";
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);  
            System.out.println("image is assigned");
            System.out.println("path is "+ imagePath);

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());  
        }
        return img;
    }
    
    public CompletableFuture<String> loadMovieName(String movieId) {
        DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("movies/" + movieId + "/title");

        CompletableFuture<String> future = new CompletableFuture<>();

        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                String movietitle = dataSnapshot.getValue(String.class);
                if (movietitle != null) {
                    future.complete(movietitle);
                } else {
                    future.completeExceptionally(new Exception("title not found"));
                }
            }

            public void onCancelled(DatabaseError databaseError) {
                future.completeExceptionally(new Exception(databaseError.getMessage()));
            }
        });

        return future;
    }
    

    public void insert(ActionEvent e)
    {
        if (userN.getText().equals("") || pass.getText().equals("")) 
        {
            message.setText("Empty Password or Username");
            message.setFill(Color.rgb(139, 0, 0));
        }
        else
        {
            DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) 
                    {
                        Object value = dataSnapshot.getValue();
                        takeUserID(value);
                    }
                    else 
                    {
                        takeUserID(0);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error: " + databaseError.getMessage());
                }
            });
        }
    }


    public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText() , pass.getText() ))
        {
            changeMainPage(e);
        }
    }

    public void takeUserID(Object value)
    {
        id = Integer.parseInt(""+value) ;
        if (fb.userPush(userN.getText() , pass.getText() , id)) 
        {
            message.setText("You are signed up");
            message.setFill(Color.rgb(34,139,34));
        }
        else 
        {
            message.setText("You are signed up");
            message.setFill(Color.rgb(139,0,0));
        }
    }

    public void changeIn(ActionEvent e) throws IOException
    {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.show();
    }

    public void changeUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeMainPage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow() ;
        scene = new Scene(root) ;
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openProfileSettings(ActionEvent e) throws IOException 
    {
        root = FXMLLoader.load(getClass().getResource("profilePage.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
