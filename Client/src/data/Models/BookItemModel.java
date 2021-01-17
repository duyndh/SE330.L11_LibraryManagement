package data.Models;

import utils.TableModel;

@TableModel.Table(tableName = "book_item")
public class BookItemModel extends BaseModel {

    public BookItemModel() { }

    @TableModel.Column(columnName = "id")
    public int id;

    @TableModel.Column(columnName = "book_id")
    public int book_id;

    @TableModel.NestedModel(refColumn = "book_id")
    public BookModel book;
}
