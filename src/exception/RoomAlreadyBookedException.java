package exception;

/**
 * Custom exception thrown when a room is attempted to be booked
 * but it is already booked.
 */
public class RoomAlreadyBookedException extends Exception {

    /**
     * Constructs a new RoomAlreadyBookedException with the specified detail message.
     *
     * @param message the detail message explaining the exception.
     */
    public RoomAlreadyBookedException(String message) {
        super(message);
    }
}
