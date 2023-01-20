package org.payroll.ProfileManager;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUsernameFrame extends JFrame{
    private JPanel ChangeUsernamePanel;
    private JButton JBtnCancel;
    private JButton JBtnChange;
    private JLabel JLblTitle;
    private JPasswordField JPFldPassword;
    private JTextField JTFldNewUsername;
    private JTextField JTFldConfirmUsername;
    private JLabel JLblPassword;
    private JLabel JLblNewUsername;
    private JLabel JLblConfirmUsername;

    public ChangeUsernameFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(ChangeUsernamePanel);
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
                String Pass = (JPFldPassword.getPassword()).toString();
                String N_UName = (JTFldNewUsername.getText());
                String C_UName = (JTFldConfirmUsername.getText());

                if (N_UName == C_UName) {
                    if (Main.dbManager.verifyLoginId(Pass)) {
                        Main.dbManager.changeUsername(N_UName, Pass);
                        JOptionPane.showMessageDialog(
                                null,
                                "Username Changed",
                                "Username Changed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Username Change Failed\nYou enter wrong password",
                                "Username Change Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        JPFldPassword.setText("");
                        JTFldNewUsername.setText("");
                        JTFldConfirmUsername.setText("");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Username Change Failed\nCannot confirm the new username",
                            "Cannot confirm the new username",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    JPFldPassword.setText("");
                    JTFldNewUsername.setText("");
                    JTFldConfirmUsername.setText("");
                }
            }
        });
    }
}
