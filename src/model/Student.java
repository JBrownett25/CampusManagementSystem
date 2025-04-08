package model;

/**
 * The Student class represents a student in the Smart Campus Management System.
 * It stores basic information such as the student's ID, name, and course of study.
 */
public class Student {

    // Private fields encapsulating the student's properties
    private int id;
    private String name;
    private String course;

    /**
     * Constructs a new Student with the specified id, name, and course.
     *
     * @param id the unique identifier for the student
     * @param name the full name of the student
     * @param course the course that the student is enrolled in
     */
    public Student(int id, String name, String course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    // Getter and setter methods for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for course
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * Returns a string representation of the Student.
     *
     * @return a formatted string containing the student details
     */
    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                '}';
    }
}
