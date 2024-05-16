import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import java.util.Arrays;
public class MongoCRUDExample {
    public static void main(String[] args) {
        // URI di connessione a MongoDB
        String uri = "mongodb://root:example@localhost:27017/new_db.coll1?authSource=admin";

        // Crea una connessione MongoDB
        ConnectionString connectionString = new ConnectionString(uri);
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            // Ottieni il database
            MongoDatabase database = mongoClient.getDatabase("new_db");

            // Ottieni la collection
            MongoCollection<Document> collection = database.getCollection("coll1");


            // Some examples of docs
            Document doc1 = new Document("name", "John Doe")
                    .append("age", 29)
                    .append("city", "New York");

            Document doc2 = new Document("name", "Jane Smith")
                    .append("age", 34)
                    .append("city", "Los Angeles");

            Document doc3 = new Document("name", "Alice Johnson")
                    .append("age", 23)
                    .append("city", "Chicago");

            // INSERT MORE DOCS
            collection.insertMany(Arrays.asList(doc1, doc2, doc3));


            // INSERT DOCUMENT
            Document doc4 = new Document("name", "John Doe")
                    .append("age", 29)
                    .append("city", "New York");

            collection.insertOne(doc4);
            System.out.println("((insert)New document: " + doc4.toJson());

            // READ DOCUMENT
            Document foundDoc = collection.find(Filters.eq("name", "John Doe")).first();
            if (foundDoc != null) {
                System.out.println("read(doc): " + foundDoc.toJson());
            } else {
                System.out.println("Doc not found.");
            }

            // UPDATE DOCUMENT
            collection.updateOne(Filters.eq("name", "John Doe"), Updates.set("age", 30));
            Document updatedDoc = collection.find(Filters.eq("name", "John Doe")).first();
            if (updatedDoc != null) {
                System.out.println("Update: " + updatedDoc.toJson());
            } else {
                System.out.println("Doc not found for update.");
            }

            //DELETE DOCUMENT
            collection.deleteOne(Filters.eq("name", "John Doe"));
            Document deletedDoc = collection.find(Filters.eq("name", "John Doe")).first();
            if (deletedDoc == null) {
                System.out.println("Doc deleted.");
            } else {
                System.out.println("Doc not found for del");
            }
        }
    }
}

