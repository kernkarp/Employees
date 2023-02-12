import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMenu extends JFrame {
    private JPanel mainPanel;
    private JButton showInfoButton;
    private JButton addNewEmpButton;
    private JButton raiseSalaryButton;
    private JButton deleteEmpButton;
    private JTextField IdTextField;
    private JRadioButton managerRadioButton;
    private JRadioButton employeeRadioButton;
    private static ArrayList<Employee> employees;

    public EmployeeMenu(String title) {
        super(title);
        setContentPane(mainPanel);
        setVisible(true);
        setBounds(100, 100, 780, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        managerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeeRadioButton.isSelected())
                    employeeRadioButton.setSelected(false);
            }
        });

        employeeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (managerRadioButton.isSelected())
                    managerRadioButton.setSelected(false);
            }
        });

        showInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(IdTextField.getText());
                    for (Employee employee : employees) {
                        if (employee.getId() == id && employee instanceof Manager manager) {
                            JOptionPane.showMessageDialog(null, manager.toString(), "Info Manager " + id,
                                    JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        else if (employee.getId() == id) {
                            JOptionPane.showMessageDialog(null, employee.toString(), "Info Employee " + id,
                                    JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }
                    throw new IllegalArgumentException("id not found");
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addNewEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!managerRadioButton.isSelected() && !employeeRadioButton.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Ви не вибрали кого вводити", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JFrame form = new JFrame();
                form.setBounds(300, 300, 410, 250);
                form.setVisible(true);
                form.setLayout(null);
                JButton buttonOk = new JButton("Ok");
                buttonOk.setBounds(5, (250-70), 40, 35);
                JButton buttonCancel = new JButton("Cancel");
                buttonCancel.setBounds((410-75), (250-70), 70, 35);
                JTextField textFieldName = new JTextField();
                textFieldName.setBounds(205, 0, 200, 35);
                JTextField textFieldSurname = new JTextField();
                textFieldSurname.setBounds(205, 40, 200, 35);
                JTextField textFieldSalary = new JTextField();
                textFieldSalary.setBounds(205, 80, 200, 35);
                JTextField textFieldBonus = new JTextField();
                textFieldBonus.setBounds(205, 120, 200, 35);
                JTextField textFieldManId = new JTextField();
                textFieldManId.setBounds(205, 120, 200, 35);
                JLabel labelName = new JLabel("Введіть імʼя");
                labelName.setBounds(5, 0, 200, 35);
                JLabel labelSurname = new JLabel("Введіть прізвище");
                labelSurname.setBounds(5, 40, 200, 35);
                JLabel labelSalary = new JLabel("Введіть заробітню плату");
                labelSalary.setBounds(5, 80, 200, 35);
                JLabel labelBonus = new JLabel("Введіть бонус");
                labelBonus.setBounds(5, 120, 200, 35);
                JLabel labelManId = new JLabel("Введіть id менеджера");
                labelManId.setBounds(5, 120, 200, 35);

                form.add(labelName);
                form.add(textFieldName);
                form.add(labelSurname);
                form.add(textFieldSurname);
                form.add(labelSalary);
                form.add(textFieldSalary);

                if (managerRadioButton.isSelected()) {
                    form.setTitle("Input Manager");
                    form.add(labelBonus);
                    form.add(textFieldBonus);
                } else {
                    form.setTitle("Input Employee");
                    form.add(labelManId);
                    form.add(textFieldManId);
                }

                buttonOk.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String name = textFieldName.getText();
                            String surname = textFieldSurname.getText();
                            double salary = Double.parseDouble(textFieldSalary.getText());
                            if (managerRadioButton.isSelected()) {
                                double bonus = Double.parseDouble(textFieldBonus.getText());
                                employees.add(new Manager(name, surname, salary, bonus));
                                form.dispose();
                                JOptionPane.showMessageDialog(null, "Complete", "Add Manager",
                                        JOptionPane.PLAIN_MESSAGE);
                            } else {
                                int manId = Integer.parseInt(textFieldManId.getText());
                                for (Employee employee : employees) {
                                    if (employee.getId() == manId && employee instanceof Manager) {
                                        Employee newEmployee = new Employee(name, surname, salary);
                                        newEmployee.setManager((Manager) employee);
                                        employees.add(newEmployee);
                                        form.dispose();
                                        JOptionPane.showMessageDialog(null, "Complete", "Add Employee",
                                                JOptionPane.PLAIN_MESSAGE);
                                    }
                                }
                                throw new IllegalArgumentException("Id not found");
                            }
                        } catch (IllegalArgumentException | NullPointerException exception) {
                            JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                            form.dispose();
                        }
                    }
                });

                buttonCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        form.dispose();
                    }
                });

                form.add(buttonOk);
                form.add(buttonCancel);
            }
        });

        deleteEmpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(IdTextField.getText());
                    for (Employee employee : employees) {
                        if (employee.getId() == id) {
                            employees.remove(employee);
                            JOptionPane.showMessageDialog(null, "Complete", "Delete Employee " + id,
                                    JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                    }
                    throw new IllegalArgumentException("id not found");
                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        raiseSalaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(IdTextField.getText());
                for (Employee employee : employees) {
                    if (employee.getId() == id) {
                        String p = JOptionPane.showInputDialog(null, "Введіь на скільки відсотків підняти зарплату");
                        employee.raiseSalary(Double.parseDouble(p));
                        return;
                    }
                }
                throw new IllegalArgumentException("id not found");
            }
        });

        addWindowListener(new WindowListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void windowOpened(WindowEvent e) {

            }

            public void windowClosing(WindowEvent event) {
                try {
                    FileWriter writerFileEmp = new FileWriter("inputEmployeers.txt");
                    FileWriter writerFileMan = new FileWriter("inputManager.txt");
                    int tmpMan = 0;
                    int tmpEmp = 0;
                    for (Employee employee : employees) {
                        if (employee instanceof Manager manager) {
                            if (tmpMan > 0)
                                writerFileMan.write("\n\n");

                            writerFileMan.write(String.format("%s\n%s\n%s\n%s",
                                    manager.getName(), manager.getSurname(), manager.getSalary() - manager.getBonus(),
                                    manager.getBonus()));
                            tmpMan++;
                        } else {
                            if (tmpEmp > 0)
                                writerFileEmp.write("\n\n");

                            writerFileEmp.write(String.format("%s\n%s\n%s",
                                    employee.getName(), employee.getSurname(), employee.getSalary()));
                            tmpEmp++;
                        }
                    }
                    writerFileEmp.close();
                    writerFileMan.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                System.exit(0);
            }

            /**
             * @param e the event to be processed
             */
            @Override
            public void windowClosed(WindowEvent e) {

            }

            /**
             * @param e the event to be processed
             */
            @Override
            public void windowIconified(WindowEvent e) {

            }

            /**
             * @param e the event to be processed
             */
            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            /**
             * @param e the event to be processed
             */
            @Override
            public void windowActivated(WindowEvent e) {

            }

            /**
             * @param e the event to be processed
             */
            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) throws IOException {
        FileReader readerFileEmp = new FileReader("InputEmployeers.txt");
        FileReader readerFileMan = new FileReader("InputManager.txt");
        Scanner scannerEmpFromFile = new Scanner(readerFileEmp);
        Scanner scannerManFromFile = new Scanner(readerFileMan);

        employees = new ArrayList<>();
        while (scannerManFromFile.hasNextLine()) {
            if (employees.size() > 0)
                scannerManFromFile.nextLine();

            String name = scannerManFromFile.nextLine();
            String surname = scannerManFromFile.nextLine();
            double salary = Double.parseDouble(scannerManFromFile.nextLine());
            double bonus = Double.parseDouble(scannerManFromFile.nextLine());

            employees.add(new Manager(name, surname, salary, bonus));
        }

        int tmp = 0;
        while (scannerEmpFromFile.hasNextLine()) {
            if (tmp > 0)
                scannerEmpFromFile.nextLine();

            String name = scannerEmpFromFile.nextLine();
            String surname = scannerEmpFromFile.nextLine();
            double salary = Double.parseDouble(scannerEmpFromFile.nextLine());

            employees.add(new Employee(name, surname, salary));
            employees.get(employees.size() - 1).setManager((Manager) employees.get(0));

            tmp++;
        }

        readerFileMan.close();
        readerFileEmp.close();

        new EmployeeMenu("Employee App");
    }
}
