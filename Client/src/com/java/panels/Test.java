package com.java.panels;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class Test {

    private JPanel panelMain;
    private JSplitPane splitPane;
    private JPanel navPanel;
    private JButton dashboardButton;
    private JButton booksButton;
    private JButton activitiesButton;
    private JButton usersButton;
    private JPanel contentPanel;
    private JPanel titlePanel;
    private JButton addBookButton;
    private JLabel booksLabel;
    private JPanel searchPanel;
    private JButton searchBookButton;
    private JTextField searchBookTextField;
    private JPanel dataPanel;

    public Test() {}

    public void BindBooksData(JTable table) {

        var model = new DefaultTableModel(
                new Object[] { "Id", "Title", "Categories", "Authors", "Publisher", "PublicationDate", "Description", "Price" },
                0);

        model.addRow(new Object[] {
                1,
                "Harry Potter Part 1 : Harry Potter And The Philosopher's Stone",
                "Fiction - Literature" ,
                "J. K. Rowling",
                "Bloomsbury",
                new Date(2014, 05, 01),
                "Paperback",
                218900
        });
        model.addRow(new Object[] {
                2,
                "Oxford Collocations Dictionary for Students of English (2nd Edition)",
                "Dictionary",
                "Oxford University Press",
                "Oxford University Press",
                new Date(2009, 05, 01),
                "Pap/Cdr Ne",
                290800
        });

        table.setModel(model);
        //table.setTableHeader();
    }
}
