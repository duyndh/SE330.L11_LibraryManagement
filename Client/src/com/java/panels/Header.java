package com.java.panels;

import com.java.project.Main;
import com.java.project.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Header {
    private JPanel _headerPanel;
    private JButton _addButton;
    private JLabel _titleLabel;
    private JTextField _searchTextField;
    private JButton _searchButton;
    private JPanel _titlePanel;
    private JPanel _searchPanel;

    public JPanel get_headerPanel() { return _headerPanel; }

    private Home.SceneEnum _sceneEnum;

    public Header(String name, Home.SceneEnum sceneEnum) {
        Utils.log("Header()");
        _titleLabel.setText(name);
        _sceneEnum = sceneEnum;

        //_headerPanel.setBackground(Color.ORANGE);
        _addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.get_home().get_current_scene().displayCreationPopup();
            }
        });
    }
}
