package org.payroll.Manager;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ManagerLoginFrame extends JFrame {
    private JPanel ManagerLoginPanel;
    private JLabel JLblTitle;
    private JTextField JTFldUsername;
    private JPasswordField JPFldPassword;
    private JButton JBtnLogin;
    private JLabel JPnlUsername;
    private JLabel JPnlPassword;
    private JButton JBtnExit;

    public ManagerLoginFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(ManagerLoginPanel);
        setMinimumSize(new Dimension(500, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.dbManager.verifyLoginId(JTFldUsername.getText(), new String(JPFldPassword.getPassword()))){
                    LoginSuccessful();
                }
                else{
                    LoginFailed();
                }
            }
        });

        JBtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void LoginSuccessful() {
        JOptionPane.showMessageDialog(
                null,
                "Login Successful",
                "Login Successful",
                JOptionPane.INFORMATION_MESSAGE
        );

        setVisible(false);
        (new DashboardFrame()).setVisible(true);
        dispose();
    }

    void LoginFailed() {
        JOptionPane.showMessageDialog(
                null,
                "Wrong username or password",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
        );

        JTFldUsername.setText("");
        JPFldPassword.setText("");
    }
}
