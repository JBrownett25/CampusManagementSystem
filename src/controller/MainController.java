package controller;

import exception.RoomAlreadyBookedException;
import model.Room;
import model.Resource;
import model.Student;

/**
 * The MainController class serves as the central point for managing interactions
 * between the modules of the Smart Campus Management System:
 * Student Management, Room Scheduling, and Resource Management.
 */
public class MainController {

    // Instance variables for each module's manager
    private StudentManager studentManager;
    private RoomScheduler roomScheduler;
    private ResourceManager resourceManager;

    /**
     * Constructs a new MainController and initialises all module managers.
     */
    public MainController() {
        studentManager = new StudentManager();
        roomScheduler = new RoomScheduler();
        resourceManager = new ResourceManager();
    }

    // ============================================================
    // Student Management Methods
    // ============================================================

    /**
     * Adds a new student with the specified details.
     *
     * @param id     the unique student ID
     * @param name   the student's full name
     * @param course the course in which the student is enrolled
     */
    public void addStudent(int id, String name, String course) {
        Student student = new Student(id, name, course);
        studentManager.addStudent(student);
    }

    /**
     * Updates an existing student's details.
     *
     * @param id     the unique student ID
     * @param name   the updated name of the student
     * @param course the updated course for the student
     * @return true if the student was successfully updated; false otherwise
     */
    public boolean updateStudent(int id, String name, String course) {
        return studentManager.updateStudent(id, name, course);
    }

    /**
     * Searches for a student by their unique ID.
     *
     * @param id the student ID
     * @return the Student object if found; otherwise, null
     */
    public Student searchStudentById(int id) {
        return studentManager.searchStudentById(id);
    }

    /**
     * Removes the student with the specified ID.
     *
     * @param id the student ID to remove
     * @return true if a student was removed; false otherwise
     */
    public boolean removeStudent(int id) {
        return studentManager.removeStudent(id);
    }

    // ============================================================
    // Room Scheduling Methods
    // ============================================================

    /**
     * Books a room if it is available.
     *
     * @param roomNumber the identifier for the room to book
     * @param room       the Room object containing the room details
     * @throws RoomAlreadyBookedException if the room is already booked
     */
    public void bookRoom(String roomNumber, Room room) throws RoomAlreadyBookedException {
        roomScheduler.bookRoom(roomNumber, room);
    }

    /**
     * Cancels a booking for the specified room number.
     *
     * @param roomNumber the identifier for the room booking to cancel
     */
    public void cancelRoomBooking(String roomNumber) {
        roomScheduler.cancelBooking(roomNumber);
    }

    /**
     * Checks whether a specific room is already booked.
     *
     * @param roomNumber the room number to check
     * @return true if the room is booked; false otherwise
     */
    public boolean isRoomBooked(String roomNumber) {
        return roomScheduler.isRoomBooked(roomNumber);
    }

    // ============================================================
    // Resource Management Methods
    // ============================================================

    /**
     * Adds a new resource with the specified details.
     *
     * @param resourceId the unique resource identifier
     * @param name       the resource name or description
     * @param quantity   the available quantity of the resource
     */
    public void addResource(String resourceId, String name, int quantity) {
        Resource resource = new Resource(resourceId, name, quantity);
        resourceManager.addResource(resource);
    }

    /**
     * Updates an existing resource with new details.
     *
     * @param resourceId the unique identifier for the resource
     * @param name       the new resource name or description
     * @param quantity   the new available quantity of the resource
     */
    public void updateResource(String resourceId, String name, int quantity) {
        resourceManager.updateResource(resourceId, name, quantity);
    }

    /**
     * Removes the resource with the specified identifier.
     *
     * @param resourceId the resource ID to remove
     */
    public void removeResource(String resourceId) {
        resourceManager.removeResource(resourceId);
    }

    /**
     * Searches for a resource by its unique identifier.
     *
     * @param resourceId the resource ID
     * @return the Resource object if found; otherwise, null
     */
    public Resource searchResource(String resourceId) {
        return resourceManager.getResourceById(resourceId);
    }
}
