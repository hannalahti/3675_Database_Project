import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GenreController extends Controller{

    @FXML
    private Button likeButtonAction;

    @FXML
    private Button likeButtonAdult;

    @FXML
    private Button likeButtonAdventure;

    @FXML
    private Button likeButtonAnimation;

    @FXML
    private Button likeButtonBiography;

    @FXML
    private Button likeButtonComedy;

    @FXML
    private Button likeButtonCrime;

    @FXML
    private Button likeButtonDocumentary;

    @FXML
    private Button likeButtonDrama;

    @FXML
    private Button likeButtonFamily;

    @FXML
    private Button likeButtonFantasy;

    @FXML
    private Button likeButtonFilmNoir;

    @FXML
    private Button likeButtonGameShow;

    @FXML
    private Button likeButtonHistory;

    @FXML
    private Button likeButtonHorror;

    @FXML
    private Button likeButtonMusic;

    @FXML
    private Button likeButtonMusical;

    @FXML
    private Button likeButtonMystery;

    @FXML
    private Button likeButtonNews;

    @FXML
    private Button likeButtonRealityTV;

    @FXML
    private Button likeButtonRomance;

    @FXML
    private Button likeButtonSciFi;

    @FXML
    private Button likeButtonShort;

    @FXML
    private Button likeButtonSport;

    @FXML
    private Button likeButtonTalkShow;

    @FXML
    private Button likeButtonThriller;

    @FXML
    private Button likeButtonWar;

    @FXML
    private Button likeButtonWestern;

    @FXML
    private Text likeText;

    @FXML
    private Text likeText1;

    @FXML
    private Text likeText10;

    @FXML
    private Text likeText11;

    @FXML
    private Text likeText110;

    @FXML
    private Text likeText12;

    @FXML
    private Text likeText13;

    @FXML
    private Text likeText14;

    @FXML
    private Text likeText15;

    @FXML
    private Text likeText16;

    @FXML
    private Text likeText17;

    @FXML
    private Text likeText18;

    @FXML
    private Text likeText19;

    @FXML
    private Text likeText2;

    @FXML
    private Text likeText20;

    @FXML
    private Text likeText21;

    @FXML
    private Text likeText22;

    @FXML
    private Text likeText23;

    @FXML
    private Text likeText24;

    @FXML
    private Text likeText25;

    @FXML
    private Text likeText26;

    @FXML
    private Text likeText27;

    @FXML
    private Text likeText3;

    @FXML
    private Text likeText4;

    @FXML
    private Text likeText5;

    @FXML
    private Text likeText6;

    @FXML
    private Text likeText7;

    @FXML
    private Text likeText8;

    @FXML
    private Text likeText9;

    @FXML
    private Button logoutButton;

    @FXML
    private Button myShowsButton;

    @FXML
    private AnchorPane searchAnchorPage;

    ArrayList<String> genres;
    ArrayList<Button> genreButtons;
    ArrayList<String> likedGenres;

    @FXML
    void likeButtonPressedAction(ActionEvent event) {
        handleLikeButtonAction("Action", likeButtonAction);
    }

    @FXML
    void likeButtonPressedAdult(ActionEvent event) {
        handleLikeButtonAction("Adult", likeButtonAdult);
    }

    @FXML
    void likeButtonPressedAdventure(ActionEvent event) {
        handleLikeButtonAction("Adventure", likeButtonAdventure);
    }

    @FXML
    void likeButtonPressedAnimation(ActionEvent event) {
        handleLikeButtonAction("Animation", likeButtonAnimation);
    }

    @FXML
    void likeButtonPressedBiography(ActionEvent event) {
        handleLikeButtonAction("Biography", likeButtonBiography);
    }

    @FXML
    void likeButtonPressedComedy(ActionEvent event) {
        handleLikeButtonAction("Comedy", likeButtonComedy);
    }

    @FXML
    void likeButtonPressedCrime(ActionEvent event) {
        handleLikeButtonAction("Crime", likeButtonCrime);
    }

    @FXML
    void likeButtonPressedDocumentary(ActionEvent event) {
        handleLikeButtonAction("Documentary", likeButtonDocumentary);
    }

    @FXML
    void likeButtonPressedDrama(ActionEvent event) {
        handleLikeButtonAction("Drama", likeButtonDrama);
    }

    @FXML
    void likeButtonPressedFamily(ActionEvent event) {
        handleLikeButtonAction("Family", likeButtonFamily);
    }

    @FXML
    void likeButtonPressedFantasy(ActionEvent event) {
        handleLikeButtonAction("Fantasy", likeButtonFantasy);
    }

    @FXML
    void likeButtonPressedFilmNoir(ActionEvent event) {
        handleLikeButtonAction("Film-Noir", likeButtonFilmNoir);
    }

    @FXML
    void likeButtonPressedGameShow(ActionEvent event) {
        handleLikeButtonAction("Game-Show", likeButtonGameShow);
    }

    @FXML
    void likeButtonPressedHistory(ActionEvent event) {
        handleLikeButtonAction("History", likeButtonHistory);
    }

    @FXML
    void likeButtonPressedHorror(ActionEvent event) {
        handleLikeButtonAction("Horror", likeButtonHorror);
    }

    @FXML
    void likeButtonPressedMusic(ActionEvent event) {
        handleLikeButtonAction("Music", likeButtonMusic);
    }

    @FXML
    void likeButtonPressedMusical(ActionEvent event) {
        handleLikeButtonAction("Musical", likeButtonMusical);
    }

    @FXML
    void likeButtonPressedMystery(ActionEvent event) {
        handleLikeButtonAction("Mystery", likeButtonMystery);
    }

    @FXML
    void likeButtonPressedNews(ActionEvent event) {
        handleLikeButtonAction("News", likeButtonNews);
    }

    @FXML
    void likeButtonPressedRealityTV(ActionEvent event) {
        handleLikeButtonAction("Reality-TV", likeButtonRealityTV);
    }

    @FXML
    void likeButtonPressedRomance(ActionEvent event) {
        handleLikeButtonAction("Romance", likeButtonRomance);
    }

    @FXML
    void likeButtonPressedSciFi(ActionEvent event) {
        handleLikeButtonAction("Sci-Fi", likeButtonSciFi);
    }

    @FXML
    void likeButtonPressedShort(ActionEvent event) {
        handleLikeButtonAction("Short", likeButtonShort);
    }

    @FXML
    void likeButtonPressedSport(ActionEvent event) {
        handleLikeButtonAction("Sport", likeButtonSport);
    }

    @FXML
    void likeButtonPressedTalkShow(ActionEvent event) {
        handleLikeButtonAction("Talk-Show", likeButtonTalkShow);
    }

    @FXML
    void likeButtonPressedThriller(ActionEvent event) {
        handleLikeButtonAction("Thriller", likeButtonThriller);
    }

    @FXML
    void likeButtonPressedWar(ActionEvent event) {
        handleLikeButtonAction("War", likeButtonWar);
    }

    @FXML
    void likeButtonPressedWestern(ActionEvent event) {
        handleLikeButtonAction("Western", likeButtonWestern);
    }

    private void handleLikeButtonAction(String genre, Button button) {
        if (findIndex(genre, likedGenres) != -1) {
            DatabaseAccessor.db.deleteLikedMedia(genre);
            setHeartButton(button, false);
            likedGenres.remove(genre);
        } else {
            DatabaseAccessor.db.insertLikedGenre(genre);
            setHeartButton(button, true);
            likedGenres.add(genre);
        }
    }

    @FXML
    void logoutButtonPressed(ActionEvent event) {
        super.switchWindow(event, "login.fxml", logoutButton);
    }

    @FXML
    void myShowsButtonPressed(ActionEvent event) {
        super.switchWindow(event, "myShows.fxml", logoutButton);
    }

    public void initialize(){

        genreButtons = new ArrayList<Button>();
        genres = new ArrayList<String>();
        createButtonList();
        createGenreList();
        setAllButtons();
    }

    void setHeartButton(Button b, Boolean boo){
        if(boo==true) {
            b.setStyle("-fx-background-color:  #fc3434; ");
        }
        else{
            b.setStyle("-fx-background-color:  #ffffff; ");
        }
    }

    void setAllButtons(){
        likedGenres = DatabaseAccessor.db.findLikedGenre();

        for(int i=0; i< likedGenres.size(); i++){
            int index = findIndex(likedGenres.get(i),genres);
            if (index!=-1) {
                setHeartButton(genreButtons.get(index), true);
                break;
            }

        }
    }

    int findIndex(String s, ArrayList<String> a){
        for(int j=0;j<a.size();j++) {
            if (s.equals(a.get(j))) {
                return j;
            }
        }
        return -1;
    }

    void createGenreList(){
        genres.add("Action");
        genres.add("Adult");
        genres.add("Adventure");
        genres.add("Animation");
        genres.add("Biography");
        genres.add("Comedy");
        genres.add("Crime");
        genres.add("Documentary");
        genres.add("Drama");
        genres.add("Family");
        genres.add("Fantasy");
        genres.add("Film-Noir");
        genres.add("Game-Show");
        genres.add("History");
        genres.add("Horror");
        genres.add("Music");
        genres.add("Musical");
        genres.add("Mystery");
        genres.add("News");
        genres.add("Reality-TV");
        genres.add("Romance");
        genres.add("Sci-Fi");
        genres.add("Short");
        genres.add("Sport");
        genres.add("Talk-Show");
        genres.add("Thriller");
        genres.add("War");
        genres.add("Western");
    }

    void createButtonList(){
        genreButtons.add(likeButtonAction);
        genreButtons.add(likeButtonAdult);
        genreButtons.add(likeButtonAdventure);
        genreButtons.add(likeButtonAnimation);
        genreButtons.add(likeButtonBiography);
        genreButtons.add(likeButtonComedy);
        genreButtons.add(likeButtonCrime);
        genreButtons.add(likeButtonDocumentary);
        genreButtons.add(likeButtonDrama);
        genreButtons.add(likeButtonFamily);
        genreButtons.add(likeButtonFantasy);
        genreButtons.add(likeButtonFilmNoir);
        genreButtons.add(likeButtonGameShow);
        genreButtons.add(likeButtonHistory);
        genreButtons.add(likeButtonHorror);
        genreButtons.add(likeButtonMusic);
        genreButtons.add(likeButtonMusical);
        genreButtons.add(likeButtonMystery);
        genreButtons.add(likeButtonNews);
        genreButtons.add(likeButtonRealityTV);
        genreButtons.add(likeButtonRomance);
        genreButtons.add(likeButtonSciFi);
        genreButtons.add(likeButtonShort);
        genreButtons.add(likeButtonSport);
        genreButtons.add(likeButtonTalkShow);
        genreButtons.add(likeButtonThriller);
        genreButtons.add(likeButtonWar);
        genreButtons.add(likeButtonWestern);
    }

}
