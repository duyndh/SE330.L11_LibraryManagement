package UI.Models;

import UI.Models.BookItemModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

public class BookRowItem extends TableViewRowItem {
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    @TableViewRowItemColumn(columnName = "ID")
    private int id = 0;

    @TableViewRowItemColumn(columnName = "Name")
    private String name = null;

    @TableViewRowItemColumn(columnName = "Author")
    private String author = null;

    @TableViewRowItemColumn(columnName = "Category")
    private String category = null;

    private BookItemModel model;

    public BookItemModel getModel() { return model; }

    public BookRowItem(BookItemModel bookItem) {
        this.id = bookItem.getId();
        this.name = bookItem.getBook().getName();
        this.author = bookItem.getBook().getAuthor().getName();
        this.category = bookItem.getBook().getCategory().getName();
        this.model = bookItem;
    }

    public BookRowItem() {

    }
}
