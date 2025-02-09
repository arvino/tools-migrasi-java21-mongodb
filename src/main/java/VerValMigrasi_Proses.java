import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerValMigrasi_Proses {
    private static final Logger logger = LoggerFactory.getLogger(VerValMigrasi_Proses.class);

    public static void main(String[] args) {
        ConfigLoader.createLogsDirectory();
        
        try {
            // Muat konfigurasi dari file properties
            Properties properties = new Properties();
            try (FileInputStream fis = new FileInputStream("mongodb_config.properties")) {
                properties.load(fis);
            }

            // Ambil konfigurasi koneksi
            String uriDatabase1 = properties.getProperty("URI_VERVAL_DATABASE1");
            String uriDatabase2 = properties.getProperty("URI_VERVAL_DATABASE2");
            String dbName1 = properties.getProperty("VERVAL_DBNAME1");
            String dbName2 = properties.getProperty("VERVAL_DBNAME2");
            String collectionName = properties.getProperty("VERVAL_COLLECTION");

            // Buat koneksi ke database sumber dan tujuan
            try (MongoClient client1 = MongoClients.create(uriDatabase1);
                 MongoClient client2 = MongoClients.create(uriDatabase2)) {

                MongoDatabase database1 = client1.getDatabase(dbName1);
                MongoDatabase database2 = client2.getDatabase(dbName2);

                MongoCollection<Document> collection1 = database1.getCollection(collectionName);
                MongoCollection<Document> collection2 = database2.getCollection(collectionName);

                // Verifikasi jumlah dokumen
                long count1 = collection1.countDocuments();
                long count2 = collection2.countDocuments();
                logger.info("Jumlah dokumen di database sumber: {}", count1);
                logger.warn("Peringatan: Jumlah dokumen tidak sama!");

                if (count1 != count2) {
                    logger.warn("Peringatan: Jumlah dokumen tidak sama!");
                }

                // Verifikasi konsistensi data
                Set<Document> documents1 = new HashSet<>();
                Set<Document> documents2 = new HashSet<>();

                collection1.find().into(documents1);
                collection2.find().into(documents2);

                if (documents1.equals(documents2)) {
                    logger.info("Verifikasi berhasil: Data konsisten antara database sumber dan tujuan.");
                } else {
                    logger.warn("Peringatan: Data tidak konsisten antara database sumber dan tujuan.");
                }
            }
        } catch (Exception e) {
            logger.error("Error selama proses verifikasi dan validasi: " + e.getMessage());
        }
    }
} 