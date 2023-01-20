package org.payroll.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPositionFrame extends JFrame{
    private JPanel AddPositionPanel;
    private JTextField JTFldIdPost;
    private JTextField JTFldNamePost;
    private JTextField JTFldHourlyRate;
    private JTextField JTFldOvertimeRate;
    private JButton JBtnCancel;
    private JButton JBtnAdd;
    private JLabel JLblTitle;
    private JLabel JLblIdPost;
    private JLabel JLblNamePost;
    private JLabel JLblHourlyRate;
    private JLabel JLblOvertimeRate;

    public AddPositionFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(AddPositionPanel);
        setMinimumSize(new Dimension(550, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JBtnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Position Added",
                        "Position Added",
                        JOptionPane.INFORMATION_MESSAGE
                );
                dispose();
            }
        });

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
