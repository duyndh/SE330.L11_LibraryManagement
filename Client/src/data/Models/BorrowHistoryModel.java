package data.Models;

import utils.TableModel;

import java.util.Date;


@TableModel.Table(tableName="borrow_history")
public class BorrowHistoryModel extends BaseModel {

    public BorrowHistoryModel() { }

    @TableModel.Column(columnName="id")
    public int id;

    @TableModel.Column(columnName="member_id")
    public int memberId;

    @TableModel.NestedModel(refColumn = "member_id")
    public MemberModel member;

    @TableModel.Column(columnName="staff_id")
    public int staffId;

    @TableModel.NestedModel(refColumn = "staff_id")
    public StaffModel staff;

    @TableModel.Column(columnName="book_item_id")
    public int bookItemId;

    @TableModel.NestedModel(refColumn = "book_item_id")
    public BookItemModel bookItem;

    @TableModel.Column(columnName="borrowed_at")
    public Date borrowedAt;

    @TableModel.Column(columnName="returned_at")
    public Date returnedAt;
}

