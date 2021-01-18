package com.java.project;

import javax.swing.*;
import java.awt.*;

public class Utils {

    public static void setChildPanel(JPanel parent, JPanel child) {
        Utils.log("<-");

        parent.removeAll();
        parent.setLayout(new CardLayout());
        parent.add(child);
    }

    public static void log(String msg) {
        //System.out.println(msg);
    }

    public static void bindDataToTable(String[] columnNames, Object[][] data, JTable table) {
        table.setModel(new JTable(data, columnNames).getModel());

//        final Class[] columnClass = new Class[] {
//                Integer.class, String.class, Double.class, Boolean.class
//        };
//
//        var model = new DefaultTableModel(data, columns) {
//            @Override
//            public boolean isCellEditable(int row, int column)
//            {
//                return false;
//            }
//            @Override
//            public Class<?> getColumnClass(int columnIndex)
//            {
//                return columnClass[columnIndex];
//            }
//        };
//
//        table.setModel(model);

        //table = new JTable(model);
        //this.add(new JScrollPane(table));
    }

    public static JTextField addLabelAndTextField(JPanel panel, String labelName, String text) {
        panel.add(new JLabel(labelName));
        var textField = new JTextField(text);
        panel.add(textField);

        return textField;
    }

    public static void ShowMessageBox(String message, String title, int type) {
        JOptionPane.showMessageDialog(null, message, title, type);
    }
}
