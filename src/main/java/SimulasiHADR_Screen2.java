import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulasiHADR_Screen2 {
    private static JTextArea logClientA, logClientB, logPing, logApp;
    private static Process clientAProcess, clientBProcess, pingProcess;
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);
    private static final String LOG_DIR = "logs"; // Folder for log files
    private static final String CLIENT_A_LOG = LOG_DIR + "/client_a.log";
    private static final String CLIENT_B_LOG = LOG_DIR + "/client_b.log";
    private static final String PING_LOG = LOG_DIR + "/ping.log";
    private static final String APPLICATION_LOG = LOG_DIR + "/application.log";
    private static final Logger logger = LoggerFactory.getLogger(SimulasiHADR_Screen2.class);

    public static void showLogScreen() {
        createLogFiles(); // Ensure logs and directories are created

        JFrame frame = new JFrame("HA/DR Scenario - Log Monitoring");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel logPanel = new JPanel(new GridLayout(2, 2));

        // Log Client A
        logClientA = new JTextArea();
        logClientA.setEditable(false);
        JScrollPane scrollClientA = new JScrollPane(logClientA);
        logPanel.add(new JPanel(new BorderLayout()) {{
            add(new JLabel("ClientA Log", SwingConstants.CENTER), BorderLayout.NORTH);
            add(scrollClientA, BorderLayout.CENTER);
        }});

        // Log Client B
        logClientB = new JTextArea();
        logClientB.setEditable(false);
        JScrollPane scrollClientB = new JScrollPane(logClientB);
        logPanel.add(new JPanel(new BorderLayout()) {{
            add(new JLabel("ClientB Log", SwingConstants.CENTER), BorderLayout.NORTH);
            add(scrollClientB, BorderLayout.CENTER);
        }});

        // Log Ping
        logPing = new JTextArea();
        logPing.setEditable(false);
        JScrollPane scrollPing = new JScrollPane(logPing);
        logPanel.add(new JPanel(new BorderLayout()) {{
            add(new JLabel("Ping Log", SwingConstants.CENTER), BorderLayout.NORTH);
            add(scrollPing, BorderLayout.CENTER);
        }});

        // Log Aplikasi
        logApp = new JTextArea();
        logApp.setEditable(false);
        JScrollPane scrollApp = new JScrollPane(logApp);
        logPanel.add(new JPanel(new BorderLayout()) {{
            add(new JLabel("Application Log", SwingConstants.CENTER), BorderLayout.NORTH);
            add(scrollApp, BorderLayout.CENTER);
        }});

        // Footer and Stop Button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JLabel footer = new JLabel("Develop By Arvino - Selamat Mencoba", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 12));

        JButton stopButton = new JButton("Stop Process");
        stopButton.addActionListener(e -> stopProcesses());

        bottomPanel.add(footer, BorderLayout.CENTER);
        bottomPanel.add(stopButton, BorderLayout.EAST);

        frame.add(logPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add "Running Scenario HA/DR" button
        JButton runScenarioButton = new JButton("Running Scenario HA/DR");
        runScenarioButton.addActionListener(e -> startProcesses());
        bottomPanel.add(runScenarioButton, BorderLayout.WEST);

        frame.setVisible(true);
    }

    private static void createLogFiles() {
        File logDirectory = new File(LOG_DIR);
        if (!logDirectory.exists()) {
            logDirectory.mkdirs();  // Create logs directory if it doesn't exist
        }

        // Create log files if they do not exist
        createLogFile(CLIENT_A_LOG);
        createLogFile(CLIENT_B_LOG);
        createLogFile(PING_LOG);
        createLogFile(APPLICATION_LOG);
    }

    private static void createLogFile(String filePath) {
        try {
            File logFile = new File(filePath);
            if (!logFile.exists()) {
                logFile.createNewFile();  // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.err.println("Error creating log file: " + filePath);
        }
    }

    private static void startProcesses() {
        try {
            // Start processes
            clientAProcess = new ProcessBuilder("java", "-cp", "target/atlas-bcp-dr-1-jar-with-dependencies.jar", "ClientA").start();
            clientBProcess = new ProcessBuilder("java", "-cp", "target/atlas-bcp-dr-1-jar-with-dependencies.jar", "ClientB").start();
            pingProcess = new ProcessBuilder("java", "-cp", "target/atlas-bcp-dr-1-jar-with-dependencies.jar", "Ping").start();

            // Capture output from processes
            captureProcessOutput(clientAProcess, logClientA);
            captureProcessOutput(clientBProcess, logClientB);
            captureProcessOutput(pingProcess, logPing);

            // Read application log asynchronously
            readApplicationLog();
        } catch (IOException e) {
            SwingUtilities.invokeLater(() -> logApp.append("Error starting processes: " + e.getMessage() + "\n"));
        }
    }

    private static void captureProcessOutput(Process process, JTextArea logArea) {
        executorService.submit(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                 FileWriter fw = new FileWriter("logs/application.log", true);
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
                            logger.error("Error writing to log file", e);
                        }
                    });
                }
            } catch (Exception e) {
                logger.error("Error reading process output", e);
            }
        });
    }

    private static void readApplicationLog() {
        File logFile = new File(APPLICATION_LOG);
        if (!logFile.exists()) {
            logApp.append("No application log file found.\n");
            return;
        }

        executorService.submit(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    // Ensuring the variable 'line' is effectively final by passing it to the lambda
                    final String logLine = line;
                    SwingUtilities.invokeLater(() -> logApp.append(logLine + "\n"));
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> logApp.append("Error reading application log: " + e.getMessage() + "\n"));
            }
        });
    }

    private static void stopProcesses() {
        // Destroy processes if they are running
        if (clientAProcess != null) clientAProcess.destroy();
        if (clientBProcess != null) clientBProcess.destroy();
        if (pingProcess != null) pingProcess.destroy();

        // Shutdown the executor service
        executorService.shutdown();

        SwingUtilities.invokeLater(() -> logApp.append("All processes stopped.\n"));
    }
}
