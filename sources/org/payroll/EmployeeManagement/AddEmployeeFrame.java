package org.payroll.EmployeeManagement;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

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

    ArrayList<String> positions = Main.dbManager.getListOfPositionName();

    public AddEmployeeFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(AddEmployeePanel);
        setMinimumSize(new Dimension(550, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

//        JCBoxPosition = new JComboBox<String>(positions.toArray(new String[positions.size()]));
        JCBoxPosition.setModel(new DefaultComboBoxModel(positions.toArray(new String[positions.size()])));

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id, name, email;
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
