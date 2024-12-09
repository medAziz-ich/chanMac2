import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MACChangerGUI extends JFrame {

    private JTextField interfaceField;
    private JButton generateButton;
    private JLabel resultLabel;

    public MACChangerGUI() {
        setTitle("ChanMac");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set custom Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel inputPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        interfaceField = new JTextField();
        interfaceField.setBorder(BorderFactory.createTitledBorder("Enter Network Interface"));
        inputPanel.add(interfaceField);

        generateButton = new JButton("Generate & Change MAC");
        generateButton.setFont(new Font("Arial", Font.BOLD, 16));
        generateButton.setBackground(new Color(34, 139, 34));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        inputPanel.add(generateButton);

        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel.setForeground(Color.BLUE);
        inputPanel.add(resultLabel);

        add(inputPanel);

        generateButton.addActionListener(e -> changeMAC());
    }

private void changeMAC() {
    String networkInterface = interfaceField.getText().trim();
    if (networkInterface.isEmpty()) {
        resultLabel.setText("Please enter a valid network interface.");
        return;
    }

    // Custom panel setup
    JPasswordField passwordField = new JPasswordField(15);
    JLabel label = new JLabel("Enter Root Password:");
    label.setFont(new Font("Arial", Font.BOLD, 14));
    label.setForeground(new Color(34, 139, 34));

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.gridx = 0;
    gbc.gridy = 0;
    panel.add(label, gbc);

    gbc.gridy = 1;
    panel.add(passwordField, gbc);

    // Load and resize icon
    ImageIcon originalIcon = new ImageIcon(getClass().getResource("key.png")); // Replace with your path
    Image resizedImage = originalIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);

    ImageIcon resizedIcon = new ImageIcon(resizedImage);

    int option = JOptionPane.showConfirmDialog(
        this,
        panel,
        "Authentication Required",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE,
        resizedIcon
    );

    if (option == JOptionPane.OK_OPTION) {
        String password = new String(passwordField.getPassword());

        try {
            String command = "echo " + password + " | sudo -S ./chanmac " + networkInterface;
            Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", command});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String output = reader.readLine();
            if (output != null) {
                resultLabel.setText("New MAC Address: " + output);
            } else {
                resultLabel.setText("Error changing MAC address.");
            }

        } catch (Exception ex) {
            resultLabel.setText("Exception: " + ex.getMessage());
        }
    }
}





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MACChangerGUI gui = new MACChangerGUI();
            gui.setVisible(true);
        });
    }
}
