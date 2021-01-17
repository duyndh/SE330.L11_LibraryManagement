package com.java.panels;

import com.java.project.Utils;
import com.java.scenes.ActivitiesScene;
import com.java.scenes.BooksScene;
import com.java.scenes.DashboardScene;
import com.java.scenes.ReadersScene;

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
    private JButton[] _buttons = new JButton[] { _dashboardButton, _booksButton, _activitiesButton, _readersButton };

    private DashboardScene _dashboardScene = new DashboardScene();
    private BooksScene _booksScene = new BooksScene();
    private ActivitiesScene _activitiesScene = new ActivitiesScene();
    private ReadersScene _readersScene = new ReadersScene();

    public Home() {

        Utils.Log("Home()");

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

        Utils.Log("Load " + sceneEnum.name());

        _buttons[_currentScene.toInt()].setBackground(_navPanel.getBackground());
        _currentScene = sceneEnum;
        _buttons[_currentScene.toInt()].setBackground(Color.CYAN);

        switch (sceneEnum)
        {
            case DASHBOARD:
                Utils.setChildPanel(_contentPanel, _dashboardScene.get_content().get_contentPanel());
                break;
            case BOOKS:
                Utils.setChildPanel(_contentPanel, _booksScene.get_content().get_contentPanel());
                _booksScene.loadData();
                break;
            case ACTIVITIES:
                Utils.setChildPanel(_contentPanel, _activitiesScene.get_content().get_contentPanel());
                break;
            case READERS:
                Utils.setChildPanel(_contentPanel, _readersScene.get_content().get_contentPanel());
                _readersScene.loadData();
                break;
        }

        _contentPanel.updateUI();
    }
}
