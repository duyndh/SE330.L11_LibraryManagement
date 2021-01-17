package data.Models;

import utils.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "staff")
public class StaffModel extends BaseModel {

    public StaffModel() {
    }

    @TableModel.Column(columnName = "id")
    public int id;

    @TableModel.Column(columnName = "full_name")
    public String fulName;

    @TableModel.Column(columnName = "phone")
    public String phone;

    @TableModel.Column(columnName = "email")
    public String email;

    @TableModel.Column(columnName = "gender")
    public String gender;

    @TableModel.Column(columnName = "created_at")
    public Date createdAt;

    @TableModel.Column(columnName = "updated_at")
    public Date updatedAt;

    @TableModel.Column(columnName = "deleted_at")
    public Date deletedAt;
}
