import javax.swing.*;

public class tool extends JFrame {
    private JTabbedPane tabbedPane1;
    private JTextPane lendedPane;
    private JTextField lenderFieldLent;
    private JTextField itemFieldlent;
    private JButton lentButton;
    private JTextField itemFieldReturn;
    private JButton returnButton;
    private JPanel lenderPlane;
    private JPanel lentPlane;
    private JPanel overviewPlane;
    private JPanel returnPlane;
    private JTextField lenderNameField;
    private JTextField lenderAddressField;
    private JButton makeLenderButton;
    private JPasswordField adminPasswordField;
    private JTextField addTypeTextField;
    private JTextField removeTypeTextField;
    private JComboBox addItemComboBox;
    private JButton addTypeButton;
    private JButton addItemButton;
    private JButton removeItemButton;
    private JPanel rootPanel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public tool() {
        add(rootPanel);
        setSize(600,400);
    }
}
