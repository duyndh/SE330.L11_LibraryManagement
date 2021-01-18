package UI.Views;

import UIComponents.TableView.TableView;
import UIComponents.TableView.TableViewDelegate;

import javax.swing.*;
import java.awt.*;

public class BooksScene extends BaseScene {
    protected JPanel rootPanel;
    protected JPanel tablePanel;
    protected JPanel headerPanel;
    public TableView tableView = new TableView();

    public void reloadTableData() {
        this.tableView.reloadData();
    }

    public void setTableViewDelegate(TableViewDelegate delegate) {
        this.tableView.setDelegate(delegate);
    }

    public BooksScene() {
        add(rootPanel);
        tablePanel.setLayout(new CardLayout());
        tablePanel.add(tableView);
    }
}