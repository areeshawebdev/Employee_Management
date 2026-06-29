import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagementSystem {

    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Edit Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Sort Employees By ID");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Employee Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Employee Salary: ");
                    double salary = sc.nextDouble();

                    employees.add(new Employee(id, name, salary));

                    System.out.println("Employee Added Successfully!");
                    break;

                case 2:

                    if(employees.isEmpty()) {

                        System.out.println("No Employees Found!");

                    } else {

                        for(Employee emp : employees) {
                            emp.display();
                        }
                    }

                    break;

                case 3:

                    System.out.print("Enter Employee ID to Search: ");
                    int searchId = sc.nextInt();

                    boolean found = false;

                    for(Employee emp : employees) {

                        if(emp.id == searchId) {

                            emp.display();
                            found = true;
                            break;
                        }
                    }

                    if(!found) {
                        System.out.println("Employee Not Found!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Employee ID to Edit: ");
                    int editId = sc.nextInt();
                    sc.nextLine();

                    boolean updated = false;

                    for(Employee emp : employees) {

                        if(emp.id == editId) {

                            System.out.print("Enter New Name: ");
                            emp.name = sc.nextLine();

                            System.out.print("Enter New Salary: ");
                            emp.salary = sc.nextDouble();

                            updated = true;

                            System.out.println("Employee Updated Successfully!");
                            break;
                        }
                    }

                    if(!updated) {
                        System.out.println("Employee Not Found!");
                    }

                    break;

                case 5:

                    System.out.print("Enter Employee ID to Delete: ");
                    int deleteId = sc.nextInt();

                    boolean removed =
                            employees.removeIf(emp -> emp.id == deleteId);

                    if(removed) {
                        System.out.println("Employee Deleted Successfully!");
                    } else {
                        System.out.println("Employee Not Found!");
                    }

                    break;

                case 6:

                    for(int i = 0; i < employees.size() - 1; i++) {

                        for(int j = i + 1; j < employees.size(); j++) {

                            if(employees.get(i).id >
                               employees.get(j).id) {

                                Employee temp = employees.get(i);

                                employees.set(i, employees.get(j));

                                employees.set(j, temp);
                            }
                        }
                    }

                    System.out.println("Employees Sorted By ID Successfully!");

                    break;

                case 7:

                    System.out.println("Exiting Program...");
                    break;

                default:

                    System.out.println("Invalid Choice!");
            }

        } while(choice != 7);

        sc.close();
    }
}

