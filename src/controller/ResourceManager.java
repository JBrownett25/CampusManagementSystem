package controller;

import model.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * The ResourceManager class handles management of campus resources such as library books or lab equipment.
 * It provides methods for adding, updating, retrieving, and removing resources using a HashMap for storage.
 */
public class ResourceManager {

    // Map to store resources with their resource ID as the key
    private Map<String, Resource> resources;

    /**
     * Constructs a new ResourceManager and initialises the resource storage.
     */
    public ResourceManager() {
        resources = new HashMap<>();
    }

    /**
     * Adds a new resource to the manager.
     *
     * @param resource the Resource object to add.
     */
    public void addResource(Resource resource) {
        String resourceId = resource.getResourceId();
        if (resources.containsKey(resourceId)) {
            System.out.println("Resource with ID " + resourceId + " already exists.");
        } else {
            resources.put(resourceId, resource);
            System.out.println("Resource added: " + resource);
        }
    }

    /**
     * Updates an existing resource's details.
     *
     * @param resourceId the ID of the resource to update.
     * @param name the new name or description for the resource.
     * @param quantity the new quantity available for the resource.
     */
    public void updateResource(String resourceId, String name, int quantity) {
        if (!resources.containsKey(resourceId)) {
            System.out.println("Resource with ID " + resourceId + " does not exist.");
        } else {
            Resource resource = resources.get(resourceId);
            resource.setName(name);
            resource.setQuantity(quantity);
            System.out.println("Resource updated: " + resource);
        }
    }

    /**
     * Removes a resource from the manager.
     *
     * @param resourceId the ID of the resource to remove.
     */
    public void removeResource(String resourceId) {
        if (!resources.containsKey(resourceId)) {
            System.out.println("Resource with ID " + resourceId + " does not exist.");
        } else {
            Resource removed = resources.remove(resourceId);
            System.out.println("Resource removed: " + removed);
        }
    }

    /**
     * Retrieves a resource by its ID.
     *
     * @param resourceId the ID of the resource.
     * @return the Resource object if found, or null if not found.
     */
    public Resource getResourceById(String resourceId) {
        return resources.get(resourceId);
    }

    /**
     * Displays all resources currently managed.
     */
    public void displayResources() {
        if (resources.isEmpty()) {
            System.out.println("No resources available.");
        } else {
            System.out.println("Resources:");
            for (Resource resource : resources.values()) {
                System.out.println(resource);
            }
        }
    }
}
