package UI.Models.DomainModels;

import utils.DB.TableModel;


@TableModel.Table(tableName="category")
public class CategoryModel extends BaseModel {

    public CategoryModel() { }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
