package UI.Models.TableViewItemModel;

import UI.Models.DomainModels.WarehouseHistoryModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

import java.util.Date;

public class WarehouseHistoryRowItem extends TableViewRowItem {


    @TableViewRowItemColumn(columnName = "ID")
    private int id;

    @Override
    public int getId() {
        return id;
    }

    public int getStaffId() {
        return staffId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public WarehouseHistoryModel getModel() {
        return model;
    }

    @TableViewRowItemColumn(columnName = "Staff ID")
    private int staffId;

    @TableViewRowItemColumn(columnName = "Book ID")
    private int bookId;

    @TableViewRowItemColumn(columnName = "Created At")
    private Date createdAt;


    private WarehouseHistoryModel model;

    public WarehouseHistoryRowItem(WarehouseHistoryModel model) {
        this.id = model.getId();
        this.staffId = model.getStaffId();
        this.bookId = model.getBookItemId();
        this.createdAt = model.getCreatedAt();
        this.model = model;
    }

    public WarehouseHistoryRowItem() {

    }
}
