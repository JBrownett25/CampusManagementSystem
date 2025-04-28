import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RoomManagementTest {

    private RoomManagement roomManagement;
    @TempDir
    Path tempDir;
    
    @BeforeEach
    void setUp() throws Exception {
        roomManagement = new RoomManagement();
        
        // Create a test CSV file
        Path csvPath = tempDir.resolve("Rooms.csv");
        List<String> lines = List.of(
            "Name,Capacity,Type",
            "F01,15,Lab",
            "F02,30,Lab",
            "S01,30,Seminar"
        );
        Files.write(csvPath, lines);
        
        // We would need a way to tell RoomManagement to use this test file
        // This might require changing your code to allow injection of the file path
    }
    
    @Test
    void testRoomListFormatting() {
        // Test just the string formatting logic
        ArrayList<String> testRooms = new ArrayList<>();
        testRooms.add("F01\t15\tLab");
        testRooms.add("F02\t30\tLab");
        
        // This assumes you've extracted the formatting logic to a separate method
        String formatted = roomManagement.formatRoomsList(testRooms);
        
        assertEquals("F01\t15\tLab\nF02\t30\tLab\n", formatted);
    }
}