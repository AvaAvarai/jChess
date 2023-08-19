import javax.swing.*;
import java.awt.*;

public class StartMenu {
    public static PieceColor showMenu() {
        // Load and scale the icon for the JOptionPane
        ImageIcon originalIcon = new ImageIcon(StartMenu.class.getResource("icons/king_black.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        // Create a hidden parent JFrame to set its icon
        JFrame hiddenFrame = new JFrame();
        hiddenFrame.setIconImage(scaledIcon.getImage());
        hiddenFrame.setUndecorated(true);  // No decorations such as title bar
        hiddenFrame.setVisible(true);      // Needs to be visible to be the parent of JOptionPane
        hiddenFrame.setLocationRelativeTo(null);  // Center the hidden frame

        Object[] options = {"White", "Black"};
        int choice = JOptionPane.showOptionDialog(hiddenFrame,
                "Choose your pieces:",
                "Start Menu",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                scaledIcon,  // Use the scaled icon
                options,
                options[0]);

        // Clean up after showing the dialog
        hiddenFrame.dispose();

        // If the exit button is pressed, close the application
        if (choice == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }

        return (choice == JOptionPane.YES_OPTION) ? PieceColor.WHITE : PieceColor.BLACK;
    }
}
