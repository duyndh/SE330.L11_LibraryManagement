package UI.Models;

import utils.DB.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "warehouse_history")
public class WarehouseHistoryModel extends BaseModel {

    public WarehouseHistoryModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public StaffModel getStaff() {
        return staff;
    }

    public void setStaff(StaffModel staff) {
        this.staff = staff;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(int bookItemId) {
        this.bookItemId = bookItemId;
    }

    public BookItemModel getBookItem() {
        return bookItem;
    }

    public void setBookItem(BookItemModel bookItem) {
        this.bookItem = bookItem;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "staff_id")
    private int staffId;

    @TableModel.NestedModel(refColumn = "staff_id")
    private StaffModel staff;

    @TableModel.Column(columnName = "book_item_id")
    private int bookItemId;

    @TableModel.NestedModel(refColumn = "book_item_id")
    private BookItemModel bookItem;

    @TableModel.Column(columnName = "created_at")
    private Date createdAt;



}
