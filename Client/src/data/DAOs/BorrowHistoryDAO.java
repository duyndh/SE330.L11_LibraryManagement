package data.DAOs;

import data.Models.BorrowHistoryModel;

public class BorrowHistoryDAO extends BaseDAO<BorrowHistoryModel> {
    public BorrowHistoryDAO() {
        super(BorrowHistoryModel.class);
    }
}
