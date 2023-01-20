package org.payroll.EmployeeManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeFrame extends JFrame{
    private JPanel AddEmployeePanel;
    private JLabel JTFldTitle;
    private JTextField JTFldEmpID;
    private JTextField JTFldEmpName;
    private JTextField JTFldEmpEmail;
    private JComboBox JCBoxPosition;
    private JButton JBtnAdd;
    private JButton JBtnCancel;
    private JLabel JLblEmpID;
    private JLabel JLbldEmpName;
    private JLabel JLblEmpEmail;
    private JLabel JLblEmpPosition;

    public AddEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(AddEmployeePanel);
        setMinimumSize(new Dimension(550, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call function to retrieve data from the frame and put it in the database
                JOptionPane.showMessageDialog(
                        null,
                        "New Employee Added",
                        "New Employee Added",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });
    }
}
