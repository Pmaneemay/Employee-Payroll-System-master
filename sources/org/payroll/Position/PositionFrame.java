package org.payroll.Position;

import org.payroll.Main;
import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PositionFrame extends JFrame {
    private JPanel JPnlPositionMenu;
    private JLabel JLblTitle;
    private JButton JBtnDelete;
    private JButton JBtnAdd;
    private JButton JBtnReload;
    private JButton JBtnBack;
    private JSplitPane PositionPanel;
    private JTable JTblListPostion;
    private JScrollPane JSPnlPosition;

    public PositionFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(PositionPanel);
        setMinimumSize(new Dimension(700, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        String col[] = {"Employee ID","Employee Position","Hourly Rate","Overtime Rate"};
        Object[][] data = Main.dbManager.getPositions();

        JTblListPostion.setModel(new DefaultTableModel(data,col));

        JBtnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DashboardFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnReload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new AddPositionFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new DeletePositionFrame()).setVisible(true);
                dispose();
            }
        });
    }

}
