package CA_2; // Defines the package this class belongs to

public class Employee { // Declares the Employee class
    // Private fields for storing employee details
    private String name; // Name of the employee
    private Department department; // Department the employee belongs to
    private Manager manager; // Manager assigned to the employee
    private String jobTitle; // Job title of the employee
    private String company; // Company where the employee works

    // Constructor to initialize the Employee object with all its attributes
    public Employee(String name, Department department, Manager manager, String jobTitle, String company) {
        this.name = name; // Assigns the name to the employee
        this.department = department; // Assigns the department to the employee
        this.manager = manager; // Assigns the manager to the employee
        this.jobTitle = jobTitle; // Assigns the job title to the employee
        this.company = company; // Assigns the company to the employee
    }

    // Getter method to retrieve the name of the employee
    public String getName() {
        return name; // Returns the name
    }

    // Getter method to retrieve the department of the employee
    public Department getDepartment() {
        return department; // Returns the department
    }

    // Getter method to retrieve the manager of the employee
    public Manager getManager() {
        return manager; // Returns the manager
    }

    // Getter method to retrieve the job title of the employee
    public String getJobTitle() {
        return jobTitle; // Returns the job title
    }

    // Getter method to retrieve the company of the employee
    public String getCompany() {
        return company; // Returns the company
    }

    // Overrides the default toString() method to provide a formatted string representation of the employee
    @Override
    public String toString() {
        return String.format("%-20s | %-20s | %-15s | %-20s | %-20s",
                name, // Employee's name
                department.getName(), // Name of the department
                manager.getLevel(), // Manager's level (assumes Manager class has a getLevel() method)
                jobTitle, // Employee's job title
                company // Employee's company
        );
    }
}
