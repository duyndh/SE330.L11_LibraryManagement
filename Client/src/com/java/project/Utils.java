package com.java.project;

import UI.Models.DomainModels.*;
import UI.Models.TableViewItemModel.*;
import UIComponents.TextFieldWithTableSearch.TextFieldWithTableSearch;
import data.Repositories.*;
import org.jdatepicker.JDatePanel;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
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

    public static void showInfo(String msg, String title) {
        Utils.ShowMessageBox(msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showError(String e) {
        Utils.ShowMessageBox(e, "Error", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void createPopup(ArrayList<InfoEntry> infos, Consumer<ArrayList<Object>> finishHandler) {
        var panel = new JPanel(new GridLayout(0, 1));
        var uis = new ArrayList<Object>();

        for (var info: infos) {
            var cls = info.getCls();

            if (cls == String.class) {
                panel.add(new JLabel(info.getLabel()));
                var textField = new JTextField("");
                panel.add(textField);
                textField.setHorizontalAlignment(SwingUtilities.RIGHT);
                uis.add(textField);
            }
            if (cls == int.class || info.getCls() == Integer.class) {
                panel.add(new JLabel(info.getLabel()));
                var model = new SpinnerNumberModel();
                model.setMinimum(0);
                model.setMaximum(Integer.MAX_VALUE);
                var numberUI = new JSpinner(model);
                panel.add(numberUI);
                uis.add(numberUI);
            }
            if (cls == Date.class) {
                panel.add(new JLabel(info.getLabel()));
                UtilDateModel model = new UtilDateModel();
                JDatePanel datePanel = new JDatePanel(model);
                JDatePicker datePicker = new JDatePicker(model);
                panel.add(datePicker);
                uis.add(datePicker);
            }

            if (BaseModel.class.isAssignableFrom(cls)) {
                panel.add(new JLabel(info.getLabel()));
                var sp = createTextFieldWithSearchTableFrom(cls);

                if (info.getValue() != null) {
                    sp.getBackedTextField().setText(info.getValue().toString());
                }

                panel.add(sp);
                uis.add(sp);
            }
        }



        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "CREATE ITEM",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            var res = new ArrayList<Object>();
            for (int i = 0; i < uis.size(); i++) {
                var cls = infos.get(i).getCls();
                if (cls == String.class) {
                    var ui = uis.get(i);
                    var r = ((JTextField)ui).getText();
                    res.add(r);
                }
                if (cls == int.class || cls == Integer.class) {
                    var ui = uis.get(i);
                    var r = ((JSpinner)ui).getValue();
                    res.add(r);
                }
                if (cls == Date.class) {
                    var ui = uis.get(i);
                    var r = ((JDatePicker)ui).getModel().getValue(); // return Date
                    res.add(r);
                }
                if (BaseModel.class.isAssignableFrom(cls)) {
                    var ui = uis.get(i);
                    var _r = ((TextFieldWithTableSearch)ui).getBackedTextField().getText();
                    var r = Integer.parseInt(_r);
                    res.add(r);
                }

            }
            finishHandler.accept(res);
        }
    }


    public static void updatePopup(ArrayList<InfoEntry> infos, Consumer<ArrayList<Object>> finishHandler) {
        var panel = new JPanel(new GridLayout(0, 1));
        var uis = new ArrayList<Object>();

        for (var info: infos) {

            // Check Models first
            if (BaseModel.class.isAssignableFrom(info.getCls())) {
                panel.add(new JLabel(info.getLabel()));
                var sp = createTextFieldWithSearchTableFrom(info.getCls());
                var idString = info.getValue().toString();
                sp.getBackedTextField().setText(idString);
                panel.add(sp);
                uis.add(sp);
                continue;
            }

            // another types
            var cls = info.getCls();
            if (cls == String.class) {
                panel.add(new JLabel(info.getLabel()));
                var textField = new JTextField("");
                textField.setText((String) info.getValue());
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
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
                panel.add(datePicker);
                uis.add(datePicker);
            }

        }



        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "UPDATE ITEM",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            var res = new ArrayList<Object>();
            for (int i = 0; i < uis.size(); i++) {
                var cls = infos.get(i).getCls();
                if (cls == String.class) {
                    var ui = uis.get(i);
                    var r = ((JTextField)ui).getText();
                    res.add(r);
                }
                if (cls == int.class || cls == Integer.class) {
                    var ui = uis.get(i);
                    var r = ((JSpinner)ui).getValue();
                    res.add(r);
                }
                if (cls == Date.class) {
                    var ui = uis.get(i);
                    var r = ((JDatePicker)ui).getModel().getValue(); // return Date
                    res.add(r);
                }
                if (BaseModel.class.isAssignableFrom(cls)) {
                    var ui = uis.get(i);
                    var _r = ((TextFieldWithTableSearch)ui).getBackedTextField().getText();
                    var r = Integer.parseInt(_r);
                    res.add(r);
                }
            }
            finishHandler.accept(res);
        }
    }


    private static TextFieldWithTableSearch createTextFieldWithSearchTableFrom(Class<?> cls) {
        if (cls == BookModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new BookRepository(), BookRowItem.class);
        }

        if (cls == BookItemModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new BookItemRepository(), BookItemRowItem.class);
        }

        if (cls == CategoryModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new CategoryRepository(), CategoryRowItem.class);
        }

        if (cls == AuthorModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new AuthorRepository(), AuthorRowItem.class);
        }

        if (cls == MemberModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new MemberRepository(), MemberRowItem.class);
        }

        if (cls == StaffModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new StaffRepository(), StaffRowItem.class);
        }

        if (cls == WarehouseHistoryModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new WarehouseHistoryRepository(), WarehouseHistoryRowItem.class);
        }

        if (cls == BorrowHistoryModel.class) {
            return new TextFieldWithTableSearch<BookRowItem>(new BorrowHistoryRepository(), BorrowHistoryRowItem.class);
        }

        return null;
    }

}

