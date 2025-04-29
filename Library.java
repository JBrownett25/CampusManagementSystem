
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;


public class Library {

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
                while ((line = reader.readLine()) != null) {
                    if (line.contains(find)) {

                        String name = line.split(",")[0];
                        String author = line.split(",")[1];
                        String course = line.split(",")[2];
                        String copies = line.split(",")[3];

                        detailsGUI(find, name, author, course, copies);
                        break;


                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file");
                throw new RuntimeException(e);
            }

        }
    }

    JTextArea bookTitle, bookAuthor, bookCourse,bookCount;
    JLabel bookTitleLabel, bookAuthorLabel, bookCourseLabel, bookCountLabel;

    public void detailsGUI(String find, String name, String author, String course, String copies) {

        JFrame bookFrame;
        bookFrame = new JFrame("Book Title: " + find);//Run a new frame
        bookFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        bookFrame.setSize(600, 600);

        JPanel bookPanel = new JPanel();
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));

        bookTitleLabel = new JLabel("Book Title: ");
        bookTitleLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        bookPanel.add(bookTitleLabel);

        bookTitle = new JTextArea();
        bookTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        bookTitle.setEditable(false);
        bookTitle.setText(name); //Add details of the user's book to the TextArea.
        bookPanel.add(bookTitle);

        bookAuthorLabel = new JLabel("Book Author: ");
        bookAuthorLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        bookPanel.add(bookAuthorLabel);

        bookAuthor = new JTextArea();
        bookAuthor.setFont(new Font("Arial", Font.PLAIN, 30));
        bookAuthor.setEditable(false);
        bookAuthor.setText(author);
        bookPanel.add(bookAuthor);

        bookCourseLabel = new JLabel("Book Course: ");
        bookCourseLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        bookPanel.add(bookCourseLabel);

        bookCourse = new JTextArea();
        bookCourse.setFont(new Font("Arial", Font.PLAIN, 30));
        bookCourse.setEditable(false);
        bookCourse.setText(course);
        bookPanel.add(bookCourse);

        bookCountLabel = new JLabel("Book Count: ");
        bookCountLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        bookPanel.add(bookCountLabel);

        bookCount = new JTextArea();
        bookCount.setFont(new Font("Arial", Font.PLAIN, 30));
        bookCount.setEditable(false);
        bookCount.setText(copies);
        bookPanel.add(bookCount);

        JButton reserve = new JButton("reserve");
        reserve.setFont(new Font("Arial", Font.PLAIN, 30));
        reserve.addActionListener(e -> {

            String value = bookCount.getText();
            updateCount(value);

            JFrame reserveFrame = new JFrame("Reserve Book");
            reserveFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            reserveFrame.setSize(600, 600);

            JPanel reservePanel = new JPanel();

            JTextArea reserveText = new JTextArea();
            reserveText.setText(name + " Successfully reserved.\nPlease collect from the Library Help Desk.");
            reserveText.setFont(new Font("Arial", Font.PLAIN, 30));
            reserveText.setEditable(false);
            reservePanel.add(reserveText);


            JButton exit = new JButton("Confirm & Exit");
            exit.setFont(new Font("Arial", Font.PLAIN, 30));
            exit.addActionListener(event -> {

                reserveFrame.setVisible(false);
            });
            reservePanel.add(exit);
            reserveFrame.add(reservePanel);
            reserveFrame.setVisible(true);

        });

        bookPanel.add(reserve);
        bookFrame.add(bookPanel);
        bookFrame.setVisible(true);
    }


    String updateCount(String value) {
        int prevCount;
        String newCount;
        String file = "Library.csv";
        String temp = "temp.csv";

        prevCount = Integer.parseInt(value); //convert to int.

        try (BufferedReader reader = new BufferedReader(new FileReader(file));//read old file
             BufferedWriter wr = new BufferedWriter(new FileWriter(temp));) {//write to new file
            String line;
            while ((line = reader.readLine()) != null) {
                String[] count = line.split(",");
                for (int i = 0; i < count.length; i++) {
                    if (count[i].equals(String.valueOf(prevCount))) {
                        newCount = String.valueOf(prevCount - 1); //update count and make String
                        count[i] = newCount; //replace value with new updated value
                        System.out.println("New count: " + count[i] + ". Count Updated");//Check count updates and confirms to user.

                    }
                }
                wr.write(String.join(",", count)); //Join String bu commas for CSV.
                wr.newLine();
            }

            File originalFile = new File(file); //Declare objects for old and new file.
            File updated = new File(temp);

            if (originalFile.exists()) {
                originalFile.delete(); //Delete original
                updated.renameTo(new File("Library.csv")); //make temp Library.csv
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    }
