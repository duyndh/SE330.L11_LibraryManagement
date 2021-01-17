package data.DAOs;

import data.Models.StaffModel;

public class StaffDAO extends BaseDAO<StaffModel> {
    public StaffDAO() {
        super(StaffModel.class);
    }
}
