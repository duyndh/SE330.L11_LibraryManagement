package UI.Models;

import utils.DB.TableModel;


@TableModel.Table(tableName="category")
public class CategoryModel extends BaseModel {

    public CategoryModel() { }

    @TableModel.Column
    private int id;

    @TableModel.Column
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
