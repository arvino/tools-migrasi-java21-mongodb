import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.util.Properties;

public class VerValMigrasi_Screen1 {
    private static JFrame frame;

    public static void showScreen() {
        frame = new JFrame("Verifikasi dan Validasi Migrasi MongoDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));

        JLabel lblURI1 = new JLabel("URI Database Sumber (Sebelum Migrasi):");
        JTextField txtURI1 = new JTextField();
        JLabel lblURI2 = new JLabel("URI Database Tujuan (Setelah Migrasi):");
        JTextField txtURI2 = new JTextField();
        JLabel lblDBName1 = new JLabel("Nama Database Sumber:");
        JTextField txtDBName1 = new JTextField();
        JLabel lblDBName2 = new JLabel("Nama Database Tujuan:");
        JTextField txtDBName2 = new JTextField();
        JLabel lblCollection = new JLabel("Nama Collection:");
        JTextField txtCollection = new JTextField();

        JButton btnSimpan = new JButton("Simpan Konfigurasi");
        JButton btnRunning = new JButton("Running Verifikasi dan Validasi");

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
        panel.add(btnRunning);

        btnSimpan.addActionListener((ActionEvent e) -> {
            Properties properties = new Properties();
            properties.setProperty("URI_VERVAL_DATABASE1", txtURI1.getText());
            properties.setProperty("URI_VERVAL_DATABASE2", txtURI2.getText());
            properties.setProperty("VERVAL_DBNAME1", txtDBName1.getText());
            properties.setProperty("VERVAL_DBNAME2", txtDBName2.getText());
            properties.setProperty("VERVAL_COLLECTION", txtCollection.getText());

            try (FileOutputStream fos = new FileOutputStream("mongodb_config.properties")) {
                properties.store(fos, "Konfigurasi Verifikasi dan Validasi Migrasi MongoDB");
                JOptionPane.showMessageDialog(null, "Konfigurasi tersimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Gagal menyimpan konfigurasi!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRunning.addActionListener((ActionEvent e) -> {
            frame.dispose();
            VerValMigrasi_Screen2.showScreen();
        });

        // Tambahkan footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(panel, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
} 