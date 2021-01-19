package UI.Models.TableViewItemModel;

import UI.Models.BorrowHistoryModel;
import UIComponents.TableView.TableViewRowItem;
import UIComponents.TableView.TableViewRowItemColumn;

import java.util.Date;

public class BorrowHistoryRowItem extends TableViewRowItem {


    @TableViewRowItemColumn(columnName = "ID")
    private int id;

    @TableViewRowItemColumn(columnName = "Member ID")
    private int memberId;

    @TableViewRowItemColumn(columnName = "Staff ID")
    private int staffId;

    @TableViewRowItemColumn(columnName = "Book Item ID")
    private int bookItemId;

    @TableViewRowItemColumn(columnName = "Member Name")
    private String memberName;

    @TableViewRowItemColumn(columnName = "Book Name")
    private String bookName;

    @TableViewRowItemColumn(columnName = "Borrowed At")
    private Date borrowedAt;

    @TableViewRowItemColumn(columnName = "Returned At")
    private Date returnedAt;

    private BorrowHistoryModel model;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(int bookItemId) {
        this.bookItemId = bookItemId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(Date borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    public BorrowHistoryModel getModel() {
        return model;
    }

    public void setModel(BorrowHistoryModel model) {
        this.model = model;
    }

    public BorrowHistoryRowItem(BorrowHistoryModel model) {
        this.id = model.getId();
        this.memberId = model.getMemberId();
        this.staffId = model.getStaffId();
        this.bookItemId = model.getBookItemId();
        this.memberName = model.getMember().getFullName();
        this.bookName = model.getBookItem().getBook().getName();
        this.borrowedAt = model.getBorrowedAt();
        this.returnedAt = model.getReturnedAt();
        this.model = model;
    }

    public BorrowHistoryRowItem() {

    }
}
