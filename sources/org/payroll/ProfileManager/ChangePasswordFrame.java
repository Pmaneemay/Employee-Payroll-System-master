package org.payroll.ProfileManager;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordFrame extends JFrame{
    private JPanel ChangePasswordPanel;
    private JPasswordField JPFldPassword;
    private JPasswordField JPFldNewPassword;
    private JPasswordField JPFldConfirmPassword;
    private JButton JBtnCancel;
    private JButton JBtnChange;
    private JLabel JLblTitle;
    private JLabel JLblPassword;
    private JLabel JLblNewPassword;
    private JLabel JLblConfirmPassword;

    public ChangePasswordFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(ChangePasswordPanel);
        setMinimumSize(new Dimension(550, 500));
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

        JBtnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String O_Pass = (JPFldPassword.getPassword()).toString();
                String N_Pass = (JPFldNewPassword.getPassword()).toString();
                String C_Pass = (JPFldConfirmPassword.getPassword()).toString();

                if (N_Pass == C_Pass) {
                    if (Main.dbManager.verifyLoginId(O_Pass)) {
                        Main.dbManager.changePassword(O_Pass, N_Pass);
                        JOptionPane.showMessageDialog(
                                null,
                                "Password Changed",
                                "Password Changed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Password Change Failed\nYou enter wrong password",
                                "Password Change Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        JPFldPassword.setText("");
                        JPFldNewPassword.setText("");
                        JPFldConfirmPassword.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Password Change Failed\nCannot confirm the new password",
                            "Cannot confirm the new password",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    JPFldPassword.setText("");
                    JPFldNewPassword.setText("");
                    JPFldConfirmPassword.setText("");
                }
            }
        });
    }
}
