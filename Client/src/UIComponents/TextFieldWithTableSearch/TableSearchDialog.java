package UIComponents.TextFieldWithTableSearch;

import UI.Models.DomainModels.BookModel;
import UI.Models.TableViewItemModel.AuthorRowItem;
import UI.Models.TableViewItemModel.BookItemRowItem;
import UIComponents.TableView.TableView;
import UIComponents.TableView.TableViewDelegate;
import UIComponents.TableView.TableViewRowItem;
import data.Repositories.AuthorRepository;
import data.Repositories.BaseRepository;
import data.Repositories.BookItemRepository;
import utils.DB.TransformException;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TableSearchDialog<T extends TableViewRowItem> extends JDialog implements TableViewDelegate<T> {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel tablePane;
    private JButton searchBtn;
    private JTextField textField;
    private BaseRepository repo;

    private TableView table = new TableView();
    private Class<? extends TableViewRowItem> itemCls;
    private ArrayList<T> items = new ArrayList<>();

    public TableSearchDialog(BaseRepository repo, Class<? extends TableViewRowItem> itemCls) {

        this.itemCls = itemCls;
        this.repo = repo;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearchButtonTapped();
            }
        });


        try {

            var args = new Class[] { repo.getBackingModelClz() };
            var constructor = this.itemCls.getDeclaredConstructor(args);

            repo.getAll().stream().forEach(o -> {
                try {
                    var obj = constructor.newInstance(o);
                    this.items.add((T) obj);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });

        } catch (TransformException | SQLException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        tablePane.add(table);
        table.setDelegate(this);
        table.reloadData();
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public int showDialog() {
        setVisible(true);
        if (table.getTable().getSelectedRow() != -1) {
            var o = this.items.get(table.getTable().getSelectedRow());
            return o.getId();
        }
        return 0;
    }

    private void onSearchButtonTapped() {
        try {
            var args = new Class[] { repo.getBackingModelClz() };
            var constructor = this.itemCls.getDeclaredConstructor(args);
            this.items.clear();
            repo.searchName(this.textField.getText().trim())
                    .stream()
                    .forEach(o -> {
                        try {
                            var obj = constructor.newInstance(o);
                            this.items.add((T) obj);
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (TransformException | SQLException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        this.table.reloadData();
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public Class<?> rowItemClass() {
        return this.itemCls;
    }

    @Override
    public T itemAt(int row) {
        return items.get(row);
    }

    @Override
    public void tableViewDidSelectRow(int[] row) {
        System.out.println();
    }
}
