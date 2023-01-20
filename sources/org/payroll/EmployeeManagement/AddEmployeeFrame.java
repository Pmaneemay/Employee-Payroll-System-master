package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEmployeeFrame extends JFrame{
    private JPanel AddEmployeePanel;
    private JLabel JTFldTitle;
    private JTextField JTFldEmpID;
    private JTextField JTFldEmpFirstName;
    private JTextField JTFldEmpEmail;
    private JComboBox JCBoxPosition;
    private JButton JBtnAdd;
    private JButton JBtnCancel;
    private JLabel JLblEmpID;
    private JLabel JLbldEmpFirstName;
    private JLabel JLblEmpEmail;
    private JLabel JLblEmpPosition;
    private JLabel JLblLastName;
    private JTextField JTFldLastName;

    ArrayList<String> positions = Main.dbManager.getListOfPositionName();

    public AddEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(AddEmployeePanel);
        setMinimumSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

//        JCBoxPosition = new JComboBox<String>(positions.toArray(new String[positions.size()]));
        JCBoxPosition.setModel(new DefaultComboBoxModel(positions.toArray(new String[positions.size()])));

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, Fname, Lname, email, position;
                position = JCBoxPosition.getSelectedItem().toString();
                id = JTFldEmpID.getText();
                email = JTFldEmpEmail.getText();
                Fname = JTFldEmpFirstName.getText();
                Lname = JTFldLastName.getText();

                Main.dbManager.createEmployee(id, Fname, Lname, email, position);

                JOptionPane.showMessageDialog(
                        null,
                        "New Employee Added",
                        "New Employee Added",
                        JOptionPane.INFORMATION_MESSAGE
                );
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
