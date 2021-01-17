package data.Models;

import utils.TableModel;

@TableModel.Table(tableName = "book")
public class BookModel extends BaseModel {

    public BookModel() { }

    @TableModel.Column(columnName="id")
    public int id;

    @TableModel.Column(columnName="name")
    public String name;

    @TableModel.Column(columnName="author_id")
    public int authorId;

    @TableModel.NestedModel(refColumn = "author_id")
    public AuthorModel author;

    @TableModel.Column(columnName="category_id")
    public int categoryId;

    @TableModel.NestedModel(refColumn = "category_id")
    public CategoryModel category;
}

