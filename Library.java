
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Library {


    public void runGUI() {
        LibraryGUI libGUI = new LibraryGUI();
        libGUI.libraryGUI(); //Method to run GUI when linked to the mainGUI
    }

    public void getBook(String find) {


        ArrayList<String> books = new ArrayList<>(); //Initialise ArrayList to add titles of books from CSV.

        try (BufferedReader reader = new BufferedReader(new FileReader("Library.csv"))) { //Read file
            String line; //Declare line
            while ((line = reader.readLine()) != null) { //While to iterate through books
                String titles = line.split(",")[0]; //Only first Index (book titles) because this is what user searches for.
                books.add(titles); // Add titles to ArrayList

            }
        } catch (IOException e) { //Error handling in catch
            throw new RuntimeException(e);
        }



        if (books.contains(find)) {

            try (BufferedReader reader = new BufferedReader(new FileReader("Library.csv"))) {
                String line;
               while((line = reader.readLine()) != null) {
                   if (line.contains(find)) {
                       System.out.println(line);
                       detailsGUI(find, line);
                   }
               }
            }catch (IOException e) {
                System.out.println("Error reading file");
                throw new RuntimeException(e);
            }

        }
    }
    public void detailsGUI(String find, String bookLine) {
        JFrame bookFrame; //Run a new frame
        bookFrame = new JFrame("Book Title: " + find);
        bookFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        bookFrame.setSize(600, 600);

        JPanel bookPanel = new JPanel();

        JTextArea BookDetails = new JTextArea();
        BookDetails.setFont(new Font("Arial", Font.PLAIN, 30));
        BookDetails.setEditable(false);
        BookDetails.setText(bookLine); //Add details of the user's book to the TextArea.
        bookPanel.add(BookDetails);

        bookFrame.add(bookPanel);
        bookFrame.setVisible(true);
    }
}


