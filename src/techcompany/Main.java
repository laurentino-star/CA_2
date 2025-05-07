package techcompany; // Package name

import java.io.*;
import java.util.*;

// Main class to run the program
public class Main {

    private static final Scanner scanner = new Scanner(System.in); // Scanner for user input
    private static List<Employee> employeeList = new ArrayList<>(); // List to store Employee objects

    // Enum for menu options
    public enum MenuOption {
        SORT, SEARCH, ADD_RECORDS, GENERATE_RANDOM, EXIT
    }

    // Main method to run the program
    public static void main(String[] args) {
        String filename = "Applicants_Form.txt"; // Path to the input file
        System.out.println("Attempting to read file: " + filename);

        // Load data from file into the employee list
        if (loadFromFile(filename)) {
            System.out.println("✅ File read successfully.\n");
        } else {
            System.out.println("❌ Failed to read file. Please ensure the file exists and is in the correct format.\n");
        }

        boolean running = true;

        // Main program loop
        while (running) {
            System.out.println("Please select an option:");
            for (int i = 0; i < MenuOption.values().length; i++) {
                System.out.printf("%d. %s%n", i + 1, MenuOption.values()[i]);
            }

            int choice = 0;
            while (true) {
                System.out.print("Enter your choice (1-" + MenuOption.values().length + "): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt(); // Read choice
                    scanner.nextLine(); // Clear buffer
                    if (choice >= 1 && choice <= MenuOption.values().length) {
                        break;
                    } else {
                        System.out.println("Invalid number. Please choose between 1 and " + MenuOption.values().length);
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
            }

            // Perform action based on user choice
            switch (MenuOption.values()[choice - 1]) {
                case SORT:
                    handleSort(); // Sort employee list
                    break;
                case SEARCH:
                    handleSearch(); // Search employee by name
                    break;
                case ADD_RECORDS:
                    handleAdd(); // Add new employee record
                    break;
                case GENERATE_RANDOM:
                    handleGenerate(); // Generate random employees
                    break;
                case EXIT:
                    running = false; // Exit the program
                    break;
            }
        }

        System.out.println("Program ended."); // End of program message
    }

    // Method to load employee data from a file
    private static boolean loadFromFile(String filename) {
        File file = new File(filename); // Create a File object for the given filename
        if (!file.exists()) { // Check if the file exists
            System.out.println("⚠️ Error: " + filename + " (The system cannot find the file specified)");
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Open file for reading
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into parts: name, age, salary, address
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    System.out.println("⚠️ Invalid format: " + line);
                    continue; // Skip lines with invalid format
                }

                String name = parts[0].trim();
                int age;
                double salary;
                String address = parts[3].trim();

                // Attempt to parse the age and salary values
                try {
                    age = Integer.parseInt(parts[1].trim());
                    salary = Double.parseDouble(parts[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Error with age or salary on line: " + line);
                    continue; // Skip lines with invalid number formats
                }

                // Use helper methods to choose manager and department
                Manager manager = InputHandler.chooseManager();
                Department department = InputHandler.chooseDepartment();

                // Create a new Employee and add it to the list
                Employee emp = new Employee(name, manager, department);
                emp.setAge(age); // Set age
                emp.setSalary(salary); // Set salary
                emp.setAddress(address); // Set address
                employeeList.add(emp); // Add employee to the list
            }

            return true; // Successfully loaded file

        } catch (IOException e) { // Handle any file reading errors
            System.out.println("❌ Error reading file: " + e.getMessage());
            return false;
        }
    }

    // Method to sort employees by name and display the first 20
    private static void handleSort() {
        if (employeeList.isEmpty()) {
            System.out.println("No data to sort.");
            return;
        }

        // Sort the employee list
        employeeList = Sorter.mergeSort(employeeList);
        System.out.println("\nTop 20 sorted names:");
        for (int i = 0; i < Math.min(20, employeeList.size()); i++) {
            // Print the sorted employee names
            System.out.println(employeeList.get(i).getName());
        }
    }

    // Method to search for an employee by name
    private static void handleSearch() {
        if (employeeList.isEmpty()) {
            System.out.println("List is empty, nothing to search.");
            return;
        }

        // Sort the employee list before searching
        employeeList = Sorter.mergeSort(employeeList);
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine(); // Get name to search

        // Use binary search to find the employee
        int index = Searcher.binarySearch(employeeList, name);

        if (index >= 0) {
            Employee found = employeeList.get(index);
            System.out.printf("Found: %s | Manager: %s | Department: %s%n",
                    found.getName(), found.getManager().getTitle(), found.getDepartment().getName());
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Method to add a new employee record
    private static void handleAdd() {
        String name = InputHandler.getEmployeeName(); // Get employee name
        Manager manager = InputHandler.chooseManager(); // Get manager choice
        Department department = InputHandler.chooseDepartment(); // Get department choice

        // Create a new employee and add to the list
        employeeList.add(new Employee(name, manager, department));
        System.out.printf("\"%s\" added as \"%s\" in \"%s\" department.%n",
                name, manager.getTitle(), department.getName());
    }

    // Method to generate random employees
    private static void handleGenerate() {
        System.out.print("How many random employees to generate? ");
        int count = scanner.nextInt(); // Get number of random employees
        scanner.nextLine(); // Clear buffer

        // Generate random employees
        List<Employee> generated = RandomGenerator.generateEmployees(count);
        employeeList.addAll(generated); // Add them to the employee list

        // Display generated employees
        System.out.println("Generated employees:");
        for (Employee e : generated) {
            // Print the full employee details using the toString method
            System.out.printf("%s%n", e.toString());
        }
    }
}
