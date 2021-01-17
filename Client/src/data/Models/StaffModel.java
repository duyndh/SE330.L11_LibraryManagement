package data.Models;

import utils.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "staff")
public class StaffModel extends BaseModel {

    public StaffModel() {
    }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "full_name")
    private String fulName;

    @TableModel.Column(columnName = "phone")
    private String phone;

    @TableModel.Column(columnName = "email")
    private String email;

    @TableModel.Column(columnName = "gender")
    private String gender;

    @TableModel.Column(columnName = "created_at")
    private Date createdAt;

    @TableModel.Column(columnName = "updated_at")
    private Date updatedAt;

    @TableModel.Column(columnName = "deleted_at")
    private Date deletedAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFulName() {
        return fulName;
    }

    public void setFulName(String fulName) {
        this.fulName = fulName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
