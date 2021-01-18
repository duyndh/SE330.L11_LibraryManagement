package UI;

import javax.swing.*;
import java.awt.*;

public class TestMainUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                var mainFrame = new TestMainFrame();
                mainFrame.pack();
                mainFrame.setSize(new Dimension(800, 600));
                mainFrame.setVisible(true);
            }
        } );
    }

}
