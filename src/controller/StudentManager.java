package controller;

import model.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * The StudentManager class handles operations related to managing student records.
 * It provides methods for adding, updating, searching, removing, and displaying students.
 */
public class StudentManager {

    // List to store Student objects.
    private List<Student> students;

    /**
     * Constructs a new StudentManager and initialises the student storage.
     */
    public StudentManager() {
        students = new ArrayList<>();
    }

    /**
     * Adds a new student to the list.
     * If a student with the same ID already exists, the method displays a message.
     *
     * @param student the Student object to add.
     */
    public void addStudent(Student student) {
        if (searchStudentById(student.getId()) != null) {
            System.out.println("Student with ID " + student.getId() + " already exists.");
            return;
        }
        students.add(student);
        System.out.println("Student added: " + student);
    }

    /**
     * Updates an existing student's details.
     *
     * @param id the unique ID of the student to update.
     * @param name the new name for the student.
     * @param course the new course for the student.
     * @return true if the student was found and updated; false otherwise.
     */
    public boolean updateStudent(int id, String name, String course) {
        Student student = searchStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setCourse(course);
            System.out.println("Student updated: " + student);
            return true;
        } else {
            System.out.println("Student with ID " + id + " not found.");
            return false;
        }
    }

    /**
     * Searches for a student by their unique ID.
     *
     * @param id the unique ID of the student.
     * @return the Student if found; null otherwise.
     */
    public Student searchStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Removes a student by their unique ID.
     *
     * @param id the unique ID of the student to remove.
     * @return true if a student was removed; false otherwise.
     */
    public boolean removeStudent(int id) {
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            System.out.println("Student removed: " + student);
            return true;
        } else {
            System.out.println("Student with ID " + id + " not found.");
            return false;
        }
    }

    /**
     * Returns a list of all students.
     *
     * @return a List containing all registered students.
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * Displays all students in a simple format.
     */
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Registered Students:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
}
