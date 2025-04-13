package view;

import controller.StudentManager;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MainView class provides a simple dashboard interface for the Smart Campus Management System.
 * It allows staff to add new students and view a list of registered students.
 */
public class MainView extends JFrame {

    // Instance of StudentManager to manage student operations.
    private StudentManager studentManager;

    // Components for student input.
    private JTextField idField;
    private JTextField nameField;
    private JTextField courseField;
    private JButton addButton;

    // Components for displaying student records.
    private DefaultListModel<String> studentListModel;
    private JList<String> studentList;

    /**
     * Constructs the MainView window and initialises the GUI components.
     */
    public MainView() {
        studentManager = new StudentManager();
        initializeComponents();
    }

    /**
     * Initialises and lays out all the GUI components.
     */
    private void initializeComponents() {
        // Set up frame properties.
        setTitle("Smart Campus Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centre the window on the screen.
        setLayout(new BorderLayout());

        // Create a panel for student input fields.
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Student ID input.
        inputPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        // Student Name input.
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Course input.
        inputPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        inputPanel.add(courseField);

        // Add student button.
        addButton = new JButton("Add Student");
        inputPanel.add(addButton);

        // Placeholder label to fill the grid cell.
        inputPanel.add(new JLabel(""));

        // Create a panel to display the list of students.
        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        JScrollPane listScrollPane = new JScrollPane(studentList);
        listScrollPane.setBorder(BorderFactory.createTitledBorder("Registered Students"));

        // Add panels to the frame.
        add(inputPanel, BorderLayout.NORTH);
        add(listScrollPane, BorderLayout.CENTER);

        // Set up event listener for the Add Student button.
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddStudent();
            }
        });
    }

    /**
     * Handles the event when the Add Student button is clicked.
     * This method validates input, adds a new student using the StudentManager,
     * updates the student list, and provides visual feedback to the user.
     */
    private void handleAddStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String name = nameField.getText().trim();
            String course = courseField.getText().trim();

            // Basic input validation.
            if (name.isEmpty() || course.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both a valid name and course.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a new student and add to the manager.
            Student student = new Student(id, name, course);
            studentManager.addStudent(student);
            updateStudentList();
            clearInputFields();
            JOptionPane.showMessageDialog(this, "Student added successfully.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid student ID. Please enter a numeric value.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Refreshes the student list display using data from the StudentManager.
     */
    private void updateStudentList() {
        studentListModel.clear();
        for (Student s : studentManager.getAllStudents()) {
            studentListModel.addElement(s.toString());
        }
    }

    /**
     * Clears the input fields after a student is added.
     */
    private void clearInputFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
    }

    /**
     * The main method that launches the MainView.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
    }
}
