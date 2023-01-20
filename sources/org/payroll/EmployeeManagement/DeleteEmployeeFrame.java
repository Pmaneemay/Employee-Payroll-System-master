package org.payroll.EmployeeManagement;

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
                dispose();
            }
        });

        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //call function that will search the id and delete employee
                JOptionPane.showMessageDialog(
                        null,
                        "Employee Deleted",
                        "Employee Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });
    }
}
