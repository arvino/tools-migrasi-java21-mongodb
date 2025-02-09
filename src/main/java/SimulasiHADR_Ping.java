import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulasiHADR_Ping {
    private static final Logger logger = LoggerFactory.getLogger(SimulasiHADR_Ping.class);

    public static void main(String[] args) {
        ConfigLoader.createLogsDirectory();
        
        String uri = ConfigLoader.get("KONEKSI_URI_PING");
        String username = ConfigLoader.get("USERNAME");
        String password = ConfigLoader.get("PASSWORD");
        int interval = Integer.parseInt(ConfigLoader.get("INTERVAL_THREAD_SLEEP"));

        String connectionString = "mongodb://" + username + ":" + password + "@" + uri;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            while (true) {
                Document pingCommand = new Document("ping", 1);
                Document result = mongoClient.getDatabase("admin").runCommand(pingCommand);
                logger.info("Ping successful: {}", result.toJson());
                Thread.sleep(interval * 10); // Ping setiap 10x interval Thread Sleep
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
