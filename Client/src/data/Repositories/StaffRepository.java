package data.Repositories;

import data.DAOs.StaffDAO;
import UI.Models.StaffModel;

public class StaffRepository extends BaseRepository<StaffModel> {
    public StaffRepository() {
        super(new StaffDAO());
    }
}
