// Package declaration for the application
package CA_2;

// Import required Java libraries
import java.io.*;
import java.util.*;

// Main application class
public class CA_2 {
    // Static Scanner for user input
    static Scanner scanner = new Scanner(System.in);
    // List to store employee records
    static List<Employee> employees = new ArrayList<>();
    // Variable to store the filename
    static String filename;

    // Main method - program entry point
    public static void main(String[] args) {
        // 1. File loading loop
        while (true) {
            System.out.print("Enter filename to load (e.g., Applicants_Form.txt): ");
            filename = scanner.nextLine().trim();

            File file = new File(filename);
            // Check if file exists or can be created
            if (file.exists() || createFile(filename)) {
                readFile(filename);  // Load employee data
                break;  // Exit loop when successful
            } else {
                System.out.println("Invalid file or cannot be created. Try again.");
            }
        }

        // 2. Main program loop
        while (true) {
            // Display menu options
            System.out.println("\nChoose an option:");
            for (int i = 0; i < MenuOption.values().length; i++) {
                System.out.println((i + 1) + ". " + MenuOption.values()[i]);
            }

            try {
                // Process user selection
                int choice = Integer.parseInt(scanner.nextLine());
                MenuOption option = MenuOption.values()[choice - 1];

                // Execute selected option
                switch (option) {
                    case SORT -> sortEmployees();         // Sort employee records
                    case SEARCH -> searchEmployee();       // Search for employee
                    case ADD_RECORDS -> showAddRecordMenu(); // Show add record menu
                    case EXIT -> {                         // Exit program
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Method to create a new file
    static boolean createFile(String filename) {
        try {
            File file = new File(filename);
            return file.createNewFile();  // Returns true if file created successfully
        } catch (IOException e) {
            return false;  // Returns false if creation fails
        }
    }

    // Method to read employee data from file
    static void readFile(String filename) {
        employees.clear();  // Clear existing employee list
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            // Read file line by line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Ensure line has all required fields
                if (parts.length == 5) {
                    // Create new Employee object from file data
                    Employee e = new Employee(
                            parts[0].trim(),
                            new Department(parts[1].trim()),
                            new Manager("", parts[2].trim()),
                            parts[3].trim(),
                            parts[4].trim()
                    );
                    employees.add(e);  // Add to employee list
                }
            }
            System.out.println("File loaded with " + employees.size() + " employees.");
        } catch (IOException e) {
            System.out.println("Failed to read file.");
        }
    }

    // Method to display add record menu
    static void showAddRecordMenu() {
        System.out.println("\nAdd Record Menu:");
        // Display add record options
        for (int i = 0; i < AddRecordOption.values().length; i++) {
            System.out.println((i + 1) + ". " + AddRecordOption.values()[i]);
        }

        try {
            // Process user selection
            int option = Integer.parseInt(scanner.nextLine());
            AddRecordOption selected = AddRecordOption.values()[option - 1];

            // Execute selected option
            switch (selected) {
                case ADD_EMPLOYEE -> addEmployeeManually();          // Manual entry
                case GENERATE_RANDOM_EMPLOYEE -> generateRandomEmployee();  // Auto-generate
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Returning to main menu.");
        }
    }

    // Method to manually add an employee
    static void addEmployeeManually() {
        // Collect employee details from user
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Department: ");
        String dept = scanner.nextLine().trim();

        System.out.print("Manager Level: ");
        String level = scanner.nextLine().trim();

        System.out.print("Job Title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Company: ");
        String company = scanner.nextLine().trim();

        // Create and add new employee
        Employee e = new Employee(name, new Department(dept), new Manager("", level), title, company);
        employees.add(e);
        saveToFile(e);  // Save to file
        System.out.println("Employee added.");
    }

    // Method to generate random employee
    static void generateRandomEmployee() {
        // Arrays of possible values for random generation
        String[] names = {"John Doe", "Jane Smith", "Alice Johnson", "Bob Lee", "Maria Clark"};
        String[] departments = {"IT", "HR", "Marketing", "Finance", "Sales"};
        String[] levels = {"Junior", "Mid", "Senior", "Lead"};
        String[] titles = {"Software Engineer", "Project Manager", "Analyst", "HR Specialist"};
        String[] companies = {"TechCorp", "BizSoft", "InnovateX", "DevSolutions"};

        // Select random values
        String name = getRandom(names);
        String dept = getRandom(departments);
        String level = getRandom(levels);
        String title = getRandom(titles);
        String company = getRandom(companies);

        // Create and add random employee
        Employee e = new Employee(name, new Department(dept), new Manager("", level), title, company);
        employees.add(e);
        saveToFile(e);  // Save to file

        // Display generated employee
        System.out.println("\nRandom Employee Generated:");
        System.out.printf("Name: %s\nDepartment: %s\nManager Level: %s\nJob Title: %s\nCompany: %s\n",
                name, dept, level, title, company);
    }

    // Helper method to get random element from array
    static String getRandom(String[] array) {
        return array[new Random().nextInt(array.length)];
    }

    // Method to save employee to file
    static void saveToFile(Employee e) {
        try (FileWriter fw = new FileWriter(filename, true)) {  // Append mode
            // Write employee data in CSV format
            fw.write(String.format("%s, %s, %s, %s, %s%n",
                    e.getName(),
                    e.getDepartment().getName(),
                    e.getManager().getLevel(),
                    e.getJobTitle(),
                    e.getCompany()));
        } catch (IOException ex) {
            System.out.println("Error writing to file.");
        }
    }

    // Method to sort employees
    static void sortEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to sort.");
            return;
        }

        // Display sorting options
        System.out.println("Sort by:\n1. Name\n2. Department\n3. Manager\n4. Job Title\n5. Company");
        int field = Integer.parseInt(scanner.nextLine());
        // Perform merge sort
        List<Employee> sorted = mergeSort(new ArrayList<>(employees), field);

        // Display sorted results (first 20 records)
        System.out.printf("\n%-20s %-15s %-10s %-20s %-15s\n", "Name", "Department", "Level", "Job Title", "Company");
        System.out.println("-------------------------------------------------------------------------------");

        for (int i = 0; i < Math.min(20, sorted.size()); i++) {
            System.out.println(sorted.get(i));
        }
    }

    // Recursive merge sort implementation
    static List<Employee> mergeSort(List<Employee> list, int field) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        // Recursively sort left and right halves
        List<Employee> left = mergeSort(list.subList(0, mid), field);
        List<Employee> right = mergeSort(list.subList(mid, list.size()), field);
        return merge(left, right, field);  // Merge sorted halves
    }

    // Merge helper method for merge sort
    static List<Employee> merge(List<Employee> left, List<Employee> right, int field) {
        List<Employee> result = new ArrayList<>();
        int i = 0, j = 0;
        // Merge while both lists have elements
        while (i < left.size() && j < right.size()) {
            String lVal = getField(left.get(i), field);
            String rVal = getField(right.get(j), field);
            // Compare based on selected field
            if (lVal.compareToIgnoreCase(rVal) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        // Add remaining elements
        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));
        return result;
    }

    // Helper method to get field value for sorting
    static String getField(Employee e, int field) {
        return switch (field) {
            case 1 -> e.getName();
            case 2 -> e.getDepartment().getName();
            case 3 -> e.getManager().getLevel();
            case 4 -> e.getJobTitle();
            case 5 -> e.getCompany();
            default -> "";
        };
    }

    // Method to search for employee
    static void searchEmployee() {
        if (employees.isEmpty()) {
            System.out.println("No employees loaded.");
            return;
        }

        System.out.print("Enter employee name to search: ");
        String query = scanner.nextLine().trim().toLowerCase();

        // Sort employees by name for binary search
        List<Employee> sorted = mergeSort(new ArrayList<>(employees), 1);
        int index = binarySearch(sorted, query);

        // Display search results
        if (index >= 0) {
            Employee e = sorted.get(index);
            System.out.println("Employee found:\n" + e);
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Binary search implementation
    static int binarySearch(List<Employee> list, String name) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midName = list.get(mid).getName().toLowerCase();
            int cmp = midName.compareTo(name);
            if (cmp == 0) return mid;  // Found match
            else if (cmp < 0) low = mid + 1;  // Search right half
            else high = mid - 1;  // Search left half
        }
        return -1;  // Not found
    }
}
