package smartStore2;

import javax.swing.*;

public class MainMenu {

    public static void main(String[] args) {
        // set native look and feel for better appearance on Windows
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            smartStore2.ui.MainFrame frame = new smartStore2.ui.MainFrame();
            frame.setVisible(true);
        });
    }
}
