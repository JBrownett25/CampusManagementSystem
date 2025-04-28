
import javax.swing.*;

class RoomGUI extends JFrame {

    void runGUI() {
      setTitle("Room GUI");
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(500, 500);

      JPanel mainPanel = new JPanel();

      JButton bookButton = new JButton("Book Room");
      bookButton.addActionListener(e-> {
          BookRoomGUI bookGUI = new BookRoomGUI();
          bookGUI.BookRoomGUI();
      });

      JButton viewButton = new JButton("View Rooms");
      viewButton.addActionListener(e-> {
          RoomManagement roomManagement = new RoomManagement();
          roomManagement.getRooms();

      });


      mainPanel.add(bookButton); //add buttons to panel
      mainPanel.add(viewButton);
      add(mainPanel);//add to JFrame
      setVisible(true);


    }}
