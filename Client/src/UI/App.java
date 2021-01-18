package UI;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                var mainFrame = new MainFrame();
                mainFrame.pack();
                mainFrame.setSize(new Dimension(800, 600));
                mainFrame.setVisible(true);
            }
        });
    }

}
