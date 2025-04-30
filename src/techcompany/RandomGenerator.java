package techcompany; // Defines the package for this class

import java.util.*; // Imports utility classes like List, ArrayList, and Random

public class RandomGenerator {

    // Predefined names used for random employee generation
    private static final String[] names = {
        "Lucas", "Amelia", "Marcus", "Olivia", "Julian", "Sophia", "Sebastian", "Emily",
        "Leo", "Charlotte", "Nathan", "Isabella", "Gabriel", "Lily", "Xavier", "Mia"
    };

    // Random object to help with generating random values
    private static final Random random = new Random();

    // Method to generate a list of random employees
    public static List<Employee> generateEmployees(int count) {
        List<Employee> generated = new ArrayList<>(); // Stores generated employees

        for (int i = 0; i < count; i++) {
            // Choose a random name and add a number suffix for uniqueness
            String name = names[random.nextInt(names.length)] + "_" + (i + 1);

            // Randomly assign a manager type
            Manager manager;
            int m = random.nextInt(3);
            switch (m) {
                case 0:
                    manager = new HeadManager();
                    break;
                case 1:
                    manager = new AssistantManager();
                    break;
                default:
                    manager = new TeamLead();
                    break;
            }

            // Randomly assign a department
            Department dept;
            int d = random.nextInt(3);
            switch (d) {
                case 0:
                    dept = new TechnicalSupport();
                    break;
                case 1:
                    dept = new CustomerService();
                    break;
                default:
                    dept = new HR();
                    break;
            }

            // Create and add the new employee to the list
            generated.add(new Employee(name, manager, dept));
        }

        return generated; // Return the list of generated employees
    }
}
