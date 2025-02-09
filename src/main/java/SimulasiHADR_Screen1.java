import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Properties;

public class SimulasiHADR_Screen1 {
    private static JFrame frame;
    private static JTextArea propertiesDisplay;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimulasiHADR_Screen1::showScreen);
    }

    public static void showScreen() {
        frame = new JFrame("MongoDB Atlas HA/DR Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 700);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul besar
        JLabel titleLabel = new JLabel("MongoDB Atlas HA/DR Simulation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Input konfigurasi MongoDB
        JLabel userLabel = new JLabel("USERNAME:");
        gbc.gridy++;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);
        JTextField userInput = new JTextField(20);
        userInput.setText("arvino");
        gbc.gridx = 1;
        panel.add(userInput, gbc);

        JLabel passLabel = new JLabel("PASSWORD:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(passLabel, gbc);
        JPasswordField passInput = new JPasswordField(20);
        passInput.setText("Merdeka170845!");
        gbc.gridx = 1;
        panel.add(passInput, gbc);

        JLabel dbLabel = new JLabel("DB Name:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(dbLabel, gbc);
        JTextField dbInput = new JTextField("myTestDrDb", 20);
        gbc.gridx = 1;
        panel.add(dbInput, gbc);

        JLabel collectionLabel = new JLabel("DB Collection:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(collectionLabel, gbc);
        JTextField collectionInput = new JTextField("myTestDrCollection", 20);
        gbc.gridx = 1;
        panel.add(collectionInput, gbc);

        // Input MongoDB Connection URI
        JLabel clientALabel = new JLabel("URI Client A dengan &retryWrites=true:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(clientALabel, gbc);
        JTextArea clientAInput = new JTextArea(2, 30);
        clientAInput.setText("sistemcore-shard-00-00.w75zq.mongodb.net:27017,...");
        gbc.gridx = 1;
        panel.add(new JScrollPane(clientAInput), gbc);

        JLabel clientBLabel = new JLabel("URI Client B tanpa retry:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(clientBLabel, gbc);
        JTextArea clientBInput = new JTextArea(2, 30);
        clientBInput.setText("sistemcore-shard-00-01.w75zq.mongodb.net:27017/...");
        gbc.gridx = 1;
        panel.add(new JScrollPane(clientBInput), gbc);

        JLabel pingLabel = new JLabel("Koneksi URI_Ping:");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(pingLabel, gbc);
        JTextArea pingInput = new JTextArea(2, 30);
        pingInput.setText("sistemcore-shard-00-00.w75zq.mongodb.net:27017,...");
        gbc.gridx = 1;
        panel.add(new JScrollPane(pingInput), gbc);

        // Input Interval Thread Sleep
        JLabel intervalLabel = new JLabel("Input Interval Thread Sleep (ms):");
        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(intervalLabel, gbc);
        JTextField intervalInput = new JTextField("500", 10);
        gbc.gridx = 1;
        panel.add(intervalInput, gbc);

        JLabel intervalDesc = new JLabel(
                "<html><center>Interval menentukan jeda waktu antar insert data.<br>"
                        + "Semakin kecil angkanya, semakin cepat proses insert terjadi.</center></html>",
                SwingConstants.CENTER);
        intervalDesc.setFont(new Font("Arial", Font.PLAIN, 10));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(intervalDesc, gbc);

        // Tombol Simpan Koneksi
        JButton saveButton = new JButton("Simpan Koneksi");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(saveButton, gbc);

        // Area untuk menampilkan isi file properties
        propertiesDisplay = new JTextArea(6, 40);
        propertiesDisplay.setEditable(false);
        propertiesDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane propertiesScroll = new JScrollPane(propertiesDisplay);
        gbc.gridy++;
        panel.add(propertiesScroll, gbc);

        saveButton.addActionListener((ActionEvent e) -> {
            saveProperties(userInput.getText(), new String(passInput.getPassword()), dbInput.getText(),
                    collectionInput.getText(), clientAInput.getText(), clientBInput.getText(),
                    pingInput.getText(), intervalInput.getText());
            loadProperties();
        });

        // Tombol Running Scenario
        JButton runButton = new JButton("Running Scenario HA/DR");
        gbc.gridy++;
        panel.add(runButton, gbc);

        runButton.addActionListener(e -> {
            frame.dispose();
            SimulasiHADR_Screen2.showLogScreen();
        });

        // Footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(panel, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Load properties saat aplikasi pertama kali dibuka
        loadProperties();
    }

    private static void saveProperties(String username, String password, String dbName, String collection,
                                       String uriClientA, String uriClientB, String uriPing, String interval) {
        Properties properties = new Properties();
        properties.setProperty("USERNAME", username);
        properties.setProperty("PASSWORD", password);
        properties.setProperty("DB_NAME", dbName);
        properties.setProperty("DB_COLLECTION", collection);
        properties.setProperty("KONEKSI_URI_CLIENTA", uriClientA);
        properties.setProperty("KONEKSI_URI_CLIENTB", uriClientB);
        properties.setProperty("KONEKSI_URI_PING", uriPing);
        properties.setProperty("INTERVAL_THREAD_SLEEP", interval);

        try (FileOutputStream fos = new FileOutputStream("mongodb_config.properties")) {
            properties.store(fos, "Konfigurasi Koneksi MongoDB Atlas");
            JOptionPane.showMessageDialog(null, "Konfigurasi tersimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan konfigurasi!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void loadProperties() {
        File file = new File("mongodb_config.properties");
        if (!file.exists()) {
            propertiesDisplay.setText("File properties belum ada.");
            return;
        }

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            properties.load(fis);
            StringBuilder sb = new StringBuilder();
            properties.forEach((key, value) -> sb.append(key).append(" = ").append(value).append("\n"));
            propertiesDisplay.setText(sb.toString());
        } catch (IOException e) {
            propertiesDisplay.setText("Gagal membaca file properties.");
        }
    }
}
