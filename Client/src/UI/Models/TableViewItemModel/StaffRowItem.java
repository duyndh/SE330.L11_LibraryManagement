package UI.Models.TableViewItemModel;
import UI.Models.DomainModels.StaffModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

import java.util.Date;

public class StaffRowItem extends TableViewRowItem {

    public StaffModel getModel() {
        return model;
    }

    public StaffModel model;

    @TableViewRowItemColumn(columnName = "ID")
    private int id = 0;

    @TableViewRowItemColumn(columnName = "FullName")
    private String fullName = null;

    @TableViewRowItemColumn(columnName = "Phone")
    private String phone = null;

    @TableViewRowItemColumn(columnName = "Email")
    private String email = null;

    @Override
    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @TableViewRowItemColumn(columnName = "Created At")
    private Date createdAt = null;

    public StaffRowItem(StaffModel model) {
        this.id = model.getId();
        this.fullName = model.getFullName();
        this.phone = model.getPhone();
        this.email = model.getEmail();
        this.createdAt = model.getCreatedAt();
        this.model = model;
    }

    public StaffRowItem() {

    }
}
