package org.payroll;

import org.payroll.Employee.EmployeeFrame;
import org.payroll.Manager.ManagerLoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserTypeFrame extends JFrame{
    private JPanel UserTypePanel;
    private JLabel JLblTitle;
    private JButton JBtnManager;
    private JButton JBtnEmployee;

    public UserTypeFrame(){
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(UserTypePanel);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeFrame()).setVisible(true);

            }
        });

        JBtnManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new ManagerLoginFrame()).setVisible(true);
            }
        });
    }

}
