package UI;

import com.java.panels.LoginPanel;
import com.java.project.Utils;

import javax.swing.*;
import java.awt.*;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

//                var loginPane = new LoginPanel();
//                var loginFrame = new JFrame();
//                loginFrame.setLayout(new GridLayout());
//                loginFrame.getContentPane().add(loginPane.get_panel());
//                loginFrame.pack();
//                loginFrame.setSize(new Dimension(320, 150));
//                loginFrame.setVisible(true);
//
//                loginPane.setLoginResultHandler(res -> {
//                    if (res) {
//                        var mainFrame = new MainFrame();
//                        mainFrame.pack();
//                        mainFrame.setSize(new Dimension(950, 600));
//                        loginFrame.setVisible(false);
//                        mainFrame.setVisible(true);
//                    } else {
//                        Utils.showError("Wrong username or password.");
//                    }
//                });
                var mainFrame = new MainFrame();
                mainFrame.pack();
                mainFrame.setSize(new Dimension(900, 600));
                mainFrame.setVisible(true);
            }
        });
    }

}