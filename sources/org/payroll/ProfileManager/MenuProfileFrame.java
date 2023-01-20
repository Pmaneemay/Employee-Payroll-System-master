package org.payroll.ProfileManager;

import org.payroll.Manager.DashboardFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuProfileFrame extends JFrame{
    private JPanel MenuProfilePanel;
    private JLabel JLblTitle;
    private JButton JBtnChangeUsername;
    private JButton JBtnChangePassword;
    private JButton JBtnBack;

    public MenuProfileFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(MenuProfilePanel);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnChangeUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new ChangeUsernameFrame()).setVisible(true);
            }
        });

        JBtnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new ChangePasswordFrame()).setVisible(true);

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
