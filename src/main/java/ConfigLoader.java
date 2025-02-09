import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.File;

public class ConfigLoader {
    private static final String CONFIG_FILE = "mongodb_config.properties";  // Configuration file path
    private static Properties properties = new Properties();

    // Static block to load properties file when class is loaded
    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }

    // Get a property by key
    public static String get(String key) {
        return properties.getProperty(key, ""); // Default to an empty string if key doesn't exist
    }

    // Save a property key-value pair to the properties file
    public static void set(String key, String value) {
        properties.setProperty(key, value);
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
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
}
