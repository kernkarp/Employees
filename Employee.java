import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private static int nextId = 0;
    private String name;
    private String surname;
    private double salary;
    private Manager manager;

    public Employee(String name, String surname, double salary) {
        if (name == null)
            throw new NullPointerException("Error: Employee name is null");
        if (name.isBlank())
            throw new IllegalArgumentException("Error: Employee name is empty");
        if (surname == null)
            throw new NullPointerException("Error: Employee surname is null");
        if (surname.isBlank())
            throw new IllegalArgumentException("Error: Employee surname is empty");
        if (salary <= 0)
            throw new IllegalArgumentException("Error: Employee salary incorrect value");

        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.id = nextId;
        nextId++;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("Error: Employee name is null");
        if (name.isBlank())
            throw new IllegalArgumentException("Error: Employee name is empty");

        this.name = name;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    public void setSalary(double salary) {
        if (salary <= 0)
            throw new IllegalArgumentException("Error: Employee salary incorrect value");

        this.salary = salary;
    }

    public void setSurname(String surname) {
        if (surname == null)
            throw new NullPointerException("Error: Employee surname is null");
        if (surname.isBlank())
            throw new IllegalArgumentException("Error: Employee surname is empty");

        this.surname = surname;
    }

    public final void setManager(Manager manager) {
        this.manager = manager;
    }

    public final Manager getManager() {
        return manager;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public static int getNextId() {
        return nextId;
    }

    public void raiseSalary(double p) {
        salary += salary * (p/100);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", salary=" + salary +
                ", manager=" + manager +
                '}';
    }
}
