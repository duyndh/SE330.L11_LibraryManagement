package UI.Models.DomainModels;

import utils.DB.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "staff")
public class StaffModel extends BaseModel {

    public StaffModel() {
    }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "full_name")
    private String fullName;

    @TableModel.Column(columnName = "phone")
    private String phone;

    @TableModel.Column(columnName = "email")
    private String email;

    public String getPassword() {
        return password;
    }

    @TableModel.Column(columnName = "password")
    private String password;

    @TableModel.Column(columnName = "created_at")
    private Date createdAt = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPassword(String password) {
        this.password = password;
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


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}