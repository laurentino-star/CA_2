package CA_2; // Defines the package this class belongs to

public class Manager { // Declares the Manager class
    // Private fields to store the manager's details
    private String name;  // Name of the manager
    private String level; // Level or position of the manager

    // Constructor to initialize the Manager object with a name and level
    public Manager(String name, String level) {
        this.name = name;   // Assigns the name to the manager
        this.level = level; // Assigns the level to the manager
    }

    // Getter method to retrieve the name of the manager
    public String getName() {
        return name; // Returns the manager's name
    }

    // Getter method to retrieve the level of the manager
    public String getLevel() {
        return level; // Returns the manager's level
    }
}
