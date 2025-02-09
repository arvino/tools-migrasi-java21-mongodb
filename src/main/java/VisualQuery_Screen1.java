import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisualQuery_Screen1 extends JFrame {
    private static final Logger logger = LoggerFactory.getLogger(VisualQuery_Screen1.class);
    private JTextField uriField;
    private JButton saveButton;
    private JButton verifyButton;

    public VisualQuery_Screen1() {
        setTitle("MongoDB Configuration");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel uriLabel = new JLabel("MongoDB URI:");
        uriField = new JTextField();
        saveButton = new JButton("Save URI");
        verifyButton = new JButton("Running Verifikasi dan Validasi");

        panel.add(uriLabel);
        panel.add(uriField);
        panel.add(saveButton);
        panel.add(verifyButton);

        add(panel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUri();
            }
        });

        verifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openVisualQueryScreen2();
            }
        });
    }

    private void saveUri() {
        String uri = uriField.getText().trim();
        if (uri.isEmpty()) {
            JOptionPane.showMessageDialog(this, "URI cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save the URI in mongodb_config.properties file
        Properties properties = new Properties();
        try (OutputStream output = new FileOutputStream("mongodb_config.properties")) {
            properties.setProperty("URI_visualquery", uri);
            properties.store(output, null);
            JOptionPane.showMessageDialog(this, "URI saved successfully!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving URI", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openVisualQueryScreen2() {
        new VisualQuery_Screen2().setVisible(true);
        this.setVisible(false);  // Close the current screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VisualQuery_Screen1().setVisible(true);
        });
    }
}
