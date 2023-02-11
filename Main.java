import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileReader readerFileEmp = new FileReader("InputEmployeers.txt");
        FileReader readerFileMan = new FileReader("InputManager.txt");
        Scanner scannerEmpFromFile = new Scanner(readerFileEmp);
        Scanner scannerManFromFile = new Scanner(readerFileMan);

        Manager[] managers = new Manager[1];
        int index = 0;
        while (scannerManFromFile.hasNextLine()) {
            if (index > 0)
                scannerManFromFile.nextLine();

            String name = scannerManFromFile.nextLine();
            String surname = scannerManFromFile.nextLine();
            double salary = Double.parseDouble(scannerManFromFile.nextLine());
            double bonus = Double.parseDouble(scannerManFromFile.nextLine());

            managers[index] = new Manager(name, surname, salary, bonus);
            index++;
        }

        Employee[] employees = new Employee[2];
        index = 0;
        while (scannerEmpFromFile.hasNextLine()) {
            if (index > 0)
                scannerEmpFromFile.nextLine();

            String name = scannerEmpFromFile.nextLine();
            String surname = scannerEmpFromFile.nextLine();
            double salary = Double.parseDouble(scannerEmpFromFile.nextLine());

            employees[index] = new Employee(name, surname, salary);
            employees[index].setManager(managers[0]);
            index++;
        }

        ObjectOutputStream objectManOutputStream = new ObjectOutputStream(
                new FileOutputStream("Manager.txt"));
        objectManOutputStream.writeObject(managers[0]);
        objectManOutputStream.close();

        ObjectOutputStream objectEmpOutputStream = new ObjectOutputStream(
                new FileOutputStream("Employee.txt"));
        objectEmpOutputStream.writeObject(employees[0]);
        objectEmpOutputStream.writeObject(employees[1]);
        objectEmpOutputStream.close();

        ObjectInputStream objectManInputStream = new ObjectInputStream(
                new FileInputStream("Manager.txt"));
        Manager managerRestored = (Manager) objectManInputStream.readObject();
        objectManInputStream.close();

        ObjectInputStream objectEmpInputStream = new ObjectInputStream(
                new FileInputStream("Employee.txt"));
        Employee employee0Restored = (Employee) objectEmpInputStream.readObject();
        Employee employee1Restored = (Employee) objectEmpInputStream.readObject();
        objectEmpInputStream.close();

        System.out.println("Before Serialize:");
        System.out.println(managers[0]);
        System.out.println(employees[0]);
        System.out.println(employees[1]);

        System.out.println("After Restored:");
        System.out.println(managerRestored);
        System.out.println(employee0Restored);
        System.out.println(employee1Restored);
    }
}
