package dbConnection;

import com.example.shubham.dronemultifunction.InventoryItem;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by shubgupta on 2/8/18.
 */

public class MongoClientDB {

    private static final String MONGO_CLIENT_URI = "mongodb://drone-barcode-reader:h5BKWwsqJWRK3TzjXNodITYvWWWtFiRpItLFj9vslsUjFjp2iVBGp7b7lrxyNwFangkqkkYzFXMi0pFJDNUybw==@drone-barcode-reader.documents.azure.com:10255/?ssl=true&replicaSet=globaldb";
    private static MongoClientDB mongoClientDB;
    private MongoCollection<Document> collection;
    private MongoDatabase database;


    public static MongoClientDB getInstance() {
        if(mongoClientDB == null) {
            mongoClientDB = new MongoClientDB();
        }
        return mongoClientDB;
    }

    private MongoClientDB() {
        MongoClientURI uri = new MongoClientURI(MONGO_CLIENT_URI);
        MongoClient mongoClient = null;
        mongoClient = new MongoClient(uri);

        // Get database
        database = mongoClient.getDatabase("db");

        // Get collection
        collection = database.getCollection("coll");

//        // Insert documents
//        Document document1 = new Document("fruit", "apple");
//        collection.insertOne(document1);
//
//        Document document2 = new Document("fruit", "mango");
//        collection.insertOne(document2);
//
//        // Find fruits by name
//        Document queryResult = collection.find(Filters.eq("fruit", "apple")).first();
//        System.out.println(queryResult.toJson());
//
//        System.out.println( "Completed successfully" );

    }

    public void mongoInsertData(InventoryItem item) {
        Document document = new Document("barcode", item.getName());
        collection.insertOne(document);
    }
}
