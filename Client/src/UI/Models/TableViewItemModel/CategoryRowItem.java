package UI.Models.TableViewItemModel;

import UI.Models.CategoryModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

public class CategoryRowItem extends TableViewRowItem {

    public CategoryModel getModel() {
        return model;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryModel model;

    @TableViewRowItemColumn(columnName = "ID")
    private int id = 0;

    @TableViewRowItemColumn(columnName = "Category Name")
    private String name = null;

    public CategoryRowItem(CategoryModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.model = model;
    }

    public CategoryRowItem() {

    }
}
