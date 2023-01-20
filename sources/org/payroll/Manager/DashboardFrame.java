package org.payroll.Manager;

import org.payroll.EmployeeAttendance.EmployeeAttendanceFrame;
import org.payroll.EmployeeDetail.EmployeeDetailFrame;
import org.payroll.EmployeeManagement.EmployeeManagementFrame;
import org.payroll.EmployeeSalary.EmployeeSalaryFrame;
import org.payroll.Main;
import org.payroll.Position.PositionFrame;
import org.payroll.ProfileManager.MenuProfileFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame{

    private JPanel DashboardPanel;
    private JLabel JLblDashboard;
    private JButton JBtnEmpManage;
    private JButton JBtnEmpDetail;
    private JButton JBtnEmpAttendance;
    private JButton JBtnEmpSalary;
    private JMenuBar JMBDashboard;
    private JButton JBtnProfile;
    private JButton JBtnHelp;
    private JButton JBtnQuit;
    private JButton JBtnPosition;

    public DashboardFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DashboardPanel);
        setMinimumSize(new Dimension(900, 680));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnEmpManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeManagementFrame()).setVisible(true);
            }
        });

        JBtnEmpDetail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeDetailFrame()).setVisible(true);
            }
        });

        JBtnEmpSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                (new EmployeeSalaryFrame()).setVisible(true);
            }
        });

        JBtnEmpAttendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[][] data = Main.dbManager.getAllAttendance();
                dispose();
                (new EmployeeAttendanceFrame(data)).setVisible(true);
            }
        });

        JBtnProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new MenuProfileFrame()).setVisible(true);
                dispose();
            }

        });

        JBtnHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Employee Payroll Syatem\nAuthor: Group 51 ",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JBtnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JBtnPosition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
