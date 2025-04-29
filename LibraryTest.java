import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    
    private Library library;
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws Exception {
        library = new Library();
        
        // Create a test CSV file
        Path csvPath = tempDir.resolve("Library.csv");
        List<String> lines = List.of(
            "Title,Author,Course,Copies",
            "Hacking101,John Smith,1701,1",
            "Programming,Fredrick Jones,1703,2"
        );
        Files.write(csvPath, lines);
        

    }
    
    @Test
    void testFindBook_ExistingBook() {

        Library.Book book = library.findBook("Hacking101");
        
        assertNotNull(book);
        assertEquals("Hacking101", book.getTitle());
        assertEquals("John Smith", book.getAuthor());
        assertEquals("1701", book.getCourse());
        assertEquals("1", book.getCopies());
    }
    
    @Test
    void testFindBook_NonExistingBook() {
        Library.Book book = library.findBook("NonExistentBook");
        assertNull(book);
    }
}