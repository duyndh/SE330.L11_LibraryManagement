package data.Models;

import utils.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "warehouse_history")
public class WarehouseHistoryModel extends BaseModel {

    public WarehouseHistoryModel() {
    }

    @TableModel.Column(columnName = "id")
    public int id;

    @TableModel.Column(columnName = "staff_id")
    public int staffId;

    @TableModel.NestedModel(refColumn = "staff_id")
    public StaffModel staff;

    @TableModel.Column(columnName = "book_item_id")
    public int bookItemId;

    @TableModel.NestedModel(refColumn = "book_item_id")
    public BookItemModel bookItem;

    @TableModel.Column(columnName = "borrowed_at")
    public Date borrowedAt;

    @TableModel.Column(columnName = "returned_at")
    public Date returnedAt;
}
