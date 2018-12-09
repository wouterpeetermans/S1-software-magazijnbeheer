package be.magazijnbeheer.ui;

import be.magazijnbeheer.core.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UserInterface extends JFrame {
    private JTabbedPane tabbedPane1;
    private JTextPane lendedPane;
    private JTextField lenderIDFieldLent;
    private JTextField itemIDFieldlent;
    private JButton lentButton;
    private JTextField itemIDFieldReturn;
    private JButton returnButton;
    private JPanel lenderPlane;
    private JPanel lentPlane;
    private JPanel overviewPlane;
    private JPanel returnPlane;
    private JTextField lenderNameField;
    private JTextField lenderAddressField;
    private JButton addLenderButton;
    private JPasswordField adminPasswordField;
    private JTextField addTypeTextField;
    private JTextField removeItemTextField;
    private JComboBox addItemComboBox;
    private JButton addTypeButton;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JPanel rootPanel;
    private ViewModel viewModel;

    private void startUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public UserInterface() {
        viewModel = new ViewModel();
        startUI();
        final char[] password = {'U', 'A'};
        add(rootPanel);
        setSize(600,400);

        addTypeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(adminPasswordField.getPassword(), password)) {
                    viewModel.addType(addTypeTextField.getText());
                } else {
                    System.out.println("Wrong password");
                }
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(adminPasswordField.getPassword(), password)) {
                    viewModel.addItem(addItemComboBox.getSelectedItem().toString());
                } else {
                    System.out.println("Wrong password");
                }
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(adminPasswordField.getPassword(), password)) {
                    viewModel.removeItem(removeItemTextField.getText());
                } else {
                    System.out.println("Wrong password");
                }
            }
        });
        addLenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewModel.addLender(lenderNameField.getText(), lenderAddressField.getText());
            }
        });
        lentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewModel.addLenderToItem(lenderIDFieldLent.getText(), itemIDFieldlent.getText());
                lendedPane.setText(viewModel.viewItemsLender(lenderIDFieldLent.getText()));
                System.out.println("update lendedPlane");
            }
        });
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewModel.removeLenderFromItem(itemIDFieldReturn.getText());
                lendedPane.setText(viewModel.viewItemsLender(lenderIDFieldLent.getText()));
                System.out.println("update lendedPlane");
            }
        });
    }
}
