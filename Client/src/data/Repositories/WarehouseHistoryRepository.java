package data.Repositories;

import data.DAOs.WarehouseHistoryDAO;
import UI.Models.DomainModels.WarehouseHistoryModel;

public class WarehouseHistoryRepository extends BaseRepository<WarehouseHistoryModel> {
    public WarehouseHistoryRepository() {
        super(new WarehouseHistoryDAO());
    }
}
