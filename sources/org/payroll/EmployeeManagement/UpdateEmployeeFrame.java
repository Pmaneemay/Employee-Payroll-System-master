package org.payroll.EmployeeManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateEmployeeFrame extends JFrame{
    private JPanel UpdateEmployeePanel;
    private JLabel JLblTitle;
    private JComboBox JCBoxEmpId;
    private JTextField JTFldEmpName;
    private JTextField JTFldEmpEmail;
    private JButton JBtnCancel;
    private JButton JBtnUpadate;
    private JLabel JLblEmpID;
    private JLabel JLblEmpName;
    private JLabel JLblEmail;
    private JLabel JLblPosition;
    private JComboBox JCBoxEmpPosition;

    public UpdateEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(UpdateEmployeePanel);
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

        JBtnUpadate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call funtion to update the database
                JOptionPane.showMessageDialog(
                        null,
                        "Employee Updated",
                        "Employee Updated",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });
    }
}
