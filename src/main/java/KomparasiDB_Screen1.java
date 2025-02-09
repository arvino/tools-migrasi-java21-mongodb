import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.util.Properties;


public class KomparasiDB_Screen1 {

    private static JFrame frame;

    public static void showScreen() {
        frame = new JFrame("Konfigurasi Komparasi MongoDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel lblURI1 = new JLabel("URI Database 1:");
        JTextField txtURI1 = new JTextField();
        JLabel lblURI2 = new JLabel("URI Database 2:");
        JTextField txtURI2 = new JTextField();
        JLabel lblDBName1 = new JLabel("Nama Database 1:");
        JTextField txtDBName1 = new JTextField();
        JLabel lblDBName2 = new JLabel("Nama Database 2:");
        JTextField txtDBName2 = new JTextField();
        JLabel lblCollection = new JLabel("Nama Collection:");
        JTextField txtCollection = new JTextField();

        JButton btnSimpan = new JButton("Simpan Konfigurasi");
        JButton btnRunning = new JButton("Running Komparasi");
        JButton btnMainMenu = new JButton("Kembali ke Menu Utama");

        panel.add(lblURI1);
        panel.add(txtURI1);
        panel.add(lblURI2);
        panel.add(txtURI2);
        panel.add(lblDBName1);
        panel.add(txtDBName1);
        panel.add(lblDBName2);
        panel.add(txtDBName2);
        panel.add(lblCollection);
        panel.add(txtCollection);
        panel.add(btnSimpan);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnRunning);
        panel.add(Box.createVerticalStrut(10));
        panel.add(btnMainMenu);

        btnSimpan.addActionListener((ActionEvent e) -> {
            saveProperties(txtURI1.getText(), txtURI2.getText(), txtDBName1.getText(), txtDBName2.getText(), txtCollection.getText());
        });

        btnRunning.addActionListener((ActionEvent e) -> {
            frame.dispose();
            KomparasiDB_Screen2.showScreen();
        });

        btnMainMenu.addActionListener(e -> {
            frame.dispose();
            MainMenu.showMenu();
        });

        // Tambahkan footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(panel, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void saveProperties(String uri1, String uri2, String dbName1, 
                                      String dbName2, String collection) {
        Properties properties = new Properties();
        properties.setProperty("URI_DATABASE1", uri1);
        properties.setProperty("URI_DATABASE2", uri2);
        properties.setProperty("DB_NAME1", dbName1);
        properties.setProperty("DB_NAME2", dbName2);
        properties.setProperty("COLLECTION_NAME", collection);

        ConfigLoader.saveConfig("komparasidb", properties);
        JOptionPane.showMessageDialog(null, "Konfigurasi tersimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }
} 
