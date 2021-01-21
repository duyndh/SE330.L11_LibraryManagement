package UI.Models.TableViewItemModel;

import UI.Models.DomainModels.BookModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

public class BookRowItem extends TableViewRowItem {

    public BookModel getModel() {
        return model;
    }

    private BookModel model;

    @Override
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

    public BookRowItem(BookModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.author = model.getAuthor().getName();
        this.category = model.getCategory().getName();
        this.model = model;
    }

    public BookRowItem() {

    }
}
