package com.java.panels;

import com.java.project.Utils;

import javax.swing.*;
import java.awt.*;

public class Header {
    private JPanel _headerPanel;
    private JButton _addButton;
    private JLabel _titleLabel;
    private JTextField _searchTextField;
    private JButton _searchButton;
    private JPanel _titlePanel;
    private JPanel _searchPanel;

    public JPanel get_headerPanel() { return _headerPanel; }

    public Header(String title) {
        Utils.Log("Header()");
        _titleLabel.setText(title);

        //_headerPanel.setBackground(Color.ORANGE);
    }
}
