
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomManagement {

    void bookRoom(String search) {
        String filepath = "Rooms.csv";
        ArrayList<String> rooms = new ArrayList();

        try {
            String line;
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                while ((line = reader.readLine()) != null) {
                    String roomNames = line.split(",")[0];
                    rooms.add(roomNames);
                }System.out.println(rooms);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ArrayList<String> rooms = new ArrayList();

    void getRooms() {

        try (BufferedReader read = new BufferedReader(new FileReader("Rooms.csv"))) {//Read CSV
            String line;
            while ((line = read.readLine()) != null) {
                String roomDetails = line + "\n";
                String tabs = null;
                for (int i = 0; i < roomDetails.length(); i++) { //arraylist
                    tabs = roomDetails.replace(",", "\t"); //replace comma with tabs.
                }
                rooms.add(tabs);
            }
        } catch (IOException e) {// Catch error
            e.printStackTrace();
        }

        viewGUi(rooms);

    }
    void viewGUi(ArrayList<String> rooms){
        JFrame viewFrame = new JFrame("Room Management");
        viewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewFrame.setSize(500, 500);

        JPanel viewPanel = new JPanel();


        JTextArea roomDetails = new JTextArea();
        roomDetails.setFont(new Font("Arial", 1, 20)); //set font
        roomDetails.setEditable(false);
        roomDetails.setText(String.valueOf(rooms)); //add the arraylist
        roomDetails.append("\n");
        viewPanel.add(roomDetails);
        viewFrame.add(viewPanel);
        viewFrame.setVisible(true);
    }
}