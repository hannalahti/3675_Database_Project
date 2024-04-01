

import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    static String DATABASE_NAME = "DatabaseProject";
    public static void connectDB() {
        String uri = "mongodb+srv://root:root@database.5voxcth.mongodb.net/?retryWrites=true&w=majority&appName=Database\n";
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

            if(database==null)
                System.out.println("No database found");
            else
                System.out.println("Success!");

            MongoIterable<String> list = database.listCollectionNames();
            for (String name : list) {
                System.out.println(name);
            }
        }
    }


    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Media");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*
        //connectDB();
        DatabaseAccessor db = new DatabaseAccessor();

        //System.out.printf("Said's UserID: %d %n", db.registerUser("said", "123"));
        //System.out.printf("Hanna's UserID: %d %n", db.registerUser("hanna", "123"));
        //System.out.printf("Lois' UserID: %d %n", db.registerUser("lois", "123"));
        System.out.printf("Search Media Example %n");
        ArrayList<String> c = db.findMedia("c");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }
        db.findUser("said", "123");

        System.out.printf("%n%nSearch Watched Media Example %n");
        boolean reults = db.insertWatchedMedia("YouTubers React to Don't Hug Me I'm Scared");
        reults = db.insertWatchedMedia("Welcome Obama");
        reults = db.insertWatchedMedia("Barbie: A Touch of Magic");
        reults = db.insertWatchedMedia("Back to the Future: Back in Time Video Slots");
        reults = db.insertWatchedMedia("The Switchman's Tower");
        db.deleteWatchedMedia("The Switchman's Tower");
        for(String i : db.findWatchedMedia()) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Liked Media Example %n");
        reults = db.deleteLikedMedia("The Merchant of Venice");
        reults = db.deleteLikedMedia("A Rich Revenge");
        reults = db.deleteLikedMedia("Across the Continent");
        reults = db.insertLikedMedia("The Merchant of Venice");
        reults = db.insertLikedMedia("A Rich Revenge");
        reults = db.insertLikedMedia("Across the Continent");
        reults = db.insertLikedMedia("An Extraordinary Cab Accident");
        reults = db.insertLikedMedia("The Little Teacher");
        reults = db.deleteLikedMedia("The Little Teacher");
        for(String i : db.findLikedMedia()) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Liked Genres Example %n");
        reults = db.insertLikedGenre("short");
        reults = db.insertLikedGenre("documentary");
        reults = db.deleteLikedGenre("documentary");
        for(String i : db.findLikedGenre()) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Media Sorted Example %n");
        c = db.findMediaSorted("c", "alpha");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Watched Sorted Example %n");
        c = db.findWatchedMediaSorted("c", "alpha");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Liked Sorted Example %n");
        c = db.findLikedMediaSorted("", "alpha");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nDetails Search Example %n");
        c = db.findDetails("Welcome Obama");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nParams Search Example %n");
        /*
        c = db.findMediaParamSorted("",
        1900,
        2020,
        0,
        45,
        0,
        10,
         new String[]{"movie"},
         new String[]{"Drama"},
        "alpha");
        for(String i : c) {
            System.out.printf("%s %n", i);
        }
*/
        DatabaseAccessor.getDb();
        //DatabaseAccessor.getDb().generateRecommendedMedia();
        launch(args);

    }


}
