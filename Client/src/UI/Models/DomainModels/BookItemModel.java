package UI.Models.DomainModels;

import utils.DB.TableModel;

import java.util.Date;

@TableModel.Table(tableName = "book_item")
public class BookItemModel extends BaseModel {

    public BookItemModel() { }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "book_id")
    private int book_id;

    @TableModel.NestedModel(refColumn = "book_id")
    private BookModel book;

    public BorrowHistoryModel getBorrowHistory() {
        return borrowHistory;
    }

    public void setBorrowHistory(BorrowHistoryModel borrowHistory) {
        this.borrowHistory = borrowHistory;
    }

    private BorrowHistoryModel borrowHistory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public BookModel getBook() {
        return book;
    }

    public void setBook(BookModel book) {
        this.book = book;
    }


    public boolean isAvailable() {
        if (borrowHistory == null) {
            return true;
        }

        if (borrowHistory.getReturnedAt() == null) {
            return false;
        }

        return borrowHistory.getReturnedAt().getTime() < new Date().getTime();
    }

}
