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
        

    }
    
    @Test
    void testRoomListFormatting() {
        // Test just the string formatting logic
        ArrayList<String> testRooms = new ArrayList<>();
        testRooms.add("F01\t15\tLab");
        testRooms.add("F02\t30\tLab");

        String formatted = roomManagement.getRooms(testRooms);
        
        assertEquals("F01\t15\tLab\nF02\t30\tLab\n", formatted);
    }
}