package com.java.panels;

import com.java.project.Main;
import com.java.project.Utils;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Data {
    private JPanel _dataPanel;
    private JTable _dataTable;
    private JScrollPane _scrollPane;

    public JPanel get_dataPanel() { return _dataPanel; }
    public JTable get_dataTable() { return _dataTable; }

    public Data() {
        Utils.log("Data()");

        //_dataTable.setBackground(Color.PINK);
        _dataTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


                if (SwingUtilities.isRightMouseButton(e)) {
                    //Utils.java.ShowMessageBox("", "", JOptionPane.INFORMATION_MESSAGE);

                    // Select row
                    var row = _dataTable.rowAtPoint(e.getPoint());
                    _dataTable.clearSelection();
                    _dataTable.addRowSelectionInterval(row,row);

                    // Display popup
                    var popup = new JPopupMenu();

                    // Update item
                    var updateMenuItem = new JMenuItem("Update");
                    updateMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            super.mousePressed(e);

                            //Utils.java.ShowMessageBox("Update", "", JOptionPane.INFORMATION_MESSAGE);
                            Main.get_home().get_current_scene().displayUpdationPopup(row);
                        }
                    });
                    popup.add(updateMenuItem);

                    // Delete item
                    var deleteMenuItem = new JMenuItem("Delete");
                    deleteMenuItem.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            super.mousePressed(e);

                            Utils.ShowMessageBox("Delete", "", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                    popup.add(deleteMenuItem);

                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }
}
