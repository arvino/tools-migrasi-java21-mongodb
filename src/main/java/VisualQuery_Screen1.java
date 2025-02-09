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

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel uriLabel = new JLabel("MongoDB URI:");
        uriField = new JTextField(20);

        saveButton = new JButton("Save URI");
        verifyButton = new JButton("Running Verifikasi dan Validasi");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(uriLabel, gbc);

        gbc.gridy++;
        panel.add(uriField, gbc);

        gbc.gridy++;
        panel.add(saveButton, gbc);

        gbc.gridy++;
        panel.add(verifyButton, gbc);

        JButton btnMainMenu = new JButton("Kembali ke Menu Utama");
        btnMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Tutup frame saat ini
                MainMenu.showMenu(); // Tampilkan menu utama
            }
        });
        gbc.gridy++;
        panel.add(btnMainMenu, gbc);

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

        Properties properties = new Properties();
        properties.setProperty("URI_VISUALQUERY", uri);
        
        ConfigLoader.saveConfig("visualquery", properties);
        JOptionPane.showMessageDialog(this, "URI saved successfully!");
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
