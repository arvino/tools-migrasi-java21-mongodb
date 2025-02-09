import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::showMenu);
    }

    public static void showMenu() {
        // Pastikan folder logs sudah dibuat
        ConfigLoader.createLogsDirectory();
        
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JButton btnVerValMigrasi = new JButton("Verifikasi dan Validasi Migrasi MongoDB Atlas");
        JButton btnSimulasiHADR = new JButton("Simulasi HA dan DR MongoDB Atlas");
        JButton btnKomparasiMongoDB = new JButton("Komparasi Collection dan Document MongoDB");
        JButton btnVisualQuery = new JButton("Visual Query Builder MongoDB Atlas");

        btnVerValMigrasi.addActionListener((ActionEvent e) -> {
            ConfigLoader.loadConfig("vervalmigrasi");
            frame.dispose();
            VerValMigrasi_Screen1.showScreen();
        });

        btnSimulasiHADR.addActionListener((ActionEvent e) -> {
            ConfigLoader.loadConfig("simulasihadr");
            frame.dispose();
            SimulasiHADR_Screen1.showScreen();
        });

        btnKomparasiMongoDB.addActionListener((ActionEvent e) -> {
            ConfigLoader.loadConfig("komparasidb");
            frame.dispose();
            KomparasiDB_Screen1.showScreen();
        });

        btnVisualQuery.addActionListener((ActionEvent e) -> {
            ConfigLoader.loadConfig("visualquery");
            frame.dispose();
            VisualQuery_Screen1.main(new String[]{});
        });

        buttonPanel.add(btnVerValMigrasi);
        buttonPanel.add(btnSimulasiHADR);
        buttonPanel.add(btnKomparasiMongoDB);
        buttonPanel.add(btnVisualQuery);

        // Tambahkan footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
} 