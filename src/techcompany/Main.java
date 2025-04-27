package techcompany;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Employee> employeeList = new ArrayList<>();

    public enum MenuOption {
        SORT, SEARCH, ADD_RECORDS, GENERATE_RANDOM, EXIT
    }

    public static void main(String[] args) {
        System.out.print("Please enter the filename to read: ");
        String filename = scanner.nextLine();

        if (loadFromFile(filename)) {
            System.out.println("File read successfully.\n");
        } else {
            System.out.println("Failed to read file.");
        }

        boolean running = true;

        while (running) {
            System.out.println("\nPlease select an option:");
            for (int i = 0; i < MenuOption.values().length; i++) {
                System.out.printf("%d. %s%n", i + 1, MenuOption.values()[i]);
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (MenuOption.values()[choice - 1]) {
                case SORT:
                    handleSort();
                    break;
                case SEARCH:
                    handleSearch();
                    break;
                case ADD_RECORDS:
                    handleAdd();
                    break;
                case GENERATE_RANDOM:
                    handleGenerate();
                    break;
                case EXIT:
                    running = false;
                    break;
            }
        }

        System.out.println("Program ended.");
    }

    private static boolean loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Manager manager = InputHandler.chooseManager();
                Department dept = InputHandler.chooseDepartment();
                employeeList.add(new Employee(line.trim(), manager, dept));
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private static void handleSort() {
        if (employeeList.isEmpty()) {
            System.out.println("No data to sort.");
            return;
        }

        employeeList = Sorter.mergeSort(employeeList);
        System.out.println("\nTop 20 sorted names:");
        for (int i = 0; i < Math.min(20, employeeList.size()); i++) {
            System.out.println(employeeList.get(i).getName());
        }
    }

    private static void handleSearch() {
        if (employeeList.isEmpty()) {
            System.out.println("List is empty, nothing to search.");
            return;
        }

        employeeList = Sorter.mergeSort(employeeList); // ensure sorted before search
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        int index = Searcher.binarySearch(employeeList, name);

        if (index >= 0) {
            Employee found = employeeList.get(index);
            System.out.printf("Found: %s | Manager: %s | Department: %s%n",
                    found.getName(), found.getManager().getTitle(), found.getDepartment().getName());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void handleAdd() {
        String name = InputHandler.getEmployeeName();
        Manager manager = InputHandler.chooseManager();
        Department department = InputHandler.chooseDepartment();
        employeeList.add(new Employee(name, manager, department));
        System.out.printf("\"%s\" added as \"%s\" in \"%s\" department.%n",
                name, manager.getTitle(), department.getName());
    }

    private static void handleGenerate() {
        System.out.print("How many random employees to generate? ");
        int count = scanner.nextInt();
        scanner.nextLine(); // consume newline

        List<Employee> generated = RandomGenerator.generateEmployees(count);
        employeeList.addAll(generated);

        System.out.println("Generated employees:");
        for (Employee e : generated) {
            System.out.printf("%s | %s | %s%n", e.getName(), e.getManager().getTitle(), e.getDepartment().getName());
        }
    }
}
