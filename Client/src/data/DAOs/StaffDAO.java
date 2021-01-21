package data.DAOs;

import UI.Models.DomainModels.StaffModel;

public class StaffDAO extends BaseDAO<StaffModel> {
    public StaffDAO() {
        super(StaffModel.class);
    }
}
