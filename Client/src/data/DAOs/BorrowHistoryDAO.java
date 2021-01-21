package data.DAOs;

import UI.Models.DomainModels.BorrowHistoryModel;

public class BorrowHistoryDAO extends BaseDAO<BorrowHistoryModel> {
    public BorrowHistoryDAO() {
        super(BorrowHistoryModel.class);
    }
}
