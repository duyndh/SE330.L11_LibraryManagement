package com.java.panels;

import com.java.project.Utils;

import javax.swing.*;

public class Content {
    private JPanel _contentPanel;
    private JPanel _headerPanel;
    private JPanel _dataPanel;

    public JPanel get_headerPanel() { return _headerPanel; }
    public JPanel get_dataPanel() { return _dataPanel; }
    public JPanel get_contentPanel() { return _contentPanel; }

    public Content() {
        Utils.log("Content()");

        //_contentPanel.setBackground(Color.CYAN);
    }
}
