package com.java.panels;

import com.java.project.Utils;

import javax.swing.*;
import java.awt.*;

public class Data {
    private JPanel _dataPanel;
    private JTable _dataTable;
    private JScrollPane _scrollPane;

    public JPanel get_dataPanel() { return _dataPanel; }
    public JTable get_dataTable() { return _dataTable; }

    public Data() {
        Utils.Log("Data()");

        //_dataTable.setBackground(Color.PINK);
    }
}
