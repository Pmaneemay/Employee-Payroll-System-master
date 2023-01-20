package org.payroll.EmployeeSalary;

import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EmployeeSalaryFrame extends JFrame{
    private JSplitPane EmployeeSalaryPanel;
    private JPanel EmployeeAttendancePanel;
    private JTable JTblReport;
    private JScrollPane JSPnReport;
    private JLabel JLblTitle;
    private JLabel JTFldMonth;
    private JLabel JTFldYear;
    private JPanel JPnlYear;
    private JButton JBtnGet;
    private JLabel JLblYear;
    private JLabel JLblMonth;
    private JPanel JPnlMonth;
    private JButton JBtnBack;
    private JButton JBtnDownload;

    JYearChooser yearChooser = new JYearChooser();
    JMonthChooser monthChooser = new JMonthChooser();

    public EmployeeSalaryFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmployeeSalaryPanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        JPnlYear.add(yearChooser);
        JPnlMonth.add(monthChooser);

        Object[][] data = Main.dbManager.getAllMonthlySalary();
        String col[] = {"Employee ID","Employee Full Name","Month","Year","Total Salary","Total Hours Worked"};

        JTblReport.setModel(new DefaultTableModel(data,col));

        JBtnGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer yr = yearChooser.getYear();
                JLblYear.setText(yr.toString());
                Integer mnth = monthChooser.getMonth();
                mnth = mnth + 1;
                JLblMonth.setText(mnth.toString());

                Object[][] Ndata = Main.dbManager.getSalaryByMonthAndYear(mnth, yr);
                JTblReport.setModel(new DefaultTableModel(Ndata,col));
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
