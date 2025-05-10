package CA_2; // Defines the package this class belongs to

public class Department { // Declares the Department class
    private String name; // Private field to store the name of the department

    // Constructor to initialize the Department object with a name
    public Department(String name) {
        this.name = name; // Assigns the provided name to the name field
    }

    // Getter method to retrieve the name of the department
    public String getName() {
        return name; // Returns the name of the department
    }
}
