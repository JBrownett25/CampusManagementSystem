import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;


    public class StudentManagementApp {
        private JFrame frame;
        private CardLayout cardLayout;
        private JPanel cardPanel;

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new StudentManagementApp().createAndShowGUI());
        }

private void createAndShowGUI() {
    frame = new JFrame("Student Management System");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 400);
    
    // Ensure the CSV file exists
    ensureFileExists();
    
    // Rest of the method...

            cardLayout = new CardLayout();
            cardPanel = new JPanel(cardLayout);

            // Add all pages
            cardPanel.add(mainMenuPanel(), "main");
            cardPanel.add(manageStudentsPanel(), "manageStudents");
            cardPanel.add(addStudentPanel(), "addStudent");
            cardPanel.add(editStudentPanel(), "editStudent");
            cardPanel.add(removeStudentPanel(), "removeStudent");
            cardPanel.add(viewStudentsPanel(), "viewStudents");
            cardPanel.add(manageRoomsPanel(), "manageRooms");
            cardPanel.add(libraryPanel(), "library");

            frame.add(cardPanel);
            frame.setVisible(true);
        }

        private JPanel mainMenuPanel() {
            JPanel panel = new JPanel(new GridLayout(4, 1));
            panel.add(new JLabel("Main Menu", SwingConstants.CENTER));
            JButton manageStudents = new JButton("Manage Students");
            JButton manageRooms = new JButton("Manage Rooms");
            JButton library = new JButton("Library");

            manageStudents.addActionListener(e -> cardLayout.show(cardPanel, "manageStudents"));
            manageRooms.addActionListener(e -> cardLayout.show(cardPanel, "manageRooms"));
            library.addActionListener(e -> cardLayout.show(cardPanel, "library"));

            panel.add(manageStudents);
            panel.add(manageRooms);
            panel.add(library);
            return panel;
        }

        private JPanel manageStudentsPanel() {
            JPanel panel = new JPanel(new GridLayout(6, 1));
            panel.add(new JLabel("Manage Students", SwingConstants.CENTER));

            JButton add = new JButton("Add Student");
            JButton edit = new JButton("Edit Student");
            JButton remove = new JButton("Remove Student");
            JButton view = new JButton("View Students");
            JButton exit = new JButton("Exit");

            add.addActionListener(e -> cardLayout.show(cardPanel, "addStudent"));
            edit.addActionListener(e -> cardLayout.show(cardPanel, "editStudent"));
            remove.addActionListener(e -> cardLayout.show(cardPanel, "removeStudent"));
            view.addActionListener(e -> cardLayout.show(cardPanel, "viewStudents"));
            exit.addActionListener(e -> cardLayout.show(cardPanel, "main"));

            panel.add(add);
            panel.add(edit);
            panel.add(remove);
            panel.add(view);
            panel.add(exit);
            return panel;
        }

        private JPanel addStudentPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Add Student", SwingConstants.CENTER), BorderLayout.NORTH);

            JPanel form = new JPanel(new GridLayout(5, 2));
            JTextField name = new JTextField();
            JTextField course = new JTextField();
            JTextField email = new JTextField();
            JTextField grade = new JTextField();

            form.add(new JLabel("Name:"));
            form.add(name);
            form.add(new JLabel("Course:"));
            form.add(course);
            form.add(new JLabel("Email:"));
            form.add(email);
            form.add(new JLabel("Grade:"));
            form.add(grade);

            JButton add = new JButton("Add Student");
            add.addActionListener(e -> {
                if (name.getText().isEmpty() || course.getText().isEmpty() || 
                    email.getText().isEmpty() || grade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                Student student = new Student(
                    name.getText().trim(), 
                    course.getText().trim(), 
                    email.getText().trim(), 
                    grade.getText().trim()
                );
        
                StudentDAO studentDAO = new StudentDAO();
                boolean success = studentDAO.saveStudent(student);
        
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Student added successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    name.setText("");
                    course.setText("");
                    email.setText("");
                    grade.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Error saving student data!", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
    
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(cardPanel, "manageStudents"));

            JPanel bottom = new JPanel();
            bottom.add(add);
            bottom.add(exit);

            panel.add(form, BorderLayout.CENTER);
            panel.add(bottom, BorderLayout.SOUTH);
            return panel;
        }

        private JPanel editStudentPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Edit Student", SwingConstants.CENTER), BorderLayout.NORTH);

            JPanel top = new JPanel(new FlowLayout());
            JTextField search = new JTextField(20);
            JButton searchBtn = new JButton("Search Student");
            top.add(new JLabel("Enter Name:"));
            top.add(search);
            top.add(searchBtn);

            JPanel form = new JPanel(new GridLayout(5, 2));
            JTextField name = new JTextField();
            JTextField course = new JTextField();
            JTextField email = new JTextField();
            JTextField grade = new JTextField();

            form.add(new JLabel("Name:"));
            form.add(name);
            form.add(new JLabel("Course:"));
            form.add(course);
            form.add(new JLabel("Email:"));
            form.add(email);
            form.add(new JLabel("Grade:"));
            form.add(grade);

            // Make fields initially disabled until a student is found
            name.setEnabled(false);
            course.setEnabled(false);
            email.setEnabled(false);
            grade.setEnabled(false);

            searchBtn.addActionListener(e -> {
                String studentName = search.getText().trim();
                if (studentName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a student name to search", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.findStudentByName(studentName);
                
                if (student != null) {
                    name.setText(student.getName());
                    course.setText(student.getCourse());
                    email.setText(student.getEmail());
                    grade.setText(student.getGrade());
                    
                    // Enable fields for editing
                    name.setEnabled(true);
                    course.setEnabled(true);
                    email.setEnabled(true);
                    grade.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found!", 
                        "Not Found", JOptionPane.WARNING_MESSAGE);
                    
                    // Clear and disable fields
                    name.setText("");
                    course.setText("");
                    email.setText("");
                    grade.setText("");
                    name.setEnabled(false);
                    course.setEnabled(false);
                    email.setEnabled(false);
                    grade.setEnabled(false);
                }
            });

            JButton edit = new JButton("Edit Student");
            edit.addActionListener(e -> {
                if (!name.isEnabled()) {
                    JOptionPane.showMessageDialog(frame, "Please search for a student first", 
                        "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (name.getText().isEmpty() || course.getText().isEmpty() || 
                    email.getText().isEmpty() || grade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required!", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Student updatedStudent = new Student(
                    name.getText().trim(), 
                    course.getText().trim(), 
                    email.getText().trim(), 
                    grade.getText().trim()
                );
                
                StudentDAO studentDAO = new StudentDAO();
                boolean success = studentDAO.updateStudent(search.getText().trim(), updatedStudent);
                
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Student updated successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    search.setText("");
                    name.setText("");
                    course.setText("");
                    email.setText("");
                    grade.setText("");
                    name.setEnabled(false);
                    course.setEnabled(false);
                    email.setEnabled(false);
                    grade.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error updating student data!", 
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(cardPanel, "manageStudents"));

            JPanel bottom = new JPanel();
            bottom.add(edit);
            bottom.add(exit);

            panel.add(top, BorderLayout.NORTH);
            panel.add(form, BorderLayout.CENTER);
            panel.add(bottom, BorderLayout.SOUTH);
            return panel;
        }

        private JPanel removeStudentPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Remove Student", SwingConstants.CENTER), BorderLayout.NORTH);

            JPanel center = new JPanel(new FlowLayout());
            JTextField name = new JTextField(20);
            JButton removeBtn = new JButton("Remove Student");
            
            removeBtn.addActionListener(e -> {
                String studentName = name.getText().trim();
                if (studentName.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter a student name", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(frame, 
                    "Are you sure you want to remove student: " + studentName + "?", 
                    "Confirm Removal", JOptionPane.YES_NO_OPTION);
            
                if (confirm == JOptionPane.YES_OPTION) {
                    StudentDAO studentDAO = new StudentDAO();
                    boolean success = studentDAO.removeStudent(studentName);
            
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Student removed successfully!", 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                        name.setText("");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Student not found or error removing student!", 
                            "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            
            center.add(new JLabel("Enter Name:"));
            center.add(name);
            center.add(removeBtn);

            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(cardPanel, "manageStudents"));

            panel.add(center, BorderLayout.CENTER);
            panel.add(exit, BorderLayout.SOUTH);
            return panel;
        }

private JPanel viewStudentsPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel("View Students", SwingConstants.CENTER), BorderLayout.NORTH);

    // Create a model that prevents cell editing
    DefaultTableModel tableModel = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Make all cells not editable
        }
    };
    
    // Add column headers
    tableModel.addColumn("Name");
    tableModel.addColumn("Email");
    tableModel.addColumn("Course");
    tableModel.addColumn("Grade");
    
    // Create and customize table
    JTable table = new JTable(tableModel);
    table.setRowHeight(25);
    table.setFont(new Font("Arial", Font.PLAIN, 14));
    table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
    
    // Add scroll support
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(550, 300));
    
    // Create a panel for buttons at the bottom
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    
    // Create a refresh button
    JButton refreshBtn = new JButton("Refresh Data");
    refreshBtn.addActionListener(e -> {
        loadStudentData(tableModel);
    });
    
    // Exit button
    JButton exitBtn = new JButton("Back");
    exitBtn.addActionListener(e -> cardLayout.show(cardPanel, "manageStudents"));
    
    // Add buttons to panel
    buttonPanel.add(refreshBtn);
    buttonPanel.add(exitBtn);
    
    // Add components to main panel
    panel.add(scrollPane, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);
    
    // Load data from CSV
    loadStudentData(tableModel);
    
    return panel;
}

// Helper method to load student data from CSV
private void loadStudentData(DefaultTableModel tableModel) {
    // Clear existing data
    tableModel.setRowCount(0);
    
    StudentDAO studentDAO = new StudentDAO();
    List<Student> students = studentDAO.getAllStudents();
    
    if (students.isEmpty()) {
        // If no students in CSV, show a message
        JOptionPane.showMessageDialog(frame, 
            "No student records found in the database.", 
            "Empty Records", JOptionPane.INFORMATION_MESSAGE);
    } else {
        // Add each student to the table model
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getName(),
                student.getEmail(),
                student.getCourse(),
                student.getGrade()
            });
        }
    }
}

        private JPanel manageRoomsPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Manage Rooms", SwingConstants.CENTER), BorderLayout.NORTH);

            JPanel mainPanel = new JPanel();

            JButton bookButton = new JButton("Book Room");
            bookButton.addActionListener(e-> {
                BookRoomGUI bookGUI = new BookRoomGUI();
                bookGUI.BookRoomGUI();
            });

            JButton viewButton = new JButton("View Rooms");
            viewButton.addActionListener(e-> {
                RoomManagement roomManagement = new RoomManagement();
                roomManagement.getRooms();

            });


            mainPanel.add(bookButton); //add buttons to panel
            mainPanel.add(viewButton);
            panel.add(mainPanel, BorderLayout.CENTER);



            return panel
            ;
        }

        private JPanel libraryPanel() {
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new JLabel("Library", SwingConstants.CENTER), BorderLayout.NORTH);

            JPanel center = new JPanel(new FlowLayout());
            JTextField search = new JTextField(20);
            JButton searchBtn = new JButton("Find");
            searchBtn.addActionListener(e -> {
                String find = search.getText();
                Library lib = new Library();
                lib.getBook(find);
            });
            center.add(new JLabel("Search Content:"));
            center.add(search);
            center.add(searchBtn);

            JButton exit = new JButton("Exit");
            exit.addActionListener(e -> cardLayout.show(cardPanel, "main"));

            panel.add(center, BorderLayout.CENTER);
            panel.add(exit, BorderLayout.SOUTH);
            return panel;
        }

private void ensureFileExists() {
    File file = new File("Students.csv");
    if (!file.exists()) {
        try {
            file.createNewFile();
            // Write header
            try (FileWriter fw = new FileWriter(file);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Name,Email,Course,Grade");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    }