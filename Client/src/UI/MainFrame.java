package UI;

import UI.Controllers.BooksControlller;
import UI.Controllers.BaseController;
import UI.Views.BaseScene;
import UI.Views.BooksScene;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel scencePanel;
    private final CardLayout cardLayout = new CardLayout();


    private final BaseController[] controllers;
    private final BaseScene[] scenes = new BaseScene[] {
        new BooksScene()
    };
    private JSplitPane splitPane;
    private JPanel rootPanel;
    private JPanel sidePanel;
    private JButton dashboard;

    public MainFrame() {
        add(rootPanel);
        setVisible(true);

        scencePanel.setLayout(cardLayout);

        scencePanel.setBackground(Color.cyan);
        scencePanel.add(scenes[0]);

        this.controllers = this.initControllers();
        this.controllers[0].onAppear();
    }

    private BaseController[] initControllers() {
        var controllers = new BaseController[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            var scene = scenes[i];
            if (scene instanceof BooksScene) {
                controllers[i] = new BooksControlller(scene);
            }
        }
        return controllers;
    }

}
