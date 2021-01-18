package UI.Controllers;

import UI.Models.BookRowItem;
import UI.Views.BooksScene;
import UIComponents.TableView.TableViewDelegate;
import data.Repositories.BookItemRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BooksControlller implements Controller, TableViewDelegate<BookRowItem> {

    private final BooksScene booksScene;
    private final BookItemRepository bookItemRepository = new BookItemRepository();
    private final ArrayList<BookRowItem> bookRowItems = new ArrayList();

    public BooksControlller(BooksScene booksScene) {
        this.booksScene = booksScene;
        this.booksScene.setTableViewDelegate(this);
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
        this.bookRowItems.addAll(this.bookItemRepository
                .getAll()
                .stream()
                .map(BookRowItem::new)
                .collect(Collectors.toList()));


        if (bookRowItems.size() == 0) {
            var item = new BookRowItem();
            bookRowItems.add(item);
        }

        this.booksScene.reloadTableData();
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
}
