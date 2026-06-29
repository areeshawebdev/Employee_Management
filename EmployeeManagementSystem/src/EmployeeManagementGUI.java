import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EmployeeManagementGUI extends JFrame {

    JTextField idField, nameField, salaryField;
    DefaultTableModel model;
    JTable table;

    public EmployeeManagementGUI() {

        setTitle("Employee Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Top Panel
        JPanel topPanel = new JPanel(new GridLayout(4, 3, 10, 10));

        JLabel idLabel = new JLabel("Employee ID:");
        JLabel nameLabel = new JLabel("Employee Name:");
        JLabel salaryLabel = new JLabel("Employee Salary:");

        idField = new JTextField();
        nameField = new JTextField();
        salaryField = new JTextField();

        JButton addButton = new JButton("Add Employee");
        JButton editButton = new JButton("Edit Employee");
        JButton deleteButton = new JButton("Delete Employee");
        JButton searchButton = new JButton("Search Employee");
        JButton sortButton = new JButton("Sort By ID");

        topPanel.add(idLabel);
        topPanel.add(idField);
        topPanel.add(addButton);

        topPanel.add(nameLabel);
        topPanel.add(nameField);
        topPanel.add(editButton);

        topPanel.add(salaryLabel);
        topPanel.add(salaryField);
        topPanel.add(deleteButton);

        topPanel.add(searchButton);
        topPanel.add(sortButton);

        // Table
        String[] columns = {"ID", "Name", "Salary"};

        model = new DefaultTableModel(columns, 0);

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        // Add Employee
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText();
                String name = nameField.getText();
                String salary = salaryField.getText();

                if(id.isEmpty() || name.isEmpty() || salary.isEmpty()) {

                    JOptionPane.showMessageDialog(null,
                            "Please Fill All Fields");

                } else {

                    model.addRow(new Object[]{id, name, salary});

                    JOptionPane.showMessageDialog(null,
                            "Employee Added Successfully!");

                    idField.setText("");
                    nameField.setText("");
                    salaryField.setText("");
                }
            }
        });

        // Select Row
        table.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                int row = table.getSelectedRow();

                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                salaryField.setText(model.getValueAt(row, 2).toString());
            }
        });

        // Edit Employee
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int row = table.getSelectedRow();

                if(row >= 0) {

                    model.setValueAt(idField.getText(), row, 0);
                    model.setValueAt(nameField.getText(), row, 1);
                    model.setValueAt(salaryField.getText(), row, 2);

                    JOptionPane.showMessageDialog(null,
                            "Employee Updated Successfully!");

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Please Select Employee To Edit");
                }
            }
        });

        // Delete Employee
        deleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int selectedRow = table.getSelectedRow();

                if(selectedRow >= 0) {

                    model.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(null,
                            "Employee Deleted Successfully!");

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Please Select Row To Delete");
                }
            }
        });

        // Search Employee
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String searchId =
                        JOptionPane.showInputDialog("Enter Employee ID");

                boolean found = false;

                for(int i = 0; i < model.getRowCount(); i++) {

                    if(model.getValueAt(i, 0).toString()
                            .equals(searchId)) {

                        table.setRowSelectionInterval(i, i);

                        JOptionPane.showMessageDialog(null,
                                "Employee Found!");

                        found = true;
                        break;
                    }
                }

                if(!found) {

                    JOptionPane.showMessageDialog(null,
                            "Employee Not Found!");
                }
            }
        });

        // Sort By ID
        sortButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int rows = model.getRowCount();

                for(int i = 0; i < rows - 1; i++) {

                    for(int j = i + 1; j < rows; j++) {

                        int id1 = Integer.parseInt(
                                model.getValueAt(i, 0).toString());

                        int id2 = Integer.parseInt(
                                model.getValueAt(j, 0).toString());

                        if(id1 > id2) {

                            Object tempId = model.getValueAt(i, 0);
                            Object tempName = model.getValueAt(i, 1);
                            Object tempSalary = model.getValueAt(i, 2);

                            model.setValueAt(
                                    model.getValueAt(j, 0), i, 0);
                            model.setValueAt(
                                    model.getValueAt(j, 1), i, 1);
                            model.setValueAt(
                                    model.getValueAt(j, 2), i, 2);

                            model.setValueAt(tempId, j, 0);
                            model.setValueAt(tempName, j, 1);
                            model.setValueAt(tempSalary, j, 2);
                        }
                    }
                }

                JOptionPane.showMessageDialog(null,
                        "Employees Sorted By ID!");
            }
        });

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {

        new EmployeeManagementGUI();
    }
}

