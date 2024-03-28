
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DatabaseAccessor {


    private MongoClient mongoClient;
    private static MongoDatabase database;
    private final String DATABASE_NAME = "DatabaseProject";
    private final String URI = "mongodb+srv://root:root@database.5voxcth.mongodb.net/?retryWrites=true&w=majority&appName=Database\n";
    private static final String USERS_COLLECTION = "User";
    private final String MEDIA_COLLECTION = "Media";
    private final String PRODUCTION_COLLECTION = "Production_Details";
    private final String GENRE_COLLECTION = "Genre";

    private static final String ENJOYS_GENRE_COLLECTION = "Enjoys";
    private final String BELONGS = "Belongs";

    public static int findUser(String username, String password) {
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);
        Bson filter = Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", password)
        );
        Document doc = collection.find(filter).first();
        if (doc != null) {
            Integer i = doc.getInteger("user_id");
            return i;
        }
        else {
            System.out.println("No matching documents found.");
            return -1;
        }
    }

    public static int registerUser(String username, String password) {
        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);

        Random rand = new Random();
        int upperbound = Integer.MAX_VALUE;
        // >2 billion, if it doesn't insert then either astronomical odds or DB is full
        int int_random = rand.nextInt(upperbound);

        Document doc1 = new Document
                ("username", username)
                .append("password", password)
                .append("user_id", int_random);
        collection.insertOne(doc1);
        int user_id = findUser(username, password);
        if(user_id >= 0) {
            return user_id;
        }
        else {
            System.out.printf("failed to insert %n");
            return -1;
        }
    }

    public static String[] findEnjoyedGenres(int user_id) {
        MongoCollection<Document> collection = database.getCollection(ENJOYS_GENRE_COLLECTION);
        Bson filter = Filters.eq("user_id", user_id);
        Bson projection = Projections.fields(Projections.include("genre_name"));

        ArrayList<String> docList = new ArrayList<String>();
        collection.find(filter)
                .projection(projection)
                .forEach((Block<? super Document>) doc -> docList.add(doc.toString()));

        return (String[]) docList.toArray();
    }
    public static boolean enjoysGenre(int user_id, String genreName) {
        MongoCollection<Document> collection = database.getCollection(ENJOYS_GENRE_COLLECTION);
        Document doc = new Document("user_id", user_id).append("genre_name", genreName);
        collection.insertOne(doc);

        if(Arrays.asList( findEnjoyedGenres(user_id) ).contains(genreName) == true) {
            return true;
        }
        else {
            System.out.println("could not insert new enjoyed genre");
            return false;
        }
    }

    public static String[] findWatchedMedia(int user_id) {
        String[] s = {};
        return s;
    }

    /*
        public Document[] recommendationGenerator(int user_id) {
            // if we have an index for media newer than 2005, search that
            String[] genres = findEnjoyedGenres(user_id);
            String[] watched = findWatchedMedia(user_id);

            MongoCollection<Document> collection = database.getCollection(BELONGS);

        }
    */
    public DatabaseAccessor() {
        try (MongoClient mongoClient = MongoClients.create(URI)) {
            database = mongoClient.getDatabase(DATABASE_NAME);
        }
        catch(Exception e) {
            System.out.printf("Could not connect to database %n");
        }
    }
}
