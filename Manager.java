import java.io.Serializable;

public class Manager extends Employee implements Serializable {
    private double bonus;
    public Manager(String name, String surname, double salary, double bonus) {
        super(name, surname, salary);

        if (bonus < 0)
            throw new IllegalArgumentException("Error: Manager bonus incorrect value");

        this.bonus = bonus;
    }

    public double getSalary() {
        return super.getSalary() + bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        if (bonus < 0)
            throw new IllegalArgumentException("Error: Manager bonus incorrect value");

        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", salary=" + getSalary() +
                ", bonus=" + bonus +
                '}';
    }
}
