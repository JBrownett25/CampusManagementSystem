import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String CSV_FILE_PATH = "Students.csv";
    
    // Save student to CSV
    public boolean saveStudent(Student student) {
        try (FileWriter fw = new FileWriter(CSV_FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            out.println(student.toString());
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Read all students from CSV
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            // Skip header if exists
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    if (line.startsWith("Name,") || line.startsWith("name,")) {
                        continue;
                    }
                }
                
                String[] data = line.split(",");
                if (data.length >= 4) {
                    students.add(new Student(data[0], data[2], data[1], data[3]));
                }
            }
            
        } catch (IOException e) {
            // If file doesn't exist yet, just return empty list
            if (!(e instanceof FileNotFoundException)) {
                e.printStackTrace();
            }
        }
        
        return students;
    }
    
    // Find student by name
    public Student findStudentByName(String name) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].equalsIgnoreCase(name)) {
                    return new Student(data[0], data[2], data[1], data[3]);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Remove student by name
    public boolean removeStudent(String name) {
        List<Student> students = getAllStudents();
        boolean found = false;
        
        try (FileWriter fw = new FileWriter(CSV_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            // Write header
            out.println("Name,Email,Course,Grade");
            
            // Write all students except the one to remove
            for (Student student : students) {
                if (!student.getName().equalsIgnoreCase(name)) {
                    out.println(student.toString());
                } else {
                    found = true;
                }
            }
            
            return found;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update existing student
    public boolean updateStudent(String originalName, Student updatedStudent) {
        List<Student> students = getAllStudents();
        boolean found = false;
        
        try (FileWriter fw = new FileWriter(CSV_FILE_PATH);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            // Write header
            out.println("Name,Email,Course,Grade");
            
            // Write all students, updating the matching one
            for (Student student : students) {
                if (student.getName().equalsIgnoreCase(originalName)) {
                    out.println(updatedStudent.toString());
                    found = true;
                } else {
                    out.println(student.toString());
                }
            }
            
            return found;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}