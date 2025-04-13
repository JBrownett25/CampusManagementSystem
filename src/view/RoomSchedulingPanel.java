package view;

import controller.MainController;
import exception.RoomAlreadyBookedException;
import model.Room;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomSchedulingPanel extends JPanel {

    private MainController controller;

    // Components for booking a room
    private JTextField roomNumberField, capacityField;
    private JButton bookRoomButton;

    // Components for cancelling a booking
    private JTextField cancelRoomField;
    private JButton cancelBookingButton;

    // Text area to display booking details
    private JTextArea bookingDisplayArea;

    public RoomSchedulingPanel(MainController controller) {
        this.controller = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        // Top panel for booking a room
        JPanel bookingPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        bookingPanel.setBorder(BorderFactory.createTitledBorder("Book a Room"));

        bookingPanel.add(new JLabel("Room Number:"));
        roomNumberField = new JTextField();
        bookingPanel.add(roomNumberField);

        bookingPanel.add(new JLabel("Capacity:"));
        capacityField = new JTextField();
        bookingPanel.add(capacityField);

        bookRoomButton = new JButton("Book Room");
        bookingPanel.add(bookRoomButton);
        bookingPanel.add(new JLabel(""));  // Filler cell

        // Panel for cancelling a booking
        JPanel cancelPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        cancelPanel.setBorder(BorderFactory.createTitledBorder("Cancel Booking"));

        cancelPanel.add(new JLabel("Room Number:"));
        cancelRoomField = new JTextField();
        cancelPanel.add(cancelRoomField);

        cancelBookingButton = new JButton("Cancel Booking");
        cancelPanel.add(cancelBookingButton);
        cancelPanel.add(new JLabel(""));  // Filler cell

        // Text area to display current bookings
        bookingDisplayArea = new JTextArea(8, 30);
        bookingDisplayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(bookingDisplayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Current Bookings"));

        // Arrange panels in the main panel
        setLayout(new BorderLayout(10, 10));
        add(bookingPanel, BorderLayout.NORTH);
        add(cancelPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Set up event listeners
        bookRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookRoom();
            }
        });

        cancelBookingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelBooking();
            }
        });
    }

    private void bookRoom() {
        String roomNumber = roomNumberField.getText().trim();
        String capacityText = capacityField.getText().trim();
        if (roomNumber.isEmpty() || capacityText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Room Number and Capacity are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int capacity = Integer.parseInt(capacityText);
            Room room = new Room(roomNumber, capacity);
            controller.bookRoom(roomNumber, room);
            JOptionPane.showMessageDialog(this, "Room booked successfully.");
            updateBookingDisplay();
            clearBookingFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Capacity must be a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (RoomAlreadyBookedException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Booking Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelBooking() {
        String roomNumber = cancelRoomField.getText().trim();
        if (roomNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Room Number to cancel.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.cancelRoomBooking(roomNumber);
        JOptionPane.showMessageDialog(this, "Booking cancelled (if it existed).");
        updateBookingDisplay();
    }

    private void updateBookingDisplay() {
        // For a more advanced implementation, you might modify RoomScheduler to return a list of bookings.
        // Here we update the area with a reminder to check the console, or you can hardcode a sample display.
        bookingDisplayArea.setText("Refresh booking list functionality not implemented.\nCheck console output for booking details.");
    }

    private void clearBookingFields() {
        roomNumberField.setText("");
        capacityField.setText("");
    }
}
