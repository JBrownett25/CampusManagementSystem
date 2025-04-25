import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main GUI"); //title
        frame.setSize(500, 500); //frame size
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

        JPanel panel = new JPanel();

        JButton button = new JButton("Rooms");
        button.addActionListener((e) -> { //when button is clicked.
            RoomGUI Gui = new RoomGUI();
            Gui.runGUI();

        });
        panel.add(button);
        frame.add(panel);
    } }
