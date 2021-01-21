package UI.Models.DomainModels;

import utils.DB.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "member")
public class MemberModel extends BaseModel {

    public MemberModel() {
    }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "full_name")
    private String fullName;

    @TableModel.Column(columnName = "phone")
    private String phone;

    @TableModel.Column(columnName = "email")
    private String email;

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    @TableModel.Column(columnName = "created_at")
    private Date createdAt;

    @TableModel.Column(columnName = "expired_at")
    private Date expiredAt;

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