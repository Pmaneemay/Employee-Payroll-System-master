package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEmployeeFrame extends JFrame{
    private JPanel DeleteEmployeePanel;
    private JLabel JLblTitle;
    private JTextField JTFldEmpID;
    private JButton JBtnCancel;
    private JButton JBtnDelete;
    private JLabel JLblEmpID;

    public DeleteEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DeleteEmployeePanel);
        setMinimumSize(new Dimension(450, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Id = JTFldEmpID.getText();

                Main.dbManager.deleteEmployee(Id);

                JOptionPane.showMessageDialog(
                        null,
                        "Employee Deleted",
                        "Employee Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
                (new EmployeeManagementFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
