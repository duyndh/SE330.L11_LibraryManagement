package UI.Controllers;

import UI.Models.BookItemModel;
import UI.Models.BookRowItem;
import UI.Views.BaseScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.BookItemRepository;
import jdk.jshell.execution.Util;
import utils.DB.TransformException;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BooksControlller extends BaseController implements TableViewDelegate<BookRowItem> {

    // private final BooksScene booksScene;
    private final BookItemRepository bookItemRepository = new BookItemRepository();
    private final ArrayList<BookRowItem> bookRowItems = new ArrayList();
    private BookRowItem selectedObject = null;

    public BooksControlller(BaseScene scene) {
        super(scene);
        this.scene.setTableViewDelegate(this);
    }


    @Override
    public void onAppear() {
        this.reloadData();
    }

    @Override
    public void onDisappear() {
        // Empty
    }

    @Override
    public void reloadData() {
        this.bookRowItems.clear();
        try {
            this.bookRowItems.addAll(this.bookItemRepository
                    .getAll()
                    .stream()
                    .map(BookRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (bookRowItems.size() == 0) {
            var item = new BookRowItem();
            bookRowItems.add(item);
        }

        this.scene.reloadTableData();
    }


    @Override
    void onCreateTapped() {
        InfoEntry[] infos = {
                new InfoEntry("BOOK ID", int.class),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var item = new BookItemModel();
            item.setBook_id((int)res.get(0));
            try {
                this.bookItemRepository.create(item);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError();
            }
        });

        reloadData();
    }

    @Override
    void onUpdatedTapped() {
        var o = this.selectedObject.getModel();
        InfoEntry[] infos = {
                new InfoEntry("BOOK ID", o.getBook_id()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            var item = new BookItemModel();
            item.setBook_id((int)res.get(0));
            try {
                this.bookItemRepository.update(item);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError();
            }
        });

        reloadData();
    }

    @Override
    void onDeleteTapped() {
        var id = this.selectedObject.getId();
        try {
            this.bookItemRepository.delete(id);
        } catch (SQLException throwables) {
            Utils.showError();
        }
        this.reloadData();
    }


    //
    // Implement table delegates
    //
    @Override
    public int getRowCount() {
        return this.bookRowItems.size();
    }

    @Override
    public Class<?> rowItemClass() {
        return BookRowItem.class;
    }

    @Override
    public BookRowItem itemAt(int row) {
        return this.bookRowItems.get(row);
    }

    @Override
    public void tableViewDidSelectRow(int row) {
        if (this.bookRowItems.size() > 0 && row > -1) {
            this.selectedObject = this.bookRowItems.get(row);
        }
    }

    @Override
    void onSearchButtonTapped(String searchText) {
        this.bookRowItems.clear();
        try {
            this.bookRowItems.addAll(this.bookItemRepository
                    .searchName(searchText)
                    .stream()
                    .map(BookRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (bookRowItems.size() == 0) {
            var item = new BookRowItem();
            bookRowItems.add(item);
        }

        this.scene.reloadTableData();
    }

    @Override
    void onClearButtonTapped() {
        this.reloadData();
    }
}