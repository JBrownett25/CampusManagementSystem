package view;

import controller.MainController;
import model.Resource;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResourceManagementPanel extends JPanel {

    private MainController controller;

    // Components for Add/Update resource
    private JTextField resourceIdField, resourceNameField, quantityField;
    private JButton addResourceButton, updateResourceButton;

    // Components for Search/Remove resource
    private JTextField resourceSearchField;
    private JButton searchResourceButton, removeResourceButton;

    // Text area to display resource details
    private JTextArea resourceDisplayArea;

    public ResourceManagementPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        // Panel for Add/Update operations
        JPanel addUpdatePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addUpdatePanel.setBorder(BorderFactory.createTitledBorder("Add / Update Resource"));

        addUpdatePanel.add(new JLabel("Resource ID:"));
        resourceIdField = new JTextField();
        addUpdatePanel.add(resourceIdField);

        addUpdatePanel.add(new JLabel("Name:"));
        resourceNameField = new JTextField();
        addUpdatePanel.add(resourceNameField);

        addUpdatePanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        addUpdatePanel.add(quantityField);

        addResourceButton = new JButton("Add Resource");
        addUpdatePanel.add(addResourceButton);
        updateResourceButton = new JButton("Update Resource");
        addUpdatePanel.add(updateResourceButton);

        // Panel for Search/Remove operations
        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search / Remove Resource"));

        searchPanel.add(new JLabel("Resource ID:"));
        resourceSearchField = new JTextField();
        searchPanel.add(resourceSearchField);

        searchResourceButton = new JButton("Search");
        searchPanel.add(searchResourceButton);
        removeResourceButton = new JButton("Remove Resource");
        searchPanel.add(removeResourceButton);

        // Text area for resource details
        resourceDisplayArea = new JTextArea(8, 30);
        resourceDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resourceDisplayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resource Details"));

        // Combine the panels
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.add(addUpdatePanel);
        topPanel.add(searchPanel);

        setLayout(new BorderLayout(10, 10));
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Set up event listeners
        addResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addResource();
            }
        });

        updateResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateResource();
            }
        });

        searchResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchResource();
            }
        });

        removeResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeResource();
            }
        });
    }

    private void addResource() {
        String id = resourceIdField.getText().trim();
        String name = resourceNameField.getText().trim();
        String qtyText = quantityField.getText().trim();
        if (id.isEmpty() || name.isEmpty() || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int quantity = Integer.parseInt(qtyText);
            controller.addResource(id, name, quantity);
            JOptionPane.showMessageDialog(this, "Resource added successfully.");
            clearFields();
            updateDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateResource() {
        String id = resourceIdField.getText().trim();
        String name = resourceNameField.getText().trim();
        String qtyText = quantityField.getText().trim();
        if (id.isEmpty() || name.isEmpty() || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int quantity = Integer.parseInt(qtyText);
            controller.updateResource(id, name, quantity);
            JOptionPane.showMessageDialog(this, "Resource updated successfully.");
            clearFields();
            updateDisplay();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchResource() {
        String id = resourceSearchField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Resource ID to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Assuming MainController has been updated to provide a searchResource method:
        Resource resource = controller.searchResource(id);
        if (resource != null) {
            resourceDisplayArea.setText(resource.toString());
        } else {
            resourceDisplayArea.setText("Resource not found.");
        }
    }

    private void removeResource() {
        String id = resourceSearchField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Resource ID to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.removeResource(id);
        JOptionPane.showMessageDialog(this, "Resource removed (if it existed).");
        updateDisplay();
    }

    private void clearFields() {
        resourceIdField.setText("");
        resourceNameField.setText("");
        quantityField.setText("");
        resourceSearchField.setText("");
    }

    private void updateDisplay() {
        // For a complete implementation, you would retrieve and display all resources.
        resourceDisplayArea.setText("Display update functionality not implemented.");
    }
}
