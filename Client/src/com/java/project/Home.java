package com.java.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Home {

    private JPanel panelMain;
    private JButton dashboardButton;
    private JButton booksButton;
    private JButton activitiesButton;
    private JButton usersButton;
    private JPanel searchPanel;
    private JPanel navPanel;
    private JPanel contentPanel;
    private JPanel titlePanel;
    private JPanel dataPanel;
    private JButton addBookButton;
    private JButton searchBookButton;
    private JTextField searchBookTextField;
    private JTable booksDataTable;
    private JLabel booksLabel;
    private JScrollPane booksDataScrollPane;

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

//    public static void main(String[] args) {
//
//        var home = new Home();
//        home.BindBooksData(home.booksDataTable);
//
//        var frame = new JFrame("Library Management");
//        frame.setContentPane(home.panelMain);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setSize(1366, 768);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
}
