package org.payroll.EmployeeManagement;

import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeManagementFrame extends JFrame{
    private JPanel EmpManagementPanel;
    private JLabel JLblTitle;
    private JButton JBtnAddEmp;
    private JButton JBtnDeleteEmp;
    private JButton JBtnUpdateEmployee;
    private JButton JBtnBack;

    public EmployeeManagementFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmpManagementPanel);
        setMinimumSize(new Dimension(900, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnAddEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new AddEmployeeFrame()).setVisible(true);
            }
        });

        JBtnDeleteEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new DeleteEmployeeFrame()).setVisible(true);
            }
        });

        JBtnUpdateEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new UpdateEmployeeFrame()).setVisible(true);
            }
        });

        JBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DashboardFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
