package techcompany; // Defines the package

// Class to represent an Employee
public class Employee {
    private String name;               // Employee's name
    private Manager manager;           // Manager assigned to the employee
    private Department department;     // Department the employee belongs to
    private int age;                   // Employee's age
    private double salary;            // Employee's salary
    private String address;            // Employee's address

    // Constructor for basic employee info
    public Employee(String name, Manager manager, Department department) {
        this.name = name;
        this.manager = manager;
        this.department = department;
    }

    // Setters for optional fields loaded from file
    public void setAge(int age) {
        this.age = age; // Assigns age
    }

    public void setSalary(double salary) {
        this.salary = salary; // Assigns salary
    }

    public void setAddress(String address) {
        this.address = address; // Assigns address
    }

    // Getters if needed later
    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    // Getter for employee name
    public String getName() {
        return name;
    }

    // Getter for employee manager
    public Manager getManager() {
        return manager;
    }

    // Getter for employee department
    public Department getDepartment() {
        return department;
    }

    // Overridden toString method to include all relevant employee info
    @Override
    public String toString() {
        return String.format("%s | Age: %d | Salary: €%.2f | Address: %s | Manager: %s | Department: %s",
                name, age, salary, address, manager.getTitle(), department.getName());
    }
}
