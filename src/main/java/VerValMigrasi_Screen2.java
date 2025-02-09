import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VerValMigrasi_Screen2 {
    private static JTextArea logArea;
    private static Process verValProcess;
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void showScreen() {
        JFrame frame = new JFrame("Log Verifikasi dan Validasi Migrasi MongoDB");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        JPanel buttonPanel = new JPanel();
        JButton btnBack = new JButton("Kembali");
        JButton btnStop = new JButton("Stop Proses");

        btnBack.addActionListener(e -> {
            frame.dispose();
            VerValMigrasi_Screen1.showScreen();
        });

        btnStop.addActionListener(e -> {
            if (verValProcess != null) {
                verValProcess.destroy();
                logArea.append("Proses verifikasi dan validasi dihentikan.\n");
            }
        });

        buttonPanel.add(btnBack);
        buttonPanel.add(btnStop);

        // Tambahkan footer
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(footer, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Jalankan proses verifikasi dan validasi
        startVerValProcess();
    }

    private static void startVerValProcess() {
        try {
            verValProcess = new ProcessBuilder("java", "-cp", "target/simulasi.jar", "VerValMigrasi_Proses").start();
            executorService.submit(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(verValProcess.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String finalLine = line;
                        SwingUtilities.invokeLater(() -> logArea.append(finalLine + "\n"));
                    }
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> logArea.append("Error membaca output proses: " + e.getMessage() + "\n"));
                }
            });
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> logArea.append("Error memulai proses verifikasi dan validasi: " + e.getMessage() + "\n"));
        }
    }
} 