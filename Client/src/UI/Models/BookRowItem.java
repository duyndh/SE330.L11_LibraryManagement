package UI.Models;

import UI.Models.BookItemModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

public class BookRowItem extends TableViewRowItem {
    @TableViewRowItemColumn(columnName = "ID")
    private int id = 0;

    @TableViewRowItemColumn(columnName = "Name")
    private String name = null;

    @TableViewRowItemColumn(columnName = "Author")
    private String author = null;

    @TableViewRowItemColumn(columnName = "Category")
    private String category = null;

    public BookRowItem(BookItemModel bookItem) {
        this.id = bookItem.getBook().getId();
        this.name = bookItem.getBook().getName();
        this.author = bookItem.getBook().getAuthor().getName();
        this.category = bookItem.getBook().getCategory().getName();
    }

    public BookRowItem() {

    }
}
