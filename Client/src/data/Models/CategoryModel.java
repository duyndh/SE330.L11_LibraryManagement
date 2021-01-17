package data.Models;

import utils.TableModel;


@TableModel.Table(tableName="category")
public class CategoryModel extends BaseModel {

    public CategoryModel() { }

    @TableModel.Column
    public int id;

    @TableModel.Column
    public String name;
}
