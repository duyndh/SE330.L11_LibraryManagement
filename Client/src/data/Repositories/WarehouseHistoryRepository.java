package data.Repositories;

import data.DAOs.WarehouseHistoryDAO;
import data.Models.WarehouseHistoryModel;

public class WarehouseHistoryRepository extends BaseRepository<WarehouseHistoryModel> {
    public WarehouseHistoryRepository() {
        super(new WarehouseHistoryDAO());
    }
}
