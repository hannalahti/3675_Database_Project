


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.mql.MqlValues.current;

import com.mongodb.Block;
import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.mql.MqlDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Projections;

import java.util.*;

public class DatabaseAccessor {

    static DatabaseAccessor db;

    long user_id;
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
    private final String WATCHED_COLLECTION = "Watched";
    private final String LIKES_COLLECTION = "Likes";

    public static DatabaseAccessor getDb() {
        if(db==null)
            db=new DatabaseAccessor();
        return db;
    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();
        for (T element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return newList;
    }


    public boolean insertLikedGenre(String genreName) {
        if(findLikedGenre().contains(genreName)) {
            return true;
        };
        MongoCollection<Document> collection = database.getCollection(ENJOYS_GENRE_COLLECTION);
        Document doc1 = new Document("user_id", user_id).append("genre_name", genreName);
        try {
            collection.insertOne(doc1);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    public boolean deleteLikedGenre(String genreName) {
        if(findLikedGenre().contains(genreName)) {
            MongoCollection<Document> collection = database.getCollection(ENJOYS_GENRE_COLLECTION);
            Bson onlyForThisUser = Filters.eq("user_id", user_id);
            Bson onlyThisMedia = Filters.eq("genre_name", genreName);
            Bson removeQuery = Filters.and(onlyForThisUser, onlyThisMedia);
            try {
                collection.deleteOne(removeQuery);
                return true;
            }
            catch(Exception e) {
                return false;
            }
        }
        else {
            return true;
        }

    }
    public ArrayList<String> findLikedGenre() {

        MongoCollection<Document> collection = database.getCollection(ENJOYS_GENRE_COLLECTION);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson onlyNames = Projections.fields(
                Projections.include("genre_name"),
                Projections.excludeId());
        ArrayList<String> docList = new ArrayList<String>();
        collection.find(onlyForThisUser).projection(onlyNames).limit(20)
                .forEach((Block<? super Document>) doc -> docList.add(doc.toString()));

        for(int i = 0; i < docList.size(); i++) {
            docList.set(i, docList.get(i).replaceAll("Document\\{\\{genre_name=",""));
            docList.set(i, docList.get(i).replaceAll("}}","") );
        }

        return docList;
    }

    public boolean insertLikedMedia(String movieTitle) {
        if(findLikedMedia().contains(movieTitle)) {
            return true;
        };
        String likedMedia = findMedia(movieTitle).getFirst();
        MongoCollection<Document> collection = database.getCollection(LIKES_COLLECTION);
        Document doc1 = new Document("user_id", user_id).append("movie_title", likedMedia);
        try {
            collection.insertOne(doc1);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    public boolean deleteLikedMedia(String movieTitle) {
        if(findLikedMedia().contains(movieTitle)) {
            MongoCollection<Document> collection = database.getCollection(LIKES_COLLECTION);
            Bson onlyForThisUser = Filters.eq("user_id", user_id);
            Bson onlyThisMedia = Filters.eq("movie_title", movieTitle);
            Bson removeQuery = Filters.and(onlyForThisUser, onlyThisMedia);
            try {
                collection.deleteOne(removeQuery);
                return true;
            }
            catch(Exception e) {
                return false;
            }
        }
        else {
            return true;
        }

    }
    public ArrayList<String> findLikedMedia() {

        MongoCollection<Document> collection = database.getCollection(LIKES_COLLECTION);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson onlyNames = Projections.fields(
                Projections.include("movie_title"),
                Projections.excludeId());
        ArrayList<String> docList = new ArrayList<String>();
        collection.find(onlyForThisUser).projection(onlyNames).limit(20)
                .forEach((Block<? super Document>) doc -> docList.add(doc.toString()));

        for(int i = 0; i < docList.size(); i++) {
            docList.set(i, docList.get(i).replaceAll("Document\\{\\{movie_title=",""));
            docList.set(i, docList.get(i).replaceAll("}}","") );
        }

        return docList;
    }

    public boolean insertWatchedMedia(String movieTitle) {
        if(findWatchedMedia().contains(movieTitle)) {
            return true;
        };
        String watchedMedia = findMedia(movieTitle).getFirst();


        MongoCollection<Document> collection = database.getCollection(WATCHED_COLLECTION);
        Document doc1 = new Document("user_id", user_id).append("movie_title", watchedMedia);
        try {
            collection.insertOne(doc1);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    public boolean deleteWatchedMedia(String movieTitle) {
        if(findWatchedMedia().contains(movieTitle)) {
            MongoCollection<Document> collection = database.getCollection(WATCHED_COLLECTION);
            Bson onlyForThisUser = Filters.eq("user_id", user_id);
            Bson onlyThisMedia = Filters.eq("movie_title", movieTitle);
            Bson removeQuery = Filters.and(onlyForThisUser, onlyThisMedia);
            try {
                collection.deleteOne(removeQuery);
                return true;
            }
            catch(Exception e) {
                return false;
            }
        }
        else {
            return true;
        }

    }
    public ArrayList<String> findWatchedMedia() {

        MongoCollection<Document> collection = database.getCollection(WATCHED_COLLECTION);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson onlyNames = Projections.fields(
                Projections.include("movie_title"),
                Projections.excludeId());
        ArrayList<String> docList = new ArrayList<String>();
        collection.find(onlyForThisUser).projection(onlyNames).limit(20)
                .forEach((Block<? super Document>) doc -> docList.add(doc.toString()));

        for(int i = 0; i < docList.size(); i++) {
            docList.set(i, docList.get(i).replaceAll("Document\\{\\{movie_title=",""));
            docList.set(i, docList.get(i).replaceAll("}}","") );
        }

        return removeDuplicates(docList);
    }
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
        ArrayList<String> d = removeDuplicates(docList);
        return d;
    }

    /*public ArrayList<String> findMediaSorted(String mediaName, String sorting) {
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);

        Bson sort = null;

        if(sortCriteria.equals("_id")) {
            sort = Aggregates.sort(Sorts.ascending("movie_title"));
        }
        else {
            if (isAscend) {
                sort = Aggregates.sort(Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = Aggregates.sort(Sorts.descending(sortCriteria));

            }
        }

        MongoCollection<Document> c = database.getCollection(PRODUCTION_COLLECTION);

        Bson mediaFinder = Filters.regex("movie_title", mediaName);

        Bson onlyNames = Projections.fields(
                Projections.include("movie_title"),
                Projections.excludeId());

        Bson combineToProd = Aggregates
                .lookup(MEDIA_COLLECTION, "movie_id", "movie_id", "media");
        Bson out = Aggregates.out("media_production");
        Bson sizeLimiter = Aggregates.limit(20);
        Bson mediaFinds = Aggregates.match(mediaFinder);
        Document replaceRootStage = new Document("$replaceRoot",
                new Document("newRoot",
                        new Document("$mergeObjects",
                                Arrays.asList(
                                        new Document("$arrayElemAt", Arrays.asList("$media", 0)),
                                        "$$ROOT"
                                )
                        )
                )
        );


        List<Document> results = c.aggregate( Arrays.asList(combineToProd, replaceRootStage, mediaFinds, sort, sizeLimiter, out) ).into(new ArrayList<>());

        ArrayList<String> docList = new ArrayList<String>();
        for(Document i: results) {
            String s = (String) i.get("movie_title");
            docList.add((String) i.get("movie_title"));
            //System.out.printf("%s %n", s);
        }

        docList = removeDuplicates(docList);
        return docList;
    }
*/
    private boolean sortWhichWay(String sorting) {
        if(Objects.equals(sorting, "alphabetical") ||
                Objects.equals(sorting, "ratingLow")    ||
                Objects.equals(sorting, "yearOldest")   ||
                Objects.equals(sorting, "runtimeShortest"))
        {
            return true;
        }
        else {
            return false;
        }
    }

    private String sortCriteria(String sorting) {
        return switch (sorting) {
            case "ratingHigh", "ratingLow" -> "avg_rating";
            case "yearNewest", "yearOldest" -> "year";
            case "runtimeLongest", "runtimeShortest" -> "run_time";
            default -> "_id";
        };
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

    // mediaAndProductionJoin does not work at all
    // clean up findWatchedMediaSorted and findDetails
    private List<Document> mediaAndProductionJoin(String mediaName, String sorting, int fCode) {
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);
        System.out.printf("joining %n");
        Bson sort = null;
        Document projectStage = new Document("$project",
                new Document("movie_title", 1)
                        .append("movie_id", 1)
                        .append("format", 1)
                        .append("run_time", 1)
                        .append("avg_rating",
                                new Document("$ifNull", Arrays.asList("$avg_rating", 0))
                        ).append("year",
                                new Document("$ifNull", Arrays.asList("$year", 0))
                        )
        );
        if(sortCriteria.equals("_id")) {
            sort = Aggregates.sort(Sorts.ascending("movie_title"));
        }
        else {

            if (isAscend) {

                sort = Aggregates.sort(Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = Aggregates.sort(Sorts.descending(sortCriteria));

            }
        }

        MongoCollection<Document> c = database.getCollection(MEDIA_COLLECTION);

        Bson mediaFinder = Filters.regex("movie_title", mediaName);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson onlyNames = Projections.fields(
                Projections.include("movie_title"),
                Projections.excludeId());

        Bson combineToProd = Aggregates
                .lookup(PRODUCTION_COLLECTION, "movie_id", "movie_id", "media");
        Bson out = Aggregates.out("media_production");
        Bson sizeLimiter = Aggregates.limit(20);
        Bson mediaFinds = Aggregates.match(mediaFinder);
        Document replaceRootStage = new Document("$replaceRoot",
                new Document("newRoot",
                        new Document("$mergeObjects",
                                Arrays.asList(
                                        new Document("$arrayElemAt", Arrays.asList("$media", 0)),
                                        "$$ROOT"
                                )
                        )
                )
        );
        Document unwindStage = new Document("$unwind", "$media");
        Bson combineWatched = Aggregates
                .lookup(WATCHED_COLLECTION, "movie_title", "movie_title", "watched");
        Document redoRoot = new Document("$replaceRoot",
                new Document("newRoot",
                        new Document("$mergeObjects",
                                Arrays.asList(
                                        new Document("$arrayElemAt", Arrays.asList("$media", 0)),
                                        new Document("$arrayElemAt", Arrays.asList("$watched", 0)),
                                        "$$ROOT"
                                )
                        )
                )
        );
        System.out.printf("here goes %n");
        Bson usersWatched = Aggregates.match(onlyForThisUser);
        List<Document> results = null;
        if(fCode == 1) {
            results = c.aggregate(
                    Arrays.asList(
                            combineToProd,
                            //unwindStage,
                            replaceRootStage,
                            combineWatched,
                            redoRoot,
                            mediaFinds,
                            usersWatched,
                            projectStage,
                            sort,
                            //sizeLimiter,
                            out) ).into(new ArrayList<>());
        }
        else if(fCode == 2){
            results = c.aggregate( Arrays.asList(
                    combineToProd,
                    replaceRootStage,
                    mediaFinds,
                    projectStage,
                    sort,
                    out) ).into(new ArrayList<>());

           /* results = c.aggregate(
                    Arrays.asList(
                            combineToProd,
                            //unwindStage,
                            replaceRootStage,
                            //combineWatched,
                            //redoRoot,
                            mediaFinds,
                            //usersWatched,
                            sort,
                            //sizeLimiter,
                            out) ).into(new ArrayList<>()); */
        }

        System.out.printf("heres the docs %n");
        for(Document i: results) {
            System.out.printf("%s %n", i);
        }
        return results;
    }
    public ArrayList<String> findWatchedMediaSorted(String mediaName, String sorting) {
        ArrayList<Document> results = new ArrayList<>();//mediaAndProductionJoin(mediaName, sorting, 1);
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);

        Bson onlyIDs = Projections.fields(
                Projections.include("movie_id"),
                Projections.excludeId());
        Bson mediaFinder = Filters.regex("movie_title", mediaName);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson criteria = Filters.and(mediaFinder , onlyForThisUser);
        Bson sort = null;

        if(sortCriteria.equals("_id")) {
            sort = (Sorts.ascending("movie_title"));
        }
        else {
            if (isAscend) {
                sort = (Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = (Sorts.descending(sortCriteria));

            }
        }
        List<Document> getIDs = new ArrayList<>();
        database.getCollection(WATCHED_COLLECTION)
                .find(criteria)
                .into(getIDs);
        ArrayList<String> temp = new ArrayList<String>();
        ArrayList<String> temp2 = new ArrayList<String>();
        List<Document> t1 = new ArrayList<>();
        List<Document> t800 = new ArrayList<>();
        for(Document i: getIDs) {
            temp.add((String) i.get("movie_title"));
            //System.out.printf("%s %n", i);
        }

        if(sortCriteria.equals("avg_rating") || sortCriteria.equals("year")) {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .into(t1);
            ArrayList<String> t2 = new ArrayList<String>();
            for(Document i: t1) {
                t2.add((String) i.get("movie_id"));
                //System.out.printf("%s %n", i);
            }
            t1.clear();
            database.getCollection(PRODUCTION_COLLECTION)
                    .find(Filters.in("movie_id", t2))
                    .sort(sort)
                    .into(t1);
            for(Document i: t1) {
                temp2.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            database.getCollection(MEDIA_COLLECTION)
                    .find( Filters.in("movie_id", t2) )
                    //.sort( (Sorts.ascending("movie_title")) )
                    .into(t800);
            //for(Document i: t800) {
                //System.out.printf("%s %n", i);
           // }
            List<Document> r = new ArrayList<>();
            ArrayList<String> movieIDs = new ArrayList<String>();
            r.addAll(t1);
            r.addAll(t800);
            for(Document i: r) {
                movieIDs.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            for(String i: movieIDs) {
                database.getCollection(MEDIA_COLLECTION)
                        .find( Filters.in("movie_id", i) )
                        //.sort( (Sorts.ascending("movie_title")) )
                        .into(results);
            }

        }
        else  {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .sort(sort)
                    .into(results);
        }

        //results = removeDuplicates(results);



        ArrayList<String> docList = new ArrayList<String>();
        System.out.printf("firing %n");
        for(Document i: results) {
            try {
                //System.out.printf("%s %n", i.get("user_id"));

                String s = (String) i.get("movie_title");
                docList.add((String) i.get("movie_title"));
                System.out.printf("%s %n", i);


            }
            catch(Exception e) {}

        }

        //docList.removeAll(null);

        return removeDuplicates(docList);
    }

    // does not work
    // idk y
    // exactly the same as the above method
    // but uses the LIKES_COLLECTION
    // cant lookup
    public ArrayList<String> findLikedMediaSorted(String mediaName, String sorting) {
        ArrayList<Document> results = new ArrayList<>();//mediaAndProductionJoin(mediaName, sorting, 1);
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);

        Bson onlyIDs = Projections.fields(
                Projections.include("movie_id"),
                Projections.excludeId());
        Bson mediaFinder = Filters.regex("movie_title", mediaName);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson criteria = Filters.and(mediaFinder , onlyForThisUser);
        Bson sort = null;

        if(sortCriteria.equals("_id")) {
            sort = (Sorts.ascending("movie_title"));
        }
        else {
            if (isAscend) {
                sort = (Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = (Sorts.descending(sortCriteria));

            }
        }
        List<Document> getIDs = new ArrayList<>();
        database.getCollection(LIKES_COLLECTION)
                .find(criteria)
                .into(getIDs);
        ArrayList<String> temp = new ArrayList<String>();
        List<Document> t1 = new ArrayList<>();
        List<Document> t800 = new ArrayList<>();
        for(Document i: getIDs) {
            temp.add((String) i.get("movie_title"));
            //System.out.printf("%s %n", i);
        }

        if(sortCriteria.equals("avg_rating") || sortCriteria.equals("year")) {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .into(t1);
            ArrayList<String> t2 = new ArrayList<String>();
            for(Document i: t1) {
                t2.add((String) i.get("movie_id"));
                //System.out.printf("%s %n", i);
            }
            t1.clear();
            database.getCollection(PRODUCTION_COLLECTION)
                    .find(Filters.in("movie_id", t2))
                    .sort(sort)
                    .into(t1);
            for(Document i: t1) {
                t2.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            database.getCollection(PRODUCTION_COLLECTION)
                    .find( Filters.and(Filters.nin("movie_id", t2),
                            Filters.in("movie_id", t1) ) )
                    //.sort( (Sorts.ascending("movie_title")) )
                    .into(t800);
            //for(Document i: t800) {
            //System.out.printf("%s %n", i);
            // }
            List<Document> r = new ArrayList<>();
            ArrayList<String> movieIDs = new ArrayList<String>();
            r.addAll(t1);
            r.addAll(t800);
            for(Document i: r) {
                movieIDs.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            for(String i: movieIDs) {
                database.getCollection(MEDIA_COLLECTION)
                        .find( Filters.in("movie_id", i) )
                        //.sort( (Sorts.ascending("movie_title")) )
                        .into(results);
            }

        }
        else  {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .sort(sort)
                    .into(results);
        }

        //results = removeDuplicates(results);



        ArrayList<String> docList = new ArrayList<String>();
        System.out.printf("firing %n");
        for(Document i: results) {
            try {
                //System.out.printf("%s %n", i.get("user_id"));

                String s = (String) i.get("movie_title");
                docList.add((String) i.get("movie_title"));
                System.out.printf("%s %n", i);


            }
            catch(Exception e) {}

        }

        //docList.removeAll(null);

        return removeDuplicates(docList);
    }

    public ArrayList<String> findDetails(String movieName) {
        System.out.printf("is finding.exe? %n");
        MongoCollection<Document> collection = database.getCollection(MEDIA_COLLECTION);
        Bson mediaFinder = Filters.regex("movie_title", movieName);

        List<Document> docList = new ArrayList<Document>();
        collection.find(mediaFinder).limit(1)
                .into(docList);

        Document doc = docList.getFirst();
        ArrayList<String> details = new ArrayList<String>();
        details.add((String) doc.get("movie_title"));
        details.add((String) doc.get("run_time").toString());
        details.add((String) doc.get("format"));

        if(findWatchedMedia().contains(movieName) == true) {
            details.add("true");
        }
        else
            details.add("false");
        if(findLikedMedia().contains(movieName))
            details.add("true");
        else
            details.add("false");
        System.out.printf("join tables? %s %n", movieName);
        String movieID = (String) doc.get("movie_id");
        List<Document> r = new ArrayList<>();
        database.getCollection(PRODUCTION_COLLECTION)
                .find( Filters.eq("movie_id", movieID) ).into(r);
        try {
            Document results = r.getFirst();
            System.out.printf("%s %n", results);
            details.add((String) results.get("avg_rating").toString());
            details.add( (String) results.get("year").toString() );
        }
        catch(Exception e) {
            details.add("unknown");
            details.add("unknown");
        }
        MongoCollection<Document> c = database.getCollection(BELONGS);
        Bson belongs = Filters.regex("movie_id", movieID);
        List<Document> belongsList = new ArrayList<Document>();
        c.find(belongs)
                .into(belongsList);
        for(Document i: belongsList) {
            details.add((String) i.get("genres"));
        }

        return details;
    }

    public ArrayList<String> findMediaSorted(String mediaName, String sorting) {
        ArrayList<Document> results = new ArrayList<>();//mediaAndProductionJoin(mediaName, sorting, 1);
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);

        Bson onlyIDs = Projections.fields(
                Projections.include("movie_id"),
                Projections.excludeId());
        Bson mediaFinder = Filters.regex("movie_title", mediaName);
        //Bson onlyForThisUser = Filters.eq("user_id", user_id);
        Bson criteria = Filters.and(mediaFinder);// , onlyForThisUser);
        Bson sort = null;

        if(sortCriteria.equals("_id")) {
            sort = (Sorts.ascending("movie_title"));
        }
        else {
            if (isAscend) {
                sort = (Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = (Sorts.descending(sortCriteria));

            }
        }
        List<Document> getIDs = new ArrayList<>();
        database.getCollection(MEDIA_COLLECTION)
                .find(criteria)
                .into(getIDs);
        ArrayList<String> temp = new ArrayList<String>();
        List<Document> t1 = new ArrayList<>();
        List<Document> t800 = new ArrayList<>();
        for(Document i: getIDs) {
            temp.add((String) i.get("movie_title"));
            //System.out.printf("%s %n", i);
        }

        if(sortCriteria.equals("avg_rating") || sortCriteria.equals("year")) {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .into(t1);
            ArrayList<String> t2 = new ArrayList<String>();
            for(Document i: t1) {
                t2.add((String) i.get("movie_id"));
                //System.out.printf("%s %n", i);
            }
            t1.clear();
            database.getCollection(PRODUCTION_COLLECTION)
                    .find(Filters.in("movie_id", t2))
                    .sort(sort)
                    .into(t1);
            for(Document i: t1) {
                t2.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            database.getCollection(PRODUCTION_COLLECTION)
                    .find( Filters.and(Filters.nin("movie_id", t2),
                            Filters.in("movie_id", t1) ) )
                    //.sort( (Sorts.ascending("movie_title")) )
                    .into(t800);
            //for(Document i: t800) {
            //System.out.printf("%s %n", i);
            // }
            List<Document> r = new ArrayList<>();
            ArrayList<String> movieIDs = new ArrayList<String>();
            r.addAll(t1);
            r.addAll(t800);
            for(Document i: r) {
                movieIDs.add((String) i.get("movie_id"));
                System.out.printf("%s %n", i);
            }
            for(String i: movieIDs) {
                database.getCollection(MEDIA_COLLECTION)
                        .find( Filters.in("movie_id", i) )
                        //.sort( (Sorts.ascending("movie_title")) )
                        .into(results);
            }

        }
        else  {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_title", temp))
                    .sort(sort)
                    .into(results);
        }

        //results = removeDuplicates(results);



        ArrayList<String> docList = new ArrayList<String>();
        System.out.printf("generally speaking %n");
        for(Document i: results) {
            try {
                //System.out.printf("%s %n", i.get("user_id"));

                String s = (String) i.get("movie_title");
                docList.add((String) i.get("movie_title"));
                System.out.printf("%s %n", i);


            }
            catch(Exception e) {}

        }

        //docList.removeAll(null);

        return removeDuplicates(docList);
    }

    public ArrayList<String> findMediaParamSorted(String mediaName,
                                                  int yearfrom,
                                                  int yearto,
                                                  double runtimefrom,
                                                  double runtimeto,
                                                  double ratingfrom,
                                                  double ratingto,
                                                  String[] format,
                                                  String[] genre,
                                                  String sorting) {
        ArrayList<Document> results = new ArrayList<>();//mediaAndProductionJoin(mediaName, sorting, 1);
        boolean isAscend = sortWhichWay(sorting);
        String  sortCriteria = sortCriteria(sorting);

        Bson searchFinder = Filters.regex("movie_title", mediaName);
        Bson formatFinder = Filters.in("format", format);
        Bson runtimeFinder= Filters.and(
                Filters.lt("run_time", runtimeto),
                Filters.gt("run_time", runtimefrom)
        );

        //Bson idFinder = Filters.in("movie_id", mediaName);
        Bson yearFinder= Filters.and(
                Filters.lt("year", yearto),
                Filters.gt("year", yearfrom)
        );
        Bson ratingFinder= Filters.and(
                Filters.lt("avg_rating", ratingto),
                Filters.gt("avg_rating", ratingfrom)
        );

        Bson genreFinder = Filters.in("genres", genre);
        Bson onlyForThisUser = Filters.eq("user_id", user_id);

        Bson sort = null;

        if(sortCriteria.equals("_id")) {
            sort = (Sorts.ascending("movie_title"));
        }
        else {
            if (isAscend) {
                sort = (Sorts.ascending(sortCriteria));
            }
            if (!isAscend) {
                sort = (Sorts.descending(sortCriteria));

            }
        }

        ArrayList<String> mediaIDsTotal = new ArrayList<>();
        List<Document> mediaInCriteria = new ArrayList<>();
        Bson mediaCriteria = Filters.and(
                searchFinder,
                formatFinder,
                runtimeFinder
        );
        database.getCollection(MEDIA_COLLECTION)
                .find(mediaCriteria)
                .into(mediaInCriteria);

        for(Document i: mediaInCriteria) {
            mediaIDsTotal.add((String) i.get("media_id"));
        }

        ArrayList<String> ProdIDsTotal = new ArrayList<>();
        List<Document> prodInCriteria = new ArrayList<>();

        Bson idFinder = Filters.in("movie_id", mediaIDsTotal);
        Bson prodCriteria= Filters.and(
                idFinder,
                ratingFinder,
                yearFinder
        );
        database.getCollection(PRODUCTION_COLLECTION)
                .find(prodCriteria)
                .into(prodInCriteria);

        for(Document i: prodInCriteria) {
            ProdIDsTotal.add((String) i.get("media_id"));
        }

        ArrayList<String> genreIDsTotal = new ArrayList<>();
        List<Document> genreInCriteria = new ArrayList<>();
        Bson genreCriteria = Filters.and(
                idFinder,
                genreFinder
        );
        database.getCollection(BELONGS)
                .find(genreCriteria)
                .into(genreInCriteria);

        for(Document i: genreInCriteria) {
            genreIDsTotal.add((String) i.get("media_id"));
        }

        mediaIDsTotal.retainAll(ProdIDsTotal);
        mediaIDsTotal.retainAll(genreIDsTotal);

        List<Document> r = new ArrayList<>();

        if(sortCriteria.equals("avg_rating") || sortCriteria.equals("year")) {
            database.getCollection(PRODUCTION_COLLECTION)
                    .find( Filters.in("media_id", mediaIDsTotal) )
                    .sort(sort)
                    .into(r);
            ArrayList<String> finalStringSet = new ArrayList<>();
            for(Document i: r) {
                finalStringSet.add((String) i.get("media_id"));
            }
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_id", finalStringSet))
                    .into(results);
        }
        else  {
            database.getCollection(MEDIA_COLLECTION)
                    .find(Filters.in("movie_id", mediaIDsTotal))
                    .sort(sort)
                    .into(results);
        }

        ArrayList<String> docList = new ArrayList<String>();
        System.out.printf("putting it bluntly %n");
        for(Document i: results) {
            try {
                String s = (String) i.get("movie_title");
                docList.add((String) i.get("movie_title"));
                System.out.printf("%s %n", i);
            }
            catch(Exception e) {}

        }

        return removeDuplicates(docList);
    }
    private DatabaseAccessor() {

        if(db != null) {
            return;
        }
        db = this;
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
        //MongoIterable<Document> col = database.getCollection(MEDIA_COLLECTION).find().limit(20);
        //for (Document name : col) {
        //System.out.println(name);
        //}

    }
}
