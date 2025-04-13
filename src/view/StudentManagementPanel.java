package view;

import controller.MainController;
import model.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementPanel extends JPanel {

    private MainController controller;

    // Components for Add/Update
    private JTextField idField, nameField, courseField;
    private JButton addButton, updateButton;

    // Components for Search/Remove
    private JTextField searchIdField;
    private JButton searchButton, removeButton;

    // Text area to display search results/details
    private JTextArea resultArea;

    public StudentManagementPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        // Use a border layout for overall panel
        setLayout(new BorderLayout(10, 10));

        // Panel for Add/Update operations
        JPanel addUpdatePanel = new JPanel(new GridLayout(4, 2, 5, 5));
        addUpdatePanel.setBorder(BorderFactory.createTitledBorder("Add / Update Student"));

        addUpdatePanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        addUpdatePanel.add(idField);

        addUpdatePanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        addUpdatePanel.add(nameField);

        addUpdatePanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        addUpdatePanel.add(courseField);

        addButton = new JButton("Add Student");
        addUpdatePanel.add(addButton);
        updateButton = new JButton("Update Student");
        addUpdatePanel.add(updateButton);

        // Panel for Search/Remove operations
        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search / Remove Student"));

        searchPanel.add(new JLabel("Search by ID:"));
        searchIdField = new JTextField();
        searchPanel.add(searchIdField);

        searchButton = new JButton("Search");
        searchPanel.add(searchButton);
        removeButton = new JButton("Remove Student");
        searchPanel.add(removeButton);

        // Text area to display student details
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setBorder(BorderFactory.createTitledBorder("Student Details"));

        // Combine the add/update and search panels into a top panel
        JPanel topPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        topPanel.add(addUpdatePanel);
        topPanel.add(searchPanel);

        add(topPanel, BorderLayout.NORTH);
        add(resultScrollPane, BorderLayout.CENTER);

        // Set up button listeners
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();
            if (name.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Course must not be empty",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.addStudent(id, name, course);
            JOptionPane.showMessageDialog(this, "Student added successfully");
            clearAddUpdateFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();
            if (controller.updateStudent(id, name, course)) {
                JOptionPane.showMessageDialog(this, "Student updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Student with given ID not found",
                        "Update Error", JOptionPane.ERROR_MESSAGE);
            }
            clearAddUpdateFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(searchIdField.getText().trim());
            Student student = controller.searchStudentById(id);
            if (student != null) {
                resultArea.setText(student.toString());
            } else {
                resultArea.setText("Student not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid search ID. Please enter a number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeStudent() {
        try {
            int id = Integer.parseInt(searchIdField.getText().trim());
            if (controller.removeStudent(id)) {
                JOptionPane.showMessageDialog(this, "Student removed successfully");
                resultArea.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Student not found.",
                        "Remove Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid ID. Please enter a number.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAddUpdateFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
    }
}
