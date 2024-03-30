/*
 * Author: Hanna Lahti
 */

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MyShowsController extends Controller{

    @FXML
    private CheckBox actionGenreCheckBox;

    @FXML
    private CheckBox adultGenreCheckBox;

    @FXML
    private CheckBox adventureGenreCheckBox;

    @FXML
    private CheckBox animationGenreCheckBox;

    @FXML
    private CheckBox anyGenreCheckBox;

    @FXML
    private Button applyButton;

    @FXML
    private CheckBox biographyGenreCheckBox;

    @FXML
    private CheckBox comedyGenreCheckBox;

    @FXML
    private CheckBox crimeGenreCheckBox;

    @FXML
    private CheckBox documentaryGenreCheckBox;

    @FXML
    private CheckBox dramaGenreCheckBox;

    @FXML
    private CheckBox familyGenreCheckBox;

    @FXML
    private CheckBox fantasyGenreCheckBox;

    @FXML
    private CheckBox filmnoirGenreCheckBox;

    @FXML
    private CheckBox gameshowGenreCheckBox;

    @FXML
    private CheckBox historyGenreCheckBox;

    @FXML
    private CheckBox horrorGenreCheckBox;


    @FXML
    private CheckBox musicGenreCheckBox;

    @FXML
    private CheckBox musicalGenreCheckBox;

    @FXML
    private CheckBox mysteryGenreCheckBox;

    @FXML
    private CheckBox newsGenreCheckBox;

    @FXML
    private Slider ratingFromSlider;

    @FXML
    private TextField ratingFromTextField;

    @FXML
    private Slider ratingToSlider;

    @FXML
    private TextField ratingToTextField;

    @FXML
    private VBox ratingVbox;

    @FXML
    private CheckBox realitytvGenreCheckBox;

    @FXML
    private CheckBox romanceGenreCheckBox;

    @FXML
    private Slider runtimeFromSlider;

    @FXML
    private TextField runtimeFromTextField;

    @FXML
    private Slider runtimeToSlider;

    @FXML
    private TextField runtimeToTextField;

    @FXML
    private CheckBox scifiGenreCheckBox;


    @FXML
    private CheckBox shortGenreCheckBox;

    @FXML
    private MenuItem sortAlphabetical;

    @FXML
    private MenuItem sortRatingHigh;

    @FXML
    private MenuItem sortRatingLow;

    @FXML
    private SplitMenuButton sortMenu;

    @FXML
    private CheckBox sportGenreCheckBox;

    @FXML
    private CheckBox talkshowGenreCheckBox;

    @FXML
    private CheckBox thrillerGenreCheckBox;

    @FXML
    private CheckBox warGenreCheckBox;

    @FXML
    private CheckBox westernGenreCheckBox;

    @FXML
    private Slider yearFromSlider;

    @FXML
    private TextField yearFromTextField;

    @FXML
    private Slider yearToSlider;

    @FXML
    private TextField yearToTextField;

    @FXML
    private CheckBox anyFormatCheckBox;

    @FXML
    private CheckBox movieFormatCheckBox;

    @FXML
    private CheckBox shortFormatCheckBox;

    @FXML
    private CheckBox tvEpisodeFormatCheckBox;

    @FXML
    private CheckBox tvMiniSeriesFormatCheckBox;

    @FXML
    private CheckBox tvMovieFormatCheckBox;

    @FXML
    private CheckBox tvPilotFormatCheckBox;

    @FXML
    private CheckBox tvSeriesFormatCheckBox;

    @FXML
    private CheckBox tvShortFormatCheckBox;

    @FXML
    private CheckBox tvSpecialFormatCheckBox;

    @FXML
    private CheckBox videoFormatCheckBox;

    @FXML
    private CheckBox videoGameFormatCheckBox;

    ArrayList<CheckBox> genreCheckBoxes;
    ArrayList<CheckBox> formatCheckBoxes;
    ObservableList<String> Media;
    String sortBy;



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
    private Button logoutButton;

    @FXML
    private ListView<String> mediaListView;

    @FXML
    private Text ratingText;

    @FXML
    private Text runtimeText;

    @FXML
    private AnchorPane searchAnchorPage;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button settingsButton;

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

    @FXML
    private ScrollPane scrollPane;

    Boolean isLiked;
    Boolean isWatched;
    String menu;
    String sort;
    String search;
    String selected;

    /*
    applying selected filters
     */
    @FXML
    void applyButtonPressed(ActionEvent event) {

        String search = searchTextField.getText();

        ArrayList<String> selectedGenres = getSelectedCheckBoxes(genreCheckBoxes);

        ArrayList<String> selectedFormats = getSelectedCheckBoxes(formatCheckBoxes);

        int yearFrom = (int) yearFromSlider.getValue();
        int yearTo = (int) yearToSlider.getValue();

        int runtimeFrom = (int) runtimeFromSlider.getValue();
        int runtimeTo = (int) runtimeToSlider.getValue();

        int ratingFrom = (int) ratingFromSlider.getValue();
        int ratingTo = (int) ratingToSlider.getValue();

        showListView(filteredQuery(search, selectedGenres, selectedFormats, yearFrom, yearTo,
                runtimeFrom, runtimeTo, ratingFrom, ratingTo));
    }

    @FXML
    void likeButtonPressed(ActionEvent event) {
        setLikedButton(valueOf(!isLiked));
        if(isLiked)
            DatabaseAccessor.db.insertLikedMedia(selected);
        else DatabaseAccessor.db.deleteLikedMedia(selected);
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) {
        super.switchWindow(event, "login.fxml", logoutButton);
    }

    @FXML
    void searchButtonPressed(ActionEvent event) {
        loadingIndicator.setVisible(true);
        search = searchTextField.getText();
        ObservableList<String> media = FXCollections.observableArrayList(DatabaseAccessor.db.findMedia(search));
        mediaListView.setItems(media);
        loadingIndicator.setVisible(false);
    }
    @FXML
    void settingsButtonPressed(ActionEvent event) {
        super.switchWindow(event, "genres.fxml", settingsButton);
    }

    @FXML
    void watchedButtonPressed(ActionEvent event) {
        setWatchedButton(valueOf(!isWatched));
        if(isWatched)
            DatabaseAccessor.db.insertWatchedMedia(selected);
        else DatabaseAccessor.db.deleteWatchedMedia(selected);
    }

    public void initialize(){
        clearMediaView();
        createGenreCheckList();
        createFormatCheckList();
        bindTextFieldWithSlider();
        bindAllCheckBoxes();
        setSearchPage();

        menu="search";
        sort="default";

        //listener for new selection on medialistview
        mediaListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selected=newValue;
                System.out.println(selected);
                setMediaView(selected);
            }
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                sort="default";
                mediaListView.setItems(null);
                scrollPane.setDisable(true);
                if(newValue.getText().equals("Liked"))
                    setLikedList();
                else if (newValue.getText().equals("Watched"))
                    setWatchedList();
                else if (newValue.getText().equals("Search"))
                    setSearchPage();
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

    void setSearchPage(){
        menu="search";
        scrollPane.setDisable(false);
        searchTextField.setText(null);
        searchButton.setVisible(true);
        searchTextField.setVisible(true);
    }

    ArrayList<String> query(){
        //ObservableList<String> media = FXCollections.observableArrayList( someFunctionToRetrieveData( menu, search, sort ) )
        //  menu= liked/watched
        //  search= in search bar
        //  sort= alphabetical / ratingHigh / ratingLow / yearNewest / yearOldest / runtimeLongest / runtimeShortest

        return null;
    }

    ArrayList<String> filteredQuery(String search, ArrayList<String> selectedGenres, ArrayList<String> selectedFormats, int yearFrom,
                                    int yearTo, int runtimeFrom, int runtimeTo, int ratingFrom, int ratingTo) {

        /*
         * Movie title like "%search%", AND
         * any with "selected genres" (make sure to only get unique), AND
         * any with "selected formats", AND
         * "yearFrom" <= year <= "yearTo", AND
         * "runtimeFrom" <= runtime <= "runtimeTo", AND
         * "ratingFrom" <= rating <= "ratingTo"
         */

        //query();

        //return results in arraylist<string> form
        return null;
    }

    /*
     * displays list in mediaListView
     */
    void showListView(ArrayList<String> list) {
        if(list==null)
            return;

        Media = FXCollections.observableArrayList(list);
        mediaListView.setItems(Media);
    }

    void sortMedia(String s){
        sort = s;
        ObservableList<String> media;
        System.out.println(menu+sort);
        if(menu.equals("search"))
            media = FXCollections.observableArrayList( DatabaseAccessor.db.findMediaSorted(search, s) );
        else if(menu.equals("liked"))
            media = FXCollections.observableArrayList( DatabaseAccessor.db.findLikedMediaSorted(search, s) );
        else media = FXCollections.observableArrayList( DatabaseAccessor.db.findWatchedMediaSorted(search, s) );

        mediaListView.setItems(media);
    }

    void resetPage(){
        clearMediaView();
        sort="default";
        selected=null;
        search=null;
        sortMenu.setText("Sort");
    }

    void clearMediaView(){
        selected= null;
        titleText.setText("");
        yearText.setText("");
        runtimeText.setText("");
        formatText.setText("");
        ratingText.setText("");
        genreText.setText("");
        buttonBox.setVisible(false);
    }

    void setRecommendedList(){
        menu="recommended";
        sort="default";
        //ObservableList<String> recommendedList = FXCollections.observableArrayList( someFunctionToGetRecommendedStringArrayList() )
        //mediaListView.setItems(recommendedList);
    }

    void setLikedList(){
        menu="liked";
        sort="default";
        searchButton.setVisible(false);
        searchTextField.setVisible(false);
        ObservableList<String> likedList = FXCollections.observableArrayList(DatabaseAccessor.db.findLikedMedia());
        mediaListView.setItems(likedList);
    }

    void setWatchedList(){
        menu="watched";
        sort="default";
        ObservableList<String> watchedList = FXCollections.observableArrayList(DatabaseAccessor.db.findWatchedMedia());
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
        ArrayList<String> info = DatabaseAccessor.db.findDetails(title);

        titleText.setText(info.get(0));
        yearText.setText(info.get(6));
        runtimeText.setText(info.get(1));
        formatText.setText(info.get(2));
        ratingText.setText(info.get(5));

        buttonBox.setVisible(true);
        setLikedButton(info.get(4));
        setWatchedButton(info.get(3));

        String genres = info.get(7);
        //concatenating all genres into one string
        for(int i=8; i<info.size(); i++)
            genres = genres.concat(", " + info.get(i));

        genreText.setText(genres);
    }

    /*
     * finds which check boxes are selected from checkbox list and returns names in string form
     */
    ArrayList<String> getSelectedCheckBoxes(ArrayList<CheckBox> x){

        ArrayList<String> selected = new ArrayList<String>();

        for(int i = 0; i < x.size(); i++) {
            if(x.get(i).isSelected())
                selected.add(x.get(i).getText());
        }
        return selected;
    }

    void bindCheckBox(CheckBox a, CheckBox b) {
        a.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                b.setSelected(false);
            }
        });

        b.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            if (isNowSelected) {
                a.setSelected(false);
            }
        });
    }

    void bindAllCheckBoxes() {

        for(int i=0; i<genreCheckBoxes.size(); i++)
            bindCheckBox(anyGenreCheckBox, genreCheckBoxes.get(i));

        for(int i=0; i<formatCheckBoxes.size(); i++)
            bindCheckBox(anyFormatCheckBox, formatCheckBoxes.get(i));
    }

    /*
     * Slider and corresponding text field will be binded to reflect the same numerical values
     */
    void bindTextFieldWithSlider() {
        Bindings.bindBidirectional(yearFromTextField.textProperty(), yearFromSlider.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(yearToTextField.textProperty(), yearToSlider.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(runtimeFromTextField.textProperty(), runtimeFromSlider.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(runtimeToTextField.textProperty(), runtimeToSlider.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(ratingFromTextField.textProperty(), ratingFromSlider.valueProperty(), new NumberStringConverter());
        Bindings.bindBidirectional(ratingToTextField.textProperty(), ratingToSlider.valueProperty(), new NumberStringConverter());
    }

    /*
     * adds all format check boxes to an arraylist for easy searching in the future
     */
    void createFormatCheckList() {

        formatCheckBoxes = new ArrayList<CheckBox>();

        formatCheckBoxes.add(movieFormatCheckBox);
        formatCheckBoxes.add(shortFormatCheckBox);
        formatCheckBoxes.add(tvEpisodeFormatCheckBox);
        formatCheckBoxes.add(tvMiniSeriesFormatCheckBox);
        formatCheckBoxes.add(tvMovieFormatCheckBox);
        formatCheckBoxes.add(tvPilotFormatCheckBox);
        formatCheckBoxes.add(tvSeriesFormatCheckBox);
        formatCheckBoxes.add(tvShortFormatCheckBox);
        formatCheckBoxes.add(tvSpecialFormatCheckBox);
        formatCheckBoxes.add(videoFormatCheckBox);
        formatCheckBoxes.add(videoGameFormatCheckBox);
    }

    /*
     * adds all genre check boxes to an arraylist for easy searching in the future
     */
    void createGenreCheckList() {

        genreCheckBoxes = new ArrayList<CheckBox>();

        genreCheckBoxes.add(actionGenreCheckBox);
        genreCheckBoxes.add(adultGenreCheckBox);
        genreCheckBoxes.add(adventureGenreCheckBox);
        genreCheckBoxes.add(animationGenreCheckBox);
        genreCheckBoxes.add(anyGenreCheckBox);
        genreCheckBoxes.add(biographyGenreCheckBox);
        genreCheckBoxes.add(comedyGenreCheckBox);
        genreCheckBoxes.add(crimeGenreCheckBox);
        genreCheckBoxes.add(documentaryGenreCheckBox);
        genreCheckBoxes.add(dramaGenreCheckBox);
        genreCheckBoxes.add(familyGenreCheckBox);
        genreCheckBoxes.add(fantasyGenreCheckBox);
        genreCheckBoxes.add(filmnoirGenreCheckBox);
        genreCheckBoxes.add(gameshowGenreCheckBox);
        genreCheckBoxes.add(historyGenreCheckBox);
        genreCheckBoxes.add(musicGenreCheckBox);
        genreCheckBoxes.add(musicalGenreCheckBox);
        genreCheckBoxes.add(mysteryGenreCheckBox);
        genreCheckBoxes.add(newsGenreCheckBox);
        genreCheckBoxes.add(realitytvGenreCheckBox);
        genreCheckBoxes.add(romanceGenreCheckBox);
        genreCheckBoxes.add(scifiGenreCheckBox);
        genreCheckBoxes.add(shortGenreCheckBox);
        genreCheckBoxes.add(sportGenreCheckBox);
        genreCheckBoxes.add(talkshowGenreCheckBox);
        genreCheckBoxes.add(thrillerGenreCheckBox);
        genreCheckBoxes.add(warGenreCheckBox);
        genreCheckBoxes.add(westernGenreCheckBox);
        genreCheckBoxes.add(horrorGenreCheckBox);
    }
}


