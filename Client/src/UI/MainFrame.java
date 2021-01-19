package UI;

import UI.Controllers.*;
import UI.Views.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel scencePanel;
    private final CardLayout cardLayout = new CardLayout();


    private final BaseController[] controllers;
    private final BaseScene[] scenes = new BaseScene[] {
            new BookItemScene(),
            new AuthorScene(),
            new CategoryScene(),
            new BookScene(),
            new WarehouseHistoryScene(),
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
        scencePanel.add(scenes[1]);

        this.controllers = this.initControllers();
        this.controllers[1].onAppear();
    }

    private BaseController[] initControllers() {
        var controllers = new BaseController[scenes.length];
        for (int i = 0; i < scenes.length; i++) {
            var scene = scenes[i];
            if (scene instanceof BookItemScene) {
                controllers[i] = new BookItemControlller(scene);
            }

            if (scene instanceof AuthorScene) {
                controllers[i] = new AuthorController(scene);
            }

            if (scene instanceof CategoryScene) {
                controllers[i] = new CategoryController(scene);
            }

            if (scene instanceof BookScene) {
                controllers[i] = new BookController(scene);
            }

            if (scene instanceof WarehouseHistoryScene) {
                controllers[i] = new WarehouseHistoryController(scene);
            }
        }
        return controllers;
    }

}
