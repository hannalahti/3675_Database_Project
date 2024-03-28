import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

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

        connectDB();
        launch(args);

    }


}