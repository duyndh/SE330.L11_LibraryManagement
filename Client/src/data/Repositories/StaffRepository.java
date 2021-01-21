package data.Repositories;

import data.DAOs.StaffDAO;
import UI.Models.DomainModels.StaffModel;
import utils.DB.TransformException;

import java.sql.SQLException;

public class StaffRepository extends BaseRepository<StaffModel> {
    public StaffRepository() {
        super(new StaffDAO());
    }

    public boolean login(String username, String pwd) {
        try {
            var res = this.modelDAO.selectAll(staffModelSelectBuilder -> {
                staffModelSelectBuilder.where("staff.email=\""
                        + username
                        + "\" AND staff.password=\""
                        + pwd + "\"");
            });
            return res.size() > 0;
        } catch (SQLException | TransformException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
