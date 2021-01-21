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
    protected JPanel bottomPanel;
    protected JButton addButton;
    protected JButton updateButton;
    protected JButton deleteButton;
    protected JPanel searchPanel;
    protected JTextField searchTextField;
    protected JButton searchButton;
    protected JButton clearButton;
    private JPanel moreButtonPanel;

    public JLabel getTitleLb() {
        return titleLb;
    }

    public void setTitleLb(JLabel titleLb) {
        this.titleLb = titleLb;
    }

    private JLabel titleLb;

    public JPanel getBelowSearchPanel() {
        return belowSearchPanel;
    }

    private JPanel belowSearchPanel;
    protected TableView tableView = new TableView();

    public void reloadTableData() {
        this.tableView.reloadData();
    }

    public void setTableViewDelegate(TableViewDelegate delegate) {
        this.tableView.setDelegate(delegate);
    }

    public BaseScene() {
        add(rootPanel);
        tablePanel.setLayout(new GridLayout());
        tablePanel.add(tableView);
        moreButtonPanel.setLayout(new GridLayout(0, 1, 10, 10));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JPanel getTablePanel() {
        return tablePanel;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JPanel getSearchPanel() {
        return searchPanel;
    }

    public JTextField getSearchTextField() {
        return searchTextField;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public TableView getTableView() {
        return tableView;
    }

    public JPanel getMoreButtonPanel() {
        return moreButtonPanel;
    }

    public void setMoreButtonPanel(JPanel moreButtonPanel) {
        this.moreButtonPanel = moreButtonPanel;
    }

}