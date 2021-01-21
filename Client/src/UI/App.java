package UI;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                var mainFrame = new MainFrame();
                mainFrame.pack();
                mainFrame.setSize(new Dimension(850, 600));
                mainFrame.setVisible(true);
            }
        });
    }

}