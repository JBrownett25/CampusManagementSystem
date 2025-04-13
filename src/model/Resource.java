package model;

/**
 * The Resource class represents a campus resource, such as a library book or lab equipment,
 * in the Smart Campus Management System.
 */
public class Resource {

    // Private fields for resource details
    private String resourceId;  // Unique identifier for the resource
    private String name;        // Name or description of the resource
    private int quantity;       // Number of items available

    /**
     * Constructs a new Resource with the specified resource ID, name, and quantity.
     *
     * @param resourceId the unique identifier for the resource
     * @param name the name or description of the resource
     * @param quantity the available quantity of the resource
     */
    public Resource(String resourceId, String name, int quantity) {
        this.resourceId = resourceId;
        this.name = name;
        this.quantity = quantity;
    }

    // Getter and setter methods for resourceId

    /**
     * Returns the unique identifier of the resource.
     *
     * @return the resource ID as a String
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets the unique identifier of the resource.
     *
     * @param resourceId the new resource ID to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    // Getter and setter methods for name

    /**
     * Returns the name or description of the resource.
     *
     * @return the resource name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name or description of the resource.
     *
     * @param name the new name for the resource
     */
    public void setName(String name) {
        this.name = name;
    }

    // Getter and setter methods for quantity

    /**
     * Returns the available quantity of the resource.
     *
     * @return the resource quantity as an int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the available quantity for the resource.
     *
     * @param quantity the new quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Resource.
     *
     * @return a formatted string with resource details
     */
    @Override
    public String toString() {
        return "Resource {" +
                "resourceId='" + resourceId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
