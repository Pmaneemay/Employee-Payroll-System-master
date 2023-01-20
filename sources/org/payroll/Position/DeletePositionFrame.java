package org.payroll.Position;

import org.payroll.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePositionFrame extends JFrame{
    private JPanel DeletePositionPanel;
    private JButton JBtnCancel;
    private JButton JBtnDelete;
    private JLabel JLblTitle;
    private JLabel JLblNamePost;
    private JComboBox JCBoxPosition;

    ArrayList<String> positions = Main.dbManager.getListOfPositionName();

    public DeletePositionFrame() {
        super();
        setTitle("EMPLOYEE PAYROLL SYSTEM");
        setContentPane(DeletePositionPanel);
        setMinimumSize(new Dimension(550, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JCBoxPosition.setModel(new DefaultComboBoxModel(positions.toArray(new String[positions.size()])));

        JBtnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
        JBtnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String position = JCBoxPosition.getSelectedItem().toString();

                Main.dbManager.deletePosition(position);

                JOptionPane.showMessageDialog(
                        null,
                        "Position Deleted",
                        "Position Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
                (new PositionFrame()).setVisible(true);
                dispose();
            }
        });
    }
}
