package controller;

import exception.RoomAlreadyBookedException;
import model.Room;

import java.util.HashMap;
import java.util.Map;

/**
 * The RoomScheduler class manages the booking of rooms for lectures and labs.
 * It ensures that a room cannot be booked twice at the same time by throwing a custom exception.
 */
public class RoomScheduler {

    // A map to store current bookings. Key: room number, Value: Room instance.
    private Map<String, Room> bookings;

    /**
     * Constructs a new RoomScheduler and initialises the booking storage.
     */
    public RoomScheduler() {
        bookings = new HashMap<>();
    }

    /**
     * Books a room if it is not already booked.
     *
     * @param roomNumber the unique room number to book.
     * @param room the Room object representing the room details.
     * @throws RoomAlreadyBookedException if the room is already booked.
     */
    public void bookRoom(String roomNumber, Room room) throws RoomAlreadyBookedException {
        if (bookings.containsKey(roomNumber)) {
            throw new RoomAlreadyBookedException("Room " + roomNumber + " is already booked.");
        }
        bookings.put(roomNumber, room);
        System.out.println("Room " + roomNumber + " has been successfully booked.");
    }

    /**
     * Cancels the booking for a given room.
     *
     * @param roomNumber the unique room number whose booking is to be cancelled.
     */
    public void cancelBooking(String roomNumber) {
        if (!bookings.containsKey(roomNumber)) {
            System.out.println("No existing booking found for room: " + roomNumber);
            return;
        }
        bookings.remove(roomNumber);
        System.out.println("Booking for room " + roomNumber + " has been cancelled.");
    }

    /**
     * Checks if a room is already booked.
     *
     * @param roomNumber the room number to check.
     * @return true if the room is booked, false otherwise.
     */
    public boolean isRoomBooked(String roomNumber) {
        return bookings.containsKey(roomNumber);
    }

    /**
     * Displays all current room bookings.
     */
    public void displayBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No rooms are currently booked.");
        } else {
            System.out.println("Current room bookings:");
            for (Map.Entry<String, Room> entry : bookings.entrySet()) {
                System.out.println("Room: " + entry.getKey() + " | Details: " + entry.getValue());
            }
        }
    }
}
