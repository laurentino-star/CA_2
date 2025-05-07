package techcompany; // Defines the package for this class

import java.util.*; // Imports utility classes like List, ArrayList, and Random

public class RandomGenerator {

    // Predefined names used for random employee generation
    private static final String[] names = {
        "Lucas", "Amelia", "Marcus", "Olivia", "Julian", "Sophia", 
        "Sebastian", "Emily", "Leo", "Charlotte", "Nathan", "Isabella", 
        "Gabriel", "Lily", "Xavier", "Mia"
    };

    // Random object to help with generating random values
    private static final Random random = new Random();

    // Method to generate a list of random employees
    public static List<Employee> generateEmployees(int count) {
        List<Employee> generated = new ArrayList<>(); // List to store generated employees

        // Loop to create the specified number of employees
        for (int i = 0; i < count; i++) {
            // Select a random name from the list and append a unique number for distinction
            String name = names[random.nextInt(names.length)] + "_" + (i + 1);

            // Initialize the Manager variable
            Manager manager;
            
            // Randomly select a type of manager: HeadManager, AssistantManager, or TeamLead
            int m = random.nextInt(3);
            switch (m) {
                case 0 -> manager = new HeadManager();       // 0 -> HeadManager
                case 1 -> manager = new AssistantManager();  // 1 -> AssistantManager
                default -> manager = new TeamLead();         // 2 -> TeamLead
            }

            // Initialize the Department variable
            Department dept;

            // Randomly select a type of department: TechnicalSupport, CustomerService, or HR
            int d = random.nextInt(3);
            switch (d) {
                case 0 -> dept = new TechnicalSupport();     // 0 -> TechnicalSupport
                case 1 -> dept = new CustomerService();      // 1 -> CustomerService
                default -> dept = new HR();                  // 2 -> HR
            }

            // Create a new Employee with the generated name, manager, and department
            generated.add(new Employee(name, manager, dept));
        }

        // Return the list of generated employees
        return generated;
    }
}
