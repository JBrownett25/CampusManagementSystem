public class Student {
    private String name;
    private String course;
    private String email;
    private String grade;
    
    public Student(String name, String course, String email, String grade) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCourse() {
        return course;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getGrade() {
        return grade;
    }
    
    @Override
    public String toString() {
        return name + "," + email + "," + course + "," + grade;
    }
}