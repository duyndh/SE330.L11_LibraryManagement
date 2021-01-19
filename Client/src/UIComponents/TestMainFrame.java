package UIComponents;

import UI.Views.BooksScene;
import UIComponents.TableView.TableView;
import UIComponents.TableView.TableViewDelegate;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

import javax.swing.*;
import java.awt.*;

public class TestMainFrame extends Frame {
    private JPanel rootPanel;
    private JPanel displayPanel;
    private TableView tableView = new TableView();

    public TestMainFrame() {
        add(rootPanel);
        displayPanel.setBackground(Color.cyan);

        var s = new BooksScene();
        s.setBackground(Color.ORANGE);
        s.tableView.setDelegate(new TableViewTestDelegate());
        s.tableView.reloadData();
        displayPanel.add(s);
        displayPanel.updateUI();

//        this.tableView.setDelegate(new TableViewTestDelegate());
//        this.tableView.reloadData();
    }
}


class TestItem extends TableViewRowItem {
    @TableViewRowItemColumn(columnName = "This is ID")
    public int id;
    @TableViewRowItemColumn(columnName = "This is Name")
    public String name;
}


class TableViewTestDelegate implements TableViewDelegate<TestItem> {

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public Class<?> rowItemClass() {
        return TestItem.class;
    }

    @Override
    public TestItem itemAt(int row) {
        var item = new TestItem();
        item.id = 10;
        item.name = "Vinh";
        return item;
    }

    @Override
    public void tableViewDidSelectRow(int row) {

    }
}