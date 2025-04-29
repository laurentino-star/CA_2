package techcompany; // Package name

// Class to represent an Employee
public class Employee {
    private String name; // Employee's name
    private Manager manager; // Manager responsible for the employee
    private Department department; // Department where the employee works

    // Constructor to create an Employee object with a name, manager, and department
    public Employee(String name, Manager manager, Department department) {
        this.name = name;
        this.manager = manager;
        this.department = department;
    }

    // Getter method to retrieve the employee's name
    public String getName() {
        return name;
    }

    // Getter method to retrieve the employee's manager
    public Manager getManager() {
        return manager;
    }

    // Getter method to retrieve the employee's department
    public Department getDepartment() {
        return department;
    }

    // Overrides the default toString() method to return employee details as a string
    @Override
    public String toString() {
        return name + " - " + manager.getTitle() + " - " + department.getName();
    }
}
