
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.Block;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Projections;

import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseAccessor {


    long user_id = -1;
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



    public ArrayList<String> findMedia(String media_name) {
        MongoCollection<Document> collection = database.getCollection(MEDIA_COLLECTION);
        Bson mediaFinder = Filters.regex("movie_title", media_name);
        Bson onlyNames = Projections.fields(
                Projections.include("movie_title"),
                Projections.excludeId());
        ArrayList<String> docList = new ArrayList<String>();
        collection.find(mediaFinder).projection(onlyNames).limit(20)
                .forEach((Block<? super Document>) doc -> docList.add(doc.toString()));

        for(int i = 0; i < docList.size(); i++) {
            docList.set(i, docList.get(i).replaceAll("Document\\{\\{movie_title=",""));
            docList.set(i, docList.get(i).replaceAll("}}","") );
        }

        return docList;
    }

    public long findUser(String username, String password) {

        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);
        if(collection == null) {
            return -1;
        }
        Bson filter = Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", password)
        );

        Document doc = collection.find(filter).first();
        if (doc != null) {
            Long i = doc.getLong("user_id");
            user_id = i;
            return i;
        }
        else {
            System.out.println("No matching documents found.");
            return -1;
        }
    }

    public long registerUser(String username, String password) {
        if(findUser(username, password) != -1) {
            return findUser(username, password);
        }

        MongoCollection<Document> collection = database.getCollection(USERS_COLLECTION);
        long gen_user_id = 0;
        try {
            gen_user_id = collection.countDocuments() + 1;
        }
        catch(Exception e) {
            System.out.printf("could not count %n");
            System.out.printf("%s", e);
        }

        System.out.printf("user id gen : %d %n", gen_user_id);
        Document doc1 = new Document
                ("username", username)
                .append("password", password)
                .append("user_id", gen_user_id);
        try {
            collection.insertOne(doc1);
        }
        catch(Exception e) {
            System.out.printf("attempted to insert and failed %n");
            return -1;
        }

        user_id = findUser(username, password);
        if(user_id >= 0) {
            return user_id;
        }
        else {
            System.out.printf("failed to insert %n");
            return -1;
        }
    }
    public DatabaseAccessor() {

        try  {
            mongoClient = MongoClients.create(URI);
            database = mongoClient.getDatabase(DATABASE_NAME);
        }
        catch(Exception e) {
            System.out.println("Could not connect to the database");
        }

        if(database==null) {
            System.out.println("No database found");
            return;
        }
        else
            System.out.println("Success!");
        MongoIterable<String> list = database.listCollectionNames();
        for (String name : list) {
            System.out.println(name);
        }

    }
}
