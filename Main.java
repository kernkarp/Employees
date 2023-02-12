import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileReader readerFileEmp = new FileReader("InputEmployeers.txt");
        FileReader readerFileMan = new FileReader("InputManager.txt");
        Scanner scannerEmpFromFile = new Scanner(readerFileEmp);
        Scanner scannerManFromFile = new Scanner(readerFileMan);

        ArrayList<Manager> managers = new ArrayList<>();
        while (scannerManFromFile.hasNextLine()) {
            if (managers.size() > 0)
                scannerManFromFile.nextLine();

            String name = scannerManFromFile.nextLine();
            String surname = scannerManFromFile.nextLine();
            double salary = Double.parseDouble(scannerManFromFile.nextLine());
            double bonus = Double.parseDouble(scannerManFromFile.nextLine());

            managers.add(new Manager(name, surname, salary, bonus));
        }

        ArrayList<Employee> employees = new ArrayList<>();
        while (scannerEmpFromFile.hasNextLine()) {
            if (employees.size() > 0)
                scannerEmpFromFile.nextLine();

            String name = scannerEmpFromFile.nextLine();
            String surname = scannerEmpFromFile.nextLine();
            double salary = Double.parseDouble(scannerEmpFromFile.nextLine());

            employees.add(new Employee(name, surname, salary));
            employees.get(employees.size() - 1).setManager(managers.get(0));
        }

        readerFileMan.close();
        readerFileEmp.close();

        new EmployeeMenu("Employee App");
    }
}
