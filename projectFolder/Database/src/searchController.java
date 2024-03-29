/*
 * Author: Hanna Lahti
 *
 * Description:
 *
 *
 */

import java.util.ArrayList;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class searchController {

    @FXML
    private ProgressIndicator loadingIndicator;

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
    private ListView<String> mediaListView;

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
    private AnchorPane searchAnchorPage;

    @FXML
    private Button searchButton;

    @FXML
    private Menu searchLink;

    @FXML
    private Menu logoutLink;

    @FXML
    private Menu myShowsLink;

    @FXML
    private TextField searchTextField;

    @FXML
    private Menu settingsLink;

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
    DatabaseAccessor db;

    /*
     * applies all selected filters to search results
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

    /*
     * searches for results based on search bar input
     */
    @FXML
    void searchButtonPressed(ActionEvent event) throws InterruptedException {
        loadingIndicator.setVisible(!loadingIndicator.isVisible());

        String search = searchTextField.getText();

        //query
        //get an arraylist
        //showListView(arraylist);
    }

    /*
     * goes to search page
     */
    @FXML
    void searchLinkPressed(ActionEvent event) {

    }

    /*
     * goes to settings page
     */
    @FXML
    void settingsLinkPressed(ActionEvent event) {

    }

    @FXML
    void logoutLinkPressed(ActionEvent event) {
        switchWindow(event, "login.fxml");
    }

    @FXML
    void myShowsLinkPressed(ActionEvent event) {
        switchWindow(event, "myShows.fxml");
    }

    /*
     * executes when search.fxml is launched
     */
    public void initialize() {

        createGenreCheckList();
        createFormatCheckList();
        bindTextFieldWithSlider();
        bindAllCheckBoxes();

        mediaListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String>ov, String old_val, String new_val)->{
            showSelectedMedia(mediaListView.getSelectionModel().getSelectedItem());
        });

        //for sort splitmenu
        sortMenu.setOnAction(e -> {
            //set selection as label, and
            sortMedia("default");
        });

    }

    /*
     * sorts the media results based on selected method
     */
    void sortMedia(String method) {

    }

    /*
     * displays info on selected media
     */
    void showSelectedMedia(String selectedMedia) {

    }

    /*
     * creates query based on filter values
     */
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
     * accesses database and returns query result
     */
    ArrayList<String> query(String q) {

        //...
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
