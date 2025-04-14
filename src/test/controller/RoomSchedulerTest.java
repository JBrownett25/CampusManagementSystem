package test.controller;

import controller.RoomScheduler;
import exception.RoomAlreadyBookedException;
import model.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomSchedulerTest {

    private RoomScheduler roomScheduler;

    @BeforeEach
    void setUp() {
        roomScheduler = new RoomScheduler();
    }

    @Test
    void testBookRoomSuccessfully() {
        Room room = new Room("A101", 50);
        try {
            roomScheduler.bookRoom("A101", room);
            assertTrue(roomScheduler.isRoomBooked("A101"), "Room A101 should be booked");
        } catch (RoomAlreadyBookedException e) {
            fail("Room booking should be successful");
        }
    }

    @Test
    void testBookRoomAlreadyBooked() {
        Room room1 = new Room("B202", 30);
        Room room2 = new Room("B202", 40);
        try {
            roomScheduler.bookRoom("B202", room1);
            // Trying to book the same room again should throw an exception.
            assertThrows(RoomAlreadyBookedException.class, () -> {
                roomScheduler.bookRoom("B202", room2);
            }, "Booking an already booked room should throw an exception");
        } catch (RoomAlreadyBookedException e) {
            fail("Initial room booking failed unexpectedly");
        }
    }

    @Test
    void testCancelBooking() {
        Room room = new Room("C303", 25);
        try {
            roomScheduler.bookRoom("C303", room);
            assertTrue(roomScheduler.isRoomBooked("C303"), "Room C303 should be booked");
            roomScheduler.cancelBooking("C303");
            assertFalse(roomScheduler.isRoomBooked("C303"), "Room C303 booking should be cancelled");
        } catch (RoomAlreadyBookedException e) {
            fail("Room booking should be successful before cancellation");
        }
    }
}
