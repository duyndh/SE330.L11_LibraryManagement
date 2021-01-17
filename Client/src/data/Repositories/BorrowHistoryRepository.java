package data.Repositories;

import data.DAOs.BorrowHistoryDAO;
import data.Models.BorrowHistoryModel;

public class BorrowHistoryRepository extends BaseRepository<BorrowHistoryModel> {
    public BorrowHistoryRepository() {
        super(new BorrowHistoryDAO());
    }
}
