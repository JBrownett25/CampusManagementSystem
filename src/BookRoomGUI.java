import javax.swing.*;
import java.awt.*;

public class BookRoomGUI extends JFrame{
    void BookRoomGUI (){
        int col = 10; //size of text field.
        setTitle("Room Management");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 800);

        JPanel roomPanel = new JPanel();
        roomPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel booklabel = new JLabel("Book Room");
        booklabel.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        roomPanel.add(booklabel);

        JLabel roomnamelabel = new JLabel("Room Name");
        roomnamelabel.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        roomPanel.add(roomnamelabel);

        JTextField roomname = new JTextField();
        roomname.setColumns(col);
        roomname.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        roomPanel.add(roomname);

        JLabel courselabel = new JLabel("Course Name");
        courselabel.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        roomPanel.add(courselabel);

        JTextField course = new JTextField();
        course.setColumns(col);
        course.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        roomPanel.add(course);

        JButton bookBtn = new JButton("Book");
        bookBtn.setFont(new Font("Arial", 1, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        bookBtn.addActionListener((e) -> {
            String search = roomname.getText(); //get text from inputs
            RoomManagement room = new RoomManagement();
            room.bookRoom(search);
        });
        roomPanel.add(bookBtn);
        add(roomPanel);
        setVisible(true);


    }}


