package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UpdateEmployeeFrame extends JFrame{
    private JPanel UpdateEmployeePanel;
    private JLabel JLblTitle;
    private JComboBox JCBoxEmpId;
    private JTextField JTFldEmpFirstName;
    private JTextField JTFldEmpEmail;
    private JButton JBtnCancel;
    private JButton JBtnUpadate;
    private JLabel JLblEmpID;
    private JLabel JLblEmpFirstName;
    private JLabel JLblEmail;
    private JLabel JLblPosition;
    private JComboBox JCBoxEmpPosition;
    private JLabel JTFldEmpLastName;
    private JTextField JTFldLastName;

    ArrayList<String> position = Main.dbManager.getListOfPositionName();
    ArrayList<String> id_emp = Main.dbManager.getEmployeeID();

    public UpdateEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(UpdateEmployeePanel);
        setMinimumSize(new Dimension(550, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JCBoxEmpPosition.setModel(new DefaultComboBoxModel(position.toArray(new String[position.size()])));
        JCBoxEmpId.setModel(new DefaultComboBoxModel(id_emp.toArray(new String[id_emp.size()])));

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnUpadate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, Fname, Lname, email, position;
                position = JCBoxEmpPosition.getSelectedItem().toString();
                id = JCBoxEmpId.getSelectedItem().toString();
                email = JTFldEmpEmail.getText();
                Fname = JTFldEmpFirstName.getText();
                Lname = JTFldLastName.getName();

                Main.dbManager.updateEmployee(id, Fname, Lname,email, position);

                JOptionPane.showMessageDialog(
                        null,
                        "Employee Updated",
                        "Employee Updated",
                        JOptionPane.INFORMATION_MESSAGE
                );

                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
