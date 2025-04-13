package model;

/**
 * The Room class represents a classroom or lab in the Smart Campus Management System.
 * It stores basic details like the room number and capacity.
 */
public class Room {

    // Private fields encapsulating room details
    private String roomNumber;
    private int capacity;

    /**
     * Constructs a new Room with the specified room number and capacity.
     *
     * @param roomNumber the unique identifier or number of the room
     * @param capacity the maximum number of people that can be accommodated in the room
     */
    public Room(String roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
    }

    // Getter and setter methods for roomNumber

    /**
     * Returns the room number.
     *
     * @return the room number as a String
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     * Sets a new room number.
     *
     * @param roomNumber the new room number to set
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Getter and setter methods for capacity

    /**
     * Returns the capacity of the room.
     *
     * @return the room's capacity as an int
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the room.
     *
     * @param capacity the new capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Returns a string representation of the Room.
     *
     * @return a formatted string with the room number and capacity
     */
    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", capacity=" + capacity + "]";
    }
}
