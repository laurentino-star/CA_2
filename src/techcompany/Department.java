package techcompany; // Defines the package where the class is located

// Abstract class representing a general Department
public abstract class Department {

    protected String name; // Protected variable to store the department's name

    // Constructor that sets the department's name
    public Department(String name) {
        this.name = name;
    }

    // Getter method to retrieve the department's name
    public String getName() {
        return name;
    }
}
