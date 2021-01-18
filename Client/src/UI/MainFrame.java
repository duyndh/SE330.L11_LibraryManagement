package UI;

import UI.Controllers.BooksControlller;
import UI.Controllers.Controller;
import UI.Views.BaseScene;
import UI.Views.BooksScene;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JPanel rootPanel;
    private JPanel leftSidePanel;
    private JPanel scencePanel;
    private final CardLayout cardLayout = new CardLayout();


    private final Controller[] controllers;
    private final BaseScene[] scenes = new BaseScene[] {
        new BooksScene()
    };

    public MainFrame() {
        add(rootPanel);
        setVisible(true);

        scencePanel.setLayout(cardLayout);

        scencePanel.setBackground(Color.cyan);
        scencePanel.add(scenes[0]);

        this.controllers = this.initControllers();
        this.controllers[0].onAppear();
    }

    private Controller[] initControllers() {
        var controllers = new Controller[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            var scene = scenes[i];
            if (scene instanceof BooksScene) {
                controllers[i] = new BooksControlller((BooksScene)scene);
            }
        }
        return controllers;
    }

}
