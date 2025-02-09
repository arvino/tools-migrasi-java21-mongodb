import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.File;

public class ConfigLoader {
    private static final String CONFIG_DIR = "config/";
    private static Properties properties = new Properties();

    public static void loadConfig(String moduleName) {
        String configFile = CONFIG_DIR + moduleName + ".properties";
        try (FileInputStream fis = new FileInputStream(configFile)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }

    // Method get tetap sama, tapi sekarang akan membaca dari file yang sesuai
    public static String get(String key) {
        return properties.getProperty(key, "");
    }

    // Save a property key-value pair to the properties file
    public static void set(String key, String value) {
        properties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream(CONFIG_DIR + key + ".properties")) {
            properties.store(output, null);
        } catch (IOException e) {
            System.err.println("Error saving configuration file: " + e.getMessage());
        }
    }

    // Get MongoDB URI for visual query (e.g., for VisualQuery_Screen1)
    public static String getMongoUri() {
        return get("URI_visualquery");
    }

    // Get the database name from the properties file
    public static String getDbName() {
        return get("DB_NAME");
    }

    // Get the collection name from the properties file
    public static String getCollectionName() {
        return get("DB_COLLECTION");
    }

    // Get the database URI based on client configuration
    public static String getClientUri(String clientKey) {
        return get(clientKey); // You can pass KONEKSI_URI_CLIENTA or KONEKSI_URI_CLIENTB
    }

    // Add additional specific getters for other properties as needed, e.g., username, password, etc.
    public static String getUsername() {
        return get("USERNAME");
    }

    public static String getPassword() {
        return get("PASSWORD");
    }

    // Check if a specific key exists in the configuration
    public static boolean containsKey(String key) {
        return properties.containsKey(key);
    }

    public static void createLogsDirectory() {
        File logsDir = new File("logs");
        if (!logsDir.exists()) {
            logsDir.mkdirs();
        }
    }

    public record MongoDBConfig(String uri, String dbName, String collection) {
        public static MongoDBConfig load() {
            return new MongoDBConfig(
                properties.getProperty("URI"),
                properties.getProperty("DB_NAME"),
                properties.getProperty("COLLECTION")
            );
        }
    }

    public static void saveConfig(String moduleName, Properties properties) {
        String configFile = CONFIG_DIR + moduleName + ".properties";
        try (OutputStream output = new FileOutputStream(configFile)) {
            properties.store(output, "Konfigurasi untuk " + moduleName);
        } catch (IOException e) {
            System.err.println("Error saving configuration file: " + e.getMessage());
        }
    }
}
