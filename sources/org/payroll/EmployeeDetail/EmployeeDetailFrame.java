package org.payroll.EmployeeDetail;

import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;
import org.payroll.TableToPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JScrollPane JSPnlEmpDetail;



    public EmployeeDetailFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(RootPanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        Object[][] data = Main.dbManager.getEmployees();
        String col[] = {"Employee ID", "First Name", "Last Name", "Email Address", "Position"};

        JTblEmployeeDetail.setModel(new DefaultTableModel(data,col));


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
        JBtnDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableToPDF().EmployeePDF(data);
            }
        });
    }
}
