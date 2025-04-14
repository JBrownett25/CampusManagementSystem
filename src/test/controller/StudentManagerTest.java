package test.controller;

import controller.StudentManager;
import model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentManagerTest {

    private StudentManager studentManager;

    @BeforeEach
    void setUp() {
        studentManager = new StudentManager();
    }

    @Test
    void testAddStudent() {
        Student student = new Student(1, "Alice", "Computer Science");
        studentManager.addStudent(student);

        // Check that the student can be retrieved
        Student retrieved = studentManager.searchStudentById(1);
        assertNotNull(retrieved, "Student should be added and retrievable");
        assertEquals("Alice", retrieved.getName(), "Student name should match");
    }

    @Test
    void testAddDuplicateStudent() {
        Student student1 = new Student(2, "Bob", "Mathematics");
        Student student2 = new Student(2, "Bob Updated", "Mathematics");
        studentManager.addStudent(student1);
        studentManager.addStudent(student2);

        // Since duplicate should not be added, name should remain as student1's name.
        Student retrieved = studentManager.searchStudentById(2);
        assertEquals("Bob", retrieved.getName(), "Duplicate student should not overwrite existing student");
    }

    @Test
    void testUpdateStudent() {
        Student student = new Student(3, "Charlie", "History");
        studentManager.addStudent(student);

        boolean updated = studentManager.updateStudent(3, "Charles", "History");
        assertTrue(updated, "Update should return true for existing student");
        Student updatedStudent = studentManager.searchStudentById(3);
        assertEquals("Charles", updatedStudent.getName(), "Student name should be updated");
    }

    @Test
    void testRemoveStudent() {
        Student student = new Student(4, "Diana", "Biology");
        studentManager.addStudent(student);
        boolean removed = studentManager.removeStudent(4);
        assertTrue(removed, "Student removal should return true for existing student");
        assertNull(studentManager.searchStudentById(4), "Removed student should no longer be found");
    }
}
