package UI.Models.TableViewItemModel;

import UI.Models.WarehouseHistoryModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

import java.util.Date;

public class WarehouseHistoryRowItem extends TableViewRowItem {


    @TableViewRowItemColumn(columnName = "ID")
    private int id;

    public int getId() {
        return id;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public WarehouseHistoryModel getModel() {
        return model;
    }

    @TableViewRowItemColumn(columnName = "Staff ID")
    private int staffId;

    @TableViewRowItemColumn(columnName = "Book Item ID")
    private int bookItemId;

    @TableViewRowItemColumn(columnName = "Created At")
    private Date createdAt;


    private WarehouseHistoryModel model;

    public WarehouseHistoryRowItem(WarehouseHistoryModel model) {
        this.id = model.getId();
        this.staffId = model.getStaffId();
        this.bookItemId = model.getBookItemId();
        this.createdAt = model.getCreatedAt();
        this.model = model;
    }

    public WarehouseHistoryRowItem() {

    }
}
