package org.payroll.EmployeeAttendance;

import com.toedter.calendar.JDateChooser;
import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class EmployeeAttendanceFrame extends JFrame{
    private JSplitPane EmployeeAttendancePanel;
    private JPanel JPnlEmployeeAtenndance;
    private JLabel JLblTitle;
    private JLabel JLblDate;
    private JPanel JPnlDate;
    private JLabel JLblDateTry;
    private JButton JBtnGet;
    private JScrollPane JSPnlEmpAtten;
    private JTable JTblEmpAttend;
    private JButton JBtnBack;
    private JButton JBtnDownload;

    Calendar cal = Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser();

    Object[][] data;

    public EmployeeAttendanceFrame(Object[][] Newdata) {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(EmployeeAttendancePanel);
        setMinimumSize(new Dimension(750, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        Object[][] attendance = Main.dbManager.getAllAttendance();
        String col[] ={"ID","Employee ID","Employee Full Name","Date","Clock In Time","Clock Out Time"};
        JTblEmpAttend.setModel(new DefaultTableModel(attendance, col));

        dateChooser.setDateFormatString("dd/MM/yyyy");
        JPnlDate.add(dateChooser);

        JBtnGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date dat = dateChooser.getDate();
                String dat1 = sdf.format(dat);
//                JLblDateTry.setText(dat1);
                Object[][] Attendance_data = Main.dbManager.getAttendance(dat1);
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
