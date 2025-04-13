import javax.swing.*;

public class main {
    public static void main(String[] args) {
// Temporary main until final GUI is made.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setTitle("Library GUI");

        JPanel panel = new JPanel();
        frame.add(panel);

        JButton search = new JButton ("Search");
        search.addActionListener(e -> {
            Library lib = new Library();// This object will be used when linked with the main system GUI.
            lib.runGUI();
        });
        panel.add(search);

        frame.setVisible(true);

    }
}
