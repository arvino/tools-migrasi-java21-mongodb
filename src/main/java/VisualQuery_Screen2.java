import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualQuery_Screen2 extends JFrame {
    private JTextArea queryArea;
    private JButton runQueryButton;
    private JTextArea resultArea;

    public VisualQuery_Screen2() {
        setTitle("MongoDB Query Builder");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        queryArea = new JTextArea();
        JScrollPane queryScrollPane = new JScrollPane(queryArea);
        queryArea.setLineWrap(true);
        queryArea.setWrapStyleWord(true);

        runQueryButton = new JButton("Run Query");

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        JButton btnMainMenu = new JButton("Kembali ke Menu Utama");
        btnMainMenu.addActionListener(e -> {
            this.dispose();
            MainMenu.showMenu();
        });

        panel.add(queryScrollPane, BorderLayout.NORTH);
        panel.add(runQueryButton, BorderLayout.CENTER);
        panel.add(resultScrollPane, BorderLayout.SOUTH);
        panel.add(btnMainMenu);

        add(panel);

        runQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeQuery();
            }
        });
    }

    private void executeQuery() {
        // For now, just display the query as result. Implement MongoDB query execution logic here.
        String query = queryArea.getText();
        if (query.isEmpty()) {
            resultArea.setText("Query cannot be empty.");
            return;
        }

        // You can integrate MongoDB Java driver here to execute queries.
        resultArea.setText("Running query:\n" + query);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VisualQuery_Screen2().setVisible(true);
        });
    }
}
