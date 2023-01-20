package org.payroll.ProfileManager;

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
    private JTextField JTFldOldUsername;
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
                JOptionPane.showMessageDialog(
                        null,
                        "Username Changed",
                        "Username Changed",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });
    }
}
