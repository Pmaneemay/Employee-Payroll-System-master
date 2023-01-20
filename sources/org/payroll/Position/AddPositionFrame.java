package org.payroll.Position;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPositionFrame extends JFrame{
    private JPanel AddPositionPanel;
    private JTextField JTFldNamePost;
    private JTextField JTFldHourlyRate;
    private JTextField JTFldOvertimeRate;
    private JButton JBtnCancel;
    private JButton JBtnAdd;
    private JLabel JLblTitle;
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
                String name;
                name = JTFldNamePost.getText();
                int h_rate = Integer.parseInt(JTFldHourlyRate.getText());
                int o_rate = Integer.parseInt(JTFldOvertimeRate.getText());

                Main.dbManager.newPosition(name, h_rate, o_rate);

                JOptionPane.showMessageDialog(
                        null,
                        "Position Added",
                        "Position Added",
                        JOptionPane.INFORMATION_MESSAGE
                );

                setVisible(false);
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
