package techcompany;

public abstract class Department {
    protected String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
