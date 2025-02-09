import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualQuery_Builder extends JPanel {
    private JComboBox<String> fieldComboBox;
    private JComboBox<String> operatorComboBox;
    private JTextField valueField;
    private JButton addButton;
    private DefaultListModel<String> queryListModel;

    public VisualQuery_Builder() {
        setLayout(new BorderLayout());

        // Query Components
        JPanel queryPanel = new JPanel();
        queryPanel.setLayout(new FlowLayout());

        fieldComboBox = new JComboBox<>(new String[]{"client", "timestamp"});
        operatorComboBox = new JComboBox<>(new String[]{"=", ">", "<"});
        valueField = new JTextField(10);
        addButton = new JButton("Add Clause");

        queryPanel.add(new JLabel("Field:"));
        queryPanel.add(fieldComboBox);
        queryPanel.add(new JLabel("Operator:"));
        queryPanel.add(operatorComboBox);
        queryPanel.add(new JLabel("Value:"));
        queryPanel.add(valueField);
        queryPanel.add(addButton);

        // List to show the constructed query
        queryListModel = new DefaultListModel<>();
        JList<String> queryList = new JList<>(queryListModel);
        JScrollPane listScrollPane = new JScrollPane(queryList);

        add(queryPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Action for adding query clause
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String field = (String) fieldComboBox.getSelectedItem();
                String operator = (String) operatorComboBox.getSelectedItem();
                String value = valueField.getText();

                if (value.isEmpty()) {
                    JOptionPane.showMessageDialog(VisualQuery_Builder.this, "Value cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Add to list
                queryListModel.addElement(field + " " + operator + " " + value);
            }
        });
    }
}
