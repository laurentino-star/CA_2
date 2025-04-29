package techcompany; // Package name

// Abstract class Manager (cannot be directly instantiated)
public abstract class Manager {
    protected String title; // Title of the manager (e.g., Head Manager, Assistant Manager)

    // Constructor to set the title when a Manager object is created
    public Manager(String title) {
        this.title = title;
    }

    // Getter method to retrieve the title of the manager
    public String getTitle() {
        return title;
    }
}
