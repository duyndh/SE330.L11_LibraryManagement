package data.Repositories;

import data.DAOs.StaffDAO;
import data.Models.StaffModel;

public class StaffRepository extends BaseRepository<StaffModel> {
    public StaffRepository() {
        super(new StaffDAO());
    }
}
