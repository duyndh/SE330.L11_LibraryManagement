package UI;

import UI.Controllers.*;
import UI.Views.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private JPanel scencePanel;
    private final CardLayout cardLayout = new CardLayout();


    private final BaseController[] controllers;
    private final BaseScene[] scenes = new BaseScene[] {
            new BookItemScene(),
            new BookScene(),
            new AuthorScene(),
            new CategoryScene(),
            new BorrowHistoryScene(),
            new WarehouseHistoryScene(),
            new MemberScene(),
            new StaffScene()
    };
    private JSplitPane splitPane;
    private JPanel rootPanel;
    private JPanel sidePanel;
    private JButton bookSideBtn;
    private JButton bookTypeSideBtn;
    private JButton authorSideBtn;
    private JButton categorySideBtn;
    private JButton memberSideBtn;
    private JButton staffSideBtn;
    private JButton warehouseSideBtn;
    private JButton borrowHistorySideBtn;
    private JPanel dumpSpacee;

    public MainFrame() {
        add(rootPanel);
        setVisible(true);

        scencePanel.setLayout(new GridLayout());

        scencePanel.setBackground(Color.cyan);
        scencePanel.add(scenes[0]);

        this.initButtons();
        this.controllers = this.initControllers();
        this.controllers[0].onAppear();
    }

    private void show(Integer index) {
        this.scencePanel.removeAll();
        this.scencePanel.add(scenes[index]);
        this.controllers[index].onAppear();
        this.scencePanel.updateUI();
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

            if (scene instanceof BorrowHistoryScene) {
                controllers[i] = new BorrowHistoryController(scene);
            }

            if (scene instanceof StaffScene) {
                controllers[i] = new StaffController(scene);
            }

            if (scene instanceof MemberScene) {
                controllers[i] = new MemberController(scene);
            }
        }
        return controllers;
    }


    private void initButtons() {

        JButton[] buttons = {
          bookSideBtn,
          bookTypeSideBtn,
          authorSideBtn,
          categorySideBtn,
          borrowHistorySideBtn,
          warehouseSideBtn,
          memberSideBtn,
          staffSideBtn
        };

        for (var i = 0; i < buttons.length; i++) {
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    show(finalI);
                }
            });
        }
    }
}
