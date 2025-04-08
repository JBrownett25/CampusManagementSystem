import javax.swing.*;
import java.awt.*;


class LibraryGUI extends JFrame {

    public void libraryGUI() {
        setTitle("Library"); //title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close frame
        setSize(600, 600);
        setVisible(true); //Loads frame for user.

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); //Layout manager
        GridBagConstraints con = new GridBagConstraints(); //introduce the constraints object.

        JLabel header = new JLabel("Library Information"); // Label and text
        header.setFont(new Font("Arial", Font.PLAIN, 50)); // Set font style
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 3;
        con.gridheight = 1;
        panel.add(header, con); // Add to panel with contraints.

        JButton findBook = new JButton("Find Book");
        findBook.setFont(new Font("Arial", Font.PLAIN, 30));
        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        panel.add(findBook, con);

        JTextField bookText = new JTextField();
        bookText.setFont(new Font("Arial", Font.PLAIN, 30));
        con.gridx = 2;
        con.gridy = 1;
        con.gridwidth = 2;
        con.fill = GridBagConstraints.HORIZONTAL; // Cover whole 2 columns
        panel.add(bookText, con);

        add(panel); // Add panel to JFrame.
    }
}

