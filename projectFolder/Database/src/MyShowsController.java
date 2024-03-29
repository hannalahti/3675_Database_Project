import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MyShowsController {

    @FXML
    private HBox buttonBox;

    @FXML
    private Text formatText;

    @FXML
    private Text genreText;

    @FXML
    private Button likeButton;

    @FXML
    private Text likeText;

    @FXML
    private Tab likedMenu;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private Menu logoutLink;

    @FXML
    private ListView<String> mediaListView;

    @FXML
    private Menu myShowsLink;

    @FXML
    private Text ratingText;

    @FXML
    private Text runtimeText;

    @FXML
    private AnchorPane searchAnchorPage;

    @FXML
    private Button searchButton;

    @FXML
    private Menu searchLink;

    @FXML
    private TextField searchTextField;

    @FXML
    private Menu settingsLink;

    @FXML
    private MenuItem sortAlphabetical;

    @FXML
    private SplitMenuButton sortMenu;

    @FXML
    private MenuItem sortRatingHigh;

    @FXML
    private MenuItem sortRatingLow;

    @FXML
    private MenuItem sortRuntimeLongest;

    @FXML
    private MenuItem sortRuntimeShortest;

    @FXML
    private MenuItem sortYearNewest;

    @FXML
    private MenuItem sortYearOldest;

    @FXML
    private Text titleText;

    @FXML
    private Button watchedButton;

    @FXML
    private Tab watchedMenu;

    @FXML
    private Text yearText;

    @FXML
    private TabPane tabPane;

    Boolean isLiked;
    Boolean isWatched;
    String menu;
    String sort;
    String search;
    String selected;

    @FXML
    void likeButtonPressed(ActionEvent event) {
        setLikedButton(valueOf(!isLiked));
        //send to database (title, isLiked)
    }

    @FXML
    void logoutLinkPressed(ActionEvent event) {

    }

    @FXML
    void myShowsLinkPressed(ActionEvent event) {

    }

    @FXML
    void searchButtonPressed(ActionEvent event) {
        search = searchTextField.getText();
    }

    @FXML
    void searchLinkPressed(ActionEvent event) {
        switchWindow(event, "search.fxml");
    }

    @FXML
    void settingsLinkPressed(ActionEvent event) {

    }

    @FXML
    void watchedButtonPressed(ActionEvent event) {
        setWatchedButton(valueOf(!isWatched));
        //send to database (title, isWatched)
    }

    public void initialize(){

        setLikedList();
        menu="liked";
        sort="default";

        //listener for new selection on medialistview
        mediaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selected=newValue;
                setMediaView(selected);
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                mediaListView.setItems(null);
                if(newValue.getText().equals("Liked"))
                    setLikedList();
                else if (newValue.getText().equals("Watched"))
                    setWatchedList();
                else setRecommendedList();
                resetPage();
            }
        });

        //menu item listeners

        sortAlphabetical.setOnAction((e)-> {
            sortMedia("alphabetical");
            sortMenu.textProperty().set("Alphabetical");
        });
        sortRatingHigh.setOnAction((e)-> {
            sortMedia("ratingHigh");
            sortMenu.setText("Rating: high to low");
        });
        sortRatingLow.setOnAction((e)-> {
            sortMedia("ratingLow");
            sortMenu.setText("Rating: low to high");
        });
        sortYearNewest.setOnAction((e)-> {
            sortMedia("yearNewest");
            sortMenu.setText("Year: newest");
        });
        sortYearOldest.setOnAction((e)-> {
            sortMedia("yearOldest");
            sortMenu.setText("Year: oldest");
        });
        sortRuntimeLongest.setOnAction((e)-> {
            sortMedia("runtimeLongest");
            sortMenu.setText("Runtime: longest");
        });
        sortRuntimeShortest.setOnAction((e)-> {
            sortMedia("runtimeShortest");
            sortMenu.setText("Runtime: shortest");
        });
    }

    ArrayList<String> query(){
        //ObservableList<String> media = FXCollections.observableArrayList( someFunctionToRetrieveData( menu, search, sort ) )
        //  menu= liked/watched
        //  search= in search bar
        //  sort= alphabetical / ratingHigh / ratingLow / yearNewest / yearOldest / runtimeLongest / runtimeShortest

        return null;
    }

    void sortMedia(String s){
        sort = s;
        //ObservableList<String> media = FXCollections.observableArrayList( query() );
        //mediaListView.setItems(media);
    }

    void resetPage(){
        clearMediaView();
        sort="default";
        selected=null;
        search=null;
        sortMenu.setText("Sort");
    }

    void clearMediaView(){
        titleText.setText("");
        yearText.setText("");
        runtimeText.setText("");
        formatText.setText("");
        ratingText.setText("");
        genreText.setText("");
        buttonBox.setVisible(false);
    }

    void setRecommendedList(){
        //ObservableList<String> recommendedList = FXCollections.observableArrayList( someFunctionToGetRecommendedStringArrayList() )
        //mediaListView.setItems(recommendedList);
    }

    void setLikedList(){
        //ObservableList<String> likedList = FXCollections.observableArrayList( someFunctionToGetLikedStringArrayList() )

        //for testing ONLY
        ArrayList<String> media = new ArrayList<String>();
        media.add("test title");
        media.add("other title");
        ObservableList<String> likedList = FXCollections.observableArrayList(media);
        // ^

        mediaListView.setItems(likedList);
    }

    void setWatchedList(){
        //ObservableList<String> watchedList = FXCollections.observableArrayList( someFunctionToGetWatchedStringArrayList() )
        //mediaListView.setItems(watchedList);

        ArrayList<String> media = new ArrayList<String>();
        media.add("hello");
        ObservableList<String> watchedList = FXCollections.observableArrayList(media);
        mediaListView.setItems(watchedList);
    }

    /*
    setting properties of watched button
     */
    void setWatchedButton(String s){

        if(s.equals("true")) {
            isWatched=true;
            watchedButton.setStyle("-fx-background-color:   #7402c4; ");
            watchedButton.setText("Watched");
        }
        else{
            isWatched=false;
            watchedButton.setStyle("-fx-background-color:  #717171; ");
            watchedButton.setText("Watched?");
        }
    }

    /*
    Setting properties of liked (heart) button
     */
    void setLikedButton(String s){

        if(s.equals("true")) {
            isLiked=true;
            likeButton.setStyle("-fx-background-color:  #fc3434; ");
            likeText.setText("Liked");
        }
        else{
            isLiked=false;
            likeButton.setStyle("-fx-background-color:  #ffffff; ");
            likeText.setText("Like  ");
        }
    }

    /*
    Showing information on individual selected media
     */
    void setMediaView(String title){
        ArrayList<String> info = getMediaInfo(title);

        titleText.setText(info.get(0));
        yearText.setText(info.get(1));
        runtimeText.setText(info.get(2));
        formatText.setText(info.get(3));
        ratingText.setText(info.get(4));

        buttonBox.setVisible(true);
        setLikedButton(info.get(5));
        setWatchedButton(info.get(6));

        String genres = info.get(7);
        //concatenating all genres into one string
        for(int i=8; i<info.size(); i++)
            genres = genres.concat(", " + info.get(i));

        genreText.setText(genres);
    }

    /*
    Retrieving selected media's info from database
     */
    ArrayList<String> getMediaInfo(String title){
        //get media info from database by searching for specified title
        //ArrayList<String> info = someFunctionToGetResultsFromQuery(title);

        //for testing purposes ONLY:
        ArrayList<String> info = new ArrayList<String>();
        info.add("title name"); //title
        info.add("2024");       //year
        info.add("90");         //runtime
        info.add("movie");      //format
        info.add("9");          //rating
        info.add("true");          //isLiked by user (0 for not, 1 for is)
        info.add("false");          //isWatched by user (0 for not, 1 for is)
        //for genre, add as separate entries:
        info.add("mystery");
        info.add("fantasy");
        info.add("comedy");

        return info;
    }
    private Stage stage;
    private Parent root;
    public void switchWindow(ActionEvent event, String filename){
        try {
            stage = (Stage) searchButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource(filename));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
