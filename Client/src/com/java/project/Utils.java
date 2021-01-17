package com.java.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Utils {

    public static void setChildPanel(JPanel parent, JPanel child) {
        Utils.Log("<-");

        parent.removeAll();
        parent.setLayout(new CardLayout());
        parent.add(child);
    }

    public static void Log(String msg) {
        //System.out.println(msg);
    }

    public static void BindDataToTable(String[] columnNames, Object[][] data, JTable table) {
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
}
