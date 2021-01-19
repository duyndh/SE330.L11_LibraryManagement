package UI.Views;

import UIComponents.TableView.TableView;
import UIComponents.TableView.TableViewDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseScene extends JPanel {
    protected JPanel rootPanel;
    protected JPanel tablePanel;
    protected JPanel headerPanel;
    private JPanel bottomPanel;
    public JButton addButton;
    public JButton updateButton;
    public JButton deleteButton;
    private JPanel searchPanel;
    public JTextField searchTextField;
    public JButton searchButton;
    public JButton clearButton;
    public TableView tableView = new TableView();

    public void reloadTableData() {
        this.tableView.reloadData();
    }

    public void setTableViewDelegate(TableViewDelegate delegate) {
        this.tableView.setDelegate(delegate);
    }

    public BaseScene() {
        add(rootPanel);
        tablePanel.setLayout(new CardLayout());
        tablePanel.add(tableView);
    }
}