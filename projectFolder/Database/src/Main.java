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

        Parent root = FXMLLoader.load(getClass().getResource("myShows.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Media");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

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
        db.findUser("said", "1234");

        System.out.printf("%n%nSearch Watched Media Example %n");
        boolean reults = db.insertWatchedMedia("Black Sheep");
        reults = db.insertWatchedMedia("The Switchman's Tower");
        db.deleteWatchedMedia("The Switchman's Tower");
        for(String i : db.findWatchedMedia()) {
            System.out.printf("%s %n", i);
        }

        System.out.printf("%n%nSearch Liked Media Example %n");
        reults = db.insertLikedMedia("The Merchant of Venice");
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

        launch(args);

    }


}