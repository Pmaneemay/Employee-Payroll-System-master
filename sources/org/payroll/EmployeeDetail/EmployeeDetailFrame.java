package org.payroll.EmployeeDetail;

import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeDetailFrame extends JFrame{
    private JPanel EmployeeDetailPanel;
    private JButton JBtnReload;
    private JLabel JLblTitle;
    private JTable JTblEmployeeDetail;
    private JSplitPane RootPanel;
    private JButton JBtnDownload;
    private JButton JBtnBack;

    public EmployeeDetailFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(RootPanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new EmployeeDetailFrame()).setVisible(true);
                dispose();
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
