import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class KomparasiDB_Screen2 {
    private static JTextArea logArea;
    private static Process komparasiProcess;
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static final Logger logger = LoggerFactory.getLogger(KomparasiDB_Screen2.class);

    public static void showScreen() {
        JFrame frame = new JFrame("Log Komparasi MongoDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        JPanel buttonPanel = new JPanel();
        JButton btnBack = new JButton("Kembali");
        JButton btnStop = new JButton("Stop Proses");
        JButton btnMainMenu = new JButton("Kembali ke Menu Utama");

        btnBack.addActionListener(e -> {
            frame.dispose();
            KomparasiDB_Screen1.showScreen();
        });

        btnStop.addActionListener(e -> {
            if (komparasiProcess != null) {
                komparasiProcess.destroy();
                logArea.append("Proses komparasi dihentikan.\n");
            }
        });

        btnMainMenu.addActionListener(e -> {
            frame.dispose();
            MainMenu.showMenu();
        });

        buttonPanel.add(btnBack);
        buttonPanel.add(btnStop);
        buttonPanel.add(btnMainMenu);

        // Tambahkan footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(footer, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Jalankan proses komparasi
        startKomparasiProcess();
    }

    private static void startKomparasiProcess() {
        try {
            komparasiProcess = new ProcessBuilder("java", "-cp", "target/simulasi.jar", "MongoDBComparator").start();
            executorService.submit(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(komparasiProcess.getInputStream()));
                     FileWriter fw = new FileWriter("logs/compare.log", true);
                     BufferedWriter bw = new BufferedWriter(fw)) {
                    
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String finalLine = line;
                        SwingUtilities.invokeLater(() -> {
                            logArea.append(finalLine + "\n");
                            try {
                                bw.write(finalLine + "\n");
                                bw.flush();
                            } catch (IOException e) {
                                if (e instanceof IOException ioException) {
                                    logger.error("Error writing to log file", ioException);
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    logger.error("Error reading process output", e);
                }
            });
        } catch (Exception e) {
            logger.error("Error starting komparasi process", e);
        }
    }
} 
