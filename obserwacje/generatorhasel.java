import java.security.SecureRandom;
import javax.swing.*;
import java.awt.*;

public class generatorhasel {
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789" +
            "!@#$%^&*()-_=+[]{};:,.?/";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Password Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(430, 220);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lengthLabel = new JLabel("Password length:");
        JTextField lengthField = new JTextField("12", 12);

        JButton generateButton = new JButton("Generate");
        JTextArea passwordArea = new JTextArea(4, 24);
        passwordArea.setEditable(false);
        passwordArea.setLineWrap(true);
        passwordArea.setWrapStyleWord(true);
        passwordArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(passwordArea);

        generateButton.addActionListener(e -> {
            try {
                int length = Integer.parseInt(lengthField.getText().trim());

                if (length < 1) {
                    JOptionPane.showMessageDialog(frame,
                            "Password length must be at least 1.",
                            "Invalid value",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String password = generatePassword(length);
                passwordArea.setText(password);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame,
                        "Please enter a valid whole number.",
                        "Invalid input",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lengthLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        panel.add(lengthField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(generateButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int characterIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(characterIndex));
        }
        return password.toString();
    }
}
