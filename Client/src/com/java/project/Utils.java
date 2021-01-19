package com.java.project;

import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.function.Consumer;

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

    public static void showError() {
        Utils.ShowMessageBox("Something wrong.", "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createPopup(ArrayList<InfoEntry> infos, Consumer<ArrayList<Object>> finishHandler) {
        var panel = new JPanel(new GridLayout(0, 1));
        var uis = new ArrayList<Object>();

        for (var info: infos) {
            if (info.getCls() == String.class) {
                panel.add(new JLabel(info.getLabel()));
                var textField = new JTextField("");
                panel.add(textField);
                uis.add(textField);
            }
            if (info.getCls() == int.class || info.getCls() == Integer.class) {
                panel.add(new JLabel(info.getLabel()));
                var model = new SpinnerNumberModel();
                model.setMinimum(0);
                model.setMaximum(Integer.MAX_VALUE);
                var numberUI = new JSpinner(model);
                panel.add(numberUI);
                uis.add(numberUI);
            }
            if (info.getCls() == Date.class) {
                panel.add(new JLabel(info.getLabel()));
                UtilDateModel model = new UtilDateModel();
                JDatePanel datePanel = new JDatePanel(model);
                JDatePicker datePicker = new JDatePicker(model);
                uis.add(datePicker);
            }
        }



        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Add book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        var res = new ArrayList<Object>();
        for (int i = 0; i < uis.size(); i++) {
            var cls = infos.get(i).getCls();
            if (cls == String.class) {
                var ui = uis.get(0);
                var r = ((JTextField)ui).getText();
                res.add(r);
            }
            if (cls == int.class || cls == Integer.class) {
                var ui = uis.get(0);
                var r = ((JSpinner)ui).getValue();
                res.add(r);
            }
            if (cls == Date.class) {
                var ui = uis.get(0);
                var r = ((JDatePicker)ui).getModel().getValue(); // return Date
                res.add(r);
            }
        }

        if (result == JOptionPane.OK_OPTION) {
            finishHandler.accept(res);
        }
    }


    public static void updatePopup(ArrayList<InfoEntry> infos, Consumer<ArrayList<Object>> finishHandler) {
        var panel = new JPanel(new GridLayout(0, 1));
        var uis = new ArrayList<Object>();

        for (var info: infos) {
            var cls = info.getValue().getClass();
            if (cls == String.class) {
                panel.add(new JLabel(info.getLabel()));
                var textField = new JTextField("");
                textField.setText((String) info.getValue());
                panel.add(textField);
                uis.add(textField);
            }
            if (cls == Integer.class) {
                panel.add(new JLabel(info.getLabel()));
                var model = new SpinnerNumberModel();
                model.setMinimum(0);
                model.setMaximum(Integer.MAX_VALUE);
                var numberUI = new JSpinner(model);
                panel.add(numberUI);
                numberUI.setValue(info.getValue());
                uis.add(numberUI);
            }
            if (cls == Date.class) {
                panel.add(new JLabel(info.getLabel()));
                UtilDateModel model = new UtilDateModel();
                JDatePanel datePanel = new JDatePanel(model);
                JDatePicker datePicker = new JDatePicker(model);
                model.setValue((java.util.Date) info.getValue());
                uis.add(datePicker);
            }
        }



        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Add book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        var res = new ArrayList<Object>();
        for (int i = 0; i < uis.size(); i++) {
            var cls = infos.get(i).getCls();
            if (cls == String.class) {
                var ui = uis.get(0);
                var r = ((JTextField)ui).getText();
                res.add(r);
            }
            if (cls == int.class || cls == Integer.class) {
                var ui = uis.get(0);
                var r = ((JSpinner)ui).getValue();
                res.add(r);
            }
            if (cls == Date.class) {
                var ui = uis.get(0);
                var r = ((JDatePicker)ui).getModel().getValue(); // return Date
                res.add(r);
            }
        }

        if (result == JOptionPane.OK_OPTION) {
            finishHandler.accept(res);
        }
    }



}

