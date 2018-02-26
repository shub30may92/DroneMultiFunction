package com.example.shubham.dronemultifunction;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.microsoft.azure.documentdb.DocumentClient;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;


public class MainActivity extends Activity {

    private static final String MONGO_CLIENT_URI = "mongodb://drone-barcode-reader:h5BKWwsqJWRK3TzjXNodITYvWWWtFiRpItLFj9vslsUjFjp2iVBGp7b7lrxyNwFangkqkkYzFXMi0pFJDNUybw==@drone-barcode-reader.documents.azure.com:10255/";
    private DocumentClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            MongoClientURI uri = new MongoClientURI("mongodb://drone-barcode-reader:h5BKWwsqJWRK3TzjXNodITYvWWWtFiRpItLFj9vslsUjFjp2iVBGp7b7lrxyNwFangkqkkYzFXMi0pFJDNUybw==@drone-barcode-reader.documents.azure.com:10255/?ssl=true&replicaSet=globaldb");
            MongoClient mongoClient = null;
            mongoClient = new MongoClient(uri);

            // Get database
            MongoDatabase database = mongoClient.getDatabase("db");

            // Get collection
            MongoCollection<Document> collection = database.getCollection("coll");
            System.out.println(String.format("Demo complete, please hold while resources are deleted"));
        } catch (Exception e) {
            System.out.println(String.format("DocumentDB GetStarted failed with %s", e));
        }
    }

}
