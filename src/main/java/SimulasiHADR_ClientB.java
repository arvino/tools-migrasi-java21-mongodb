import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulasiHADR_ClientB {
    private static final Logger logger = LoggerFactory.getLogger(SimulasiHADR_ClientB.class);

    public static void main(String[] args) {
        ConfigLoader.createLogsDirectory();
        
        String uri = ConfigLoader.get("KONEKSI_URI_CLIENTB");
        String username = ConfigLoader.get("USERNAME");
        String password = ConfigLoader.get("PASSWORD");
        String dbName = ConfigLoader.get("DB_NAME");
        String collectionName = ConfigLoader.get("DB_COLLECTION");
        int interval = Integer.parseInt(ConfigLoader.get("INTERVAL_THREAD_SLEEP"));

        String connectionString = "mongodb://" + username + ":" + password + "@" + uri;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            while (true) {
                String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                String message = "hello from client B (" + timestamp + ")";
                Document doc = new Document("client", "B")
                    .append("message", message)
                    .append("timestamp", System.currentTimeMillis());
                collection.insertOne(doc);
                logger.info("Inserted document in ClientB: {}", doc.toJson());
                Thread.sleep(interval); // Menggunakan interval dari properties
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
