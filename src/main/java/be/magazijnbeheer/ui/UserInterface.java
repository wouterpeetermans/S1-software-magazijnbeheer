package be.magazijnbeheer.ui;

import be.magazijnbeheer.core.Database;
import be.magazijnbeheer.core.Item;
import be.magazijnbeheer.core.Lender;
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
    private JButton refreshLender;
    private ViewModel viewModel;

    private void startUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        addItemComboBox.setModel(new DefaultComboBoxModel(viewModel.getAllTypes().toArray()));
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
                    addItemComboBox.setModel(new DefaultComboBoxModel(viewModel.getAllTypes().toArray()));
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password", "Wrong password", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong password");
                }
            }
        });
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(adminPasswordField.getPassword(), password)) {
                    Item item = viewModel.addItem(addItemComboBox.getSelectedItem().toString());
                    if (item != null){
                        JOptionPane.showMessageDialog(null,"item of type: " + item.getType().getTypeName() + "\n added with ID: " + Integer.toString(item.getId()), "new item", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,"an error occured", "error",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password", "Wrong password", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong password");
                }
            }
        });
        removeItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Arrays.equals(adminPasswordField.getPassword(), password)) {
                    viewModel.removeItem(removeItemTextField.getText());
                } else {
                    JOptionPane.showMessageDialog(null, "Wrong password", "Wrong password", JOptionPane.ERROR_MESSAGE);
                    System.out.println("Wrong password");
                }
            }
        });
        addLenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Lender lender = viewModel.addLender(lenderNameField.getText(), lenderAddressField.getText());
                if (lender != null){
                    JOptionPane.showMessageDialog(null, lender.getName() + " added\nAddress: " + lender.getAddress() + "\nID: " + Integer.toString(lender.getId()), "new lender", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "an error occurred the user might already exist","error",JOptionPane.ERROR_MESSAGE);
                }
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
        refreshLender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lendedPane.setText(viewModel.viewItemsLender(lenderIDFieldLent.getText()));
                System.out.println("update lendedPlane");
            }
        });
    }
}
