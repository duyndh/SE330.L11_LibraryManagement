package com.java.panels;

import com.java.project.Utils;
import com.java.scenes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {
    private JPanel _homePanel;
    private JPanel _navPanel;
    private JPanel _contentPanel;
    private JButton _dashboardButton;
    private JButton _booksButton;
    private JButton _activitiesButton;
    private JButton _readersButton;
    private JSplitPane _splitPane;

    public JPanel get_homePanel() { return _homePanel; }

    public enum SceneEnum {
        DASHBOARD(0),
        BOOKS(1),
        ACTIVITIES(2),
        READERS(3);

        int _sceneId;

        SceneEnum(int sceneId) {
            _sceneId = sceneId;
        }

        int toInt() {
            return _sceneId;
        }
    };

    private SceneEnum _currentScene = SceneEnum.BOOKS;
    private JButton[] _buttons = new JButton[] {
            _dashboardButton,
            _booksButton,
            _activitiesButton,
            _readersButton
    };

    private AbstractScene[] _scenes = new AbstractScene[] {
            new DashboardScene(),
            new BooksScene(),
            new ActivitiesScene(),
            new ReadersScene()
    };
    //public AbstractScene get_scene(SceneEnum sceneEnum) { return _scenes[sceneEnum.toInt()]; }
    public AbstractScene get_current_scene() { return _scenes[_currentScene.toInt()]; }

    public Home() {
        Utils.log("Home()");

        _dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadScene(SceneEnum.DASHBOARD); }
        });
        _booksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadScene(SceneEnum.BOOKS);
            }
        });
        _activitiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadScene(SceneEnum.ACTIVITIES);
            }
        });
        _readersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadScene(SceneEnum.READERS);
            }
        });

        loadScene(_currentScene);
    }

    public void loadScene(SceneEnum sceneEnum) {

        Utils.log("Load " + sceneEnum.name());

        _buttons[_currentScene.toInt()].setBackground(_navPanel.getBackground());
        _currentScene = sceneEnum;
        _buttons[_currentScene.toInt()].setBackground(Color.CYAN);

        Utils.setChildPanel(_contentPanel, _scenes[sceneEnum.toInt()].get_content().get_contentPanel());
        _scenes[sceneEnum.toInt()].loadData();

        _contentPanel.updateUI();
    }
}
