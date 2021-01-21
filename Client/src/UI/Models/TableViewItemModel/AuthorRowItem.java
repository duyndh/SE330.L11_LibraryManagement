package UI.Models.TableViewItemModel;

import UI.Models.DomainModels.AuthorModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

public class AuthorRowItem extends TableViewRowItem {

    public AuthorModel getModel() {
        return model;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AuthorModel model;

    @TableViewRowItemColumn(columnName = "ID")
    private int id = 0;

    @TableViewRowItemColumn(columnName = "Author Name")
    private String name = null;

    public AuthorRowItem(AuthorModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.model = model;
    }

    public AuthorRowItem() {

    }
}
