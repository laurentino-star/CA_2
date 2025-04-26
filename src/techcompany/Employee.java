package techcompany;

public class Employee {
    private String name;
    private Manager manager;
    private Department department;

    public Employee(String name, Manager manager, Department department) {
        this.name = name;
        this.manager = manager;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public Manager getManager() {
        return manager;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return name + " - " + manager.getTitle() + " - " + department.getName();
    }
}
