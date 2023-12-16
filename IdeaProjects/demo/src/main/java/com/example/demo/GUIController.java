package com.example.demo;
import javafx.scene.control.MenuItem;
import com.example.demo.Firebase.FirebaseDataCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
public class GUIController {
    private User currentUser ; 
    private static int id;
    private Scene scene;
    private Stage stage;
    private Parent root;
    private int searchc=0;
    private ArrayList<Movie> moviesStore;
    Firebase fb = new Firebase(new FirebaseDataCallback() {
        @Override
        public void onDataLoaded(ArrayList<Movie>movies) {
            moviesStore = movies;
        }
    });
    
    @FXML
    private ComboBox<String> menu;

    @FXML
    private Button insert;

    @FXML
    private Button backIn;

    @FXML
    private Button backUp;

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
    private ImageView profilePhoto;

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
    
    @FXML
    private ListView<Movie> searchResultsListView; 
    // Sonuçları göstermek için bir ListView bileşeni
    @FXML
    private ObservableList<Integer> movieIds;
    
    private String user;
    private int index = 5;
    private String[] movieIDs = new String[5];
    //movie searchlemek için
    public List<Movie> performSearch(String searchText) {
        String trimmedSearchText = searchText.trim().toLowerCase();
        // Başlayan filmleri bulmak için bir filtre kullanın
        List<Movie> searchResults = fb.movies.stream()
                .filter(movie -> movie.getTitle().toLowerCase().startsWith(trimmedSearchText))
                .collect(Collectors.toList());
        return searchResults;
    }
    //moviesearchlemek için
    @FXML
    private void handleSearch(ActionEvent event) {
        String searchText = textFieldSearch.getText().trim();
        if (!searchText.isEmpty()) {
            List<Movie> searchResults = performSearch(searchText);
            movieIds = FXCollections.observableArrayList();
            for (Movie movie : searchResults) {
                movieIds.add(movie.takeId());
            }
            // Sonuçları arayüzde gösterme kodu
            searchResultsListView.getItems().setAll(searchResults);
        }
    }
    public void helperChange(String[] ids) {
        CompletableFuture<String> ctitle = new CompletableFuture<>();
        String title = "";
        for (int i = 0; i < ids.length; i++) {
            BufferedImage cposter = loadMoviePoster(ids[i]);
            ctitle = loadMovieName(ids[i]);
            title = ctitle.join();
            if (i == 0) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view1.setImage(posterImage);
                label1.setText(title);
            } else if (i == 1) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view2.setImage(posterImage);
                label2.setText(title);
            } else if (i == 2) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view3.setImage(posterImage);
                label3.setText(title);
            } else if (i == 3) {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view4.setImage(posterImage);
                label4.setText(title);
            } else {
                Image posterImage = SwingFXUtils.toFXImage(cposter, null);
                view5.setImage(posterImage);
                label5.setText(title);
            }
        }
    } 
    //refreshFriend
    //public void refreshFriend(){}
    public void refreshMovie(ActionEvent e) {
        System.out.println("id" +movieIDs.toString());
        int counter = 0 ;
        int c = currentUser.recommend().size()%5 ;
        for (int i = index ; i < 5 + index && i < currentUser.recommend().size() ; i++) 
        {
            movieIDs[counter] = currentUser.recommend().get(i) ;
            counter ++ ; 
        }
        index += counter ;
        if (index == currentUser.recommend().size() && c > 0) 
        { 
            String[] shortM = new String[c] ;
            for (int i = 0 ; i < c ; i++) 
            {
                shortM[i] = movieIDs[i] ;
            }
            helperChange(shortM);
        }
        else 
        {
            helperChange(movieIDs);
        }
    }

    public void displayImage(MouseEvent e){
        String[] ids = {"156022", "298618", "360920", "414906", "385687"};
        movieIDs = ids;
        helperChange(movieIDs);
        if(searchc == 0){
            menu.getItems().addAll("Friend Search","Movie Search","Session Search");
        }
        searchc = 1;
    }

    public BufferedImage loadMoviePoster(String movieId) {
        BufferedImage img = null;
        String imagePath = "IdeaProjects\\demo\\src\\main\\resources\\com\\example\\demo\\movieImages\\" + movieId
                + ".jpg";
        try {
            File imageFile = new File(imagePath);
            img = ImageIO.read(imageFile);  
            System.out.println("image is assigned");
            System.out.println("path is " + imagePath);

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

    public void insert(ActionEvent e) {
        if (userN.getText().equals("") || pass.getText().equals("")) {
            message.setText("Empty Password or Username");
            message.setFill(Color.rgb(139, 0, 0));
        } else {
            DatabaseReference data = FirebaseDatabase.getInstance().getReference("users/ID-Counter");
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        Object value = dataSnapshot.getValue();
                        takeUserID(value);
                    } else {
                        takeUserID(0);
                    }
                }

                public void onCancelled(DatabaseError databaseError) {
                    System.err.println("Error: " + databaseError.getMessage());
                }
            });
        }
    }

    public void check(ActionEvent e) throws IOException {
        if (fb.hasAcc(userN.getText(), pass.getText())) {
            currentUser = fb.getUser() ;
            changeMainPage(e);
        }
    }

    public void takeUserID(Object value) {
        id = Integer.parseInt("" + value);
        if (fb.userPush(userN.getText(), pass.getText(), id)) {
            message.setText("You are signed up");
            message.setFill(Color.rgb(34, 139, 34));
        } else {
            message.setText("You are signed up");
            message.setFill(Color.rgb(139, 0, 0));
        }
    }

    public void changeIn(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signIn.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeUp(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("signUp.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void changeMainPage(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void backToMain(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("welcomePage.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openProfileSettings(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile3.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openCalender(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sessionCalender.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void openChat(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("chat.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public void openSearchPage(ActionEvent e) throws IOException {
        if(menu.getValue() == "Friend Search"){
            root = FXMLLoader.load(getClass().getResource("userSearchPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else if(menu.getValue() == "Movie Search"){
            root = FXMLLoader.load(getClass().getResource("movieSearchPage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else if(menu.getValue() == "Session Search"){
            root = FXMLLoader.load(getClass().getResource("sessionSearchPage.fxml"));
            stage = (Stage)((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        }
        else {
            menu.setPromptText("Select Type First");
        }
    }
    public void openProfile1(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile1.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public void openProfile2(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile2.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public void openProfile3(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("profile3.fxml"));
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void exit(ActionEvent e) 
    {
        // push all datas here.
        System.exit(1);
    }
}