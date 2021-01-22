package data.Repositories;

import data.DAOs.MemberDAO;
import UI.Models.DomainModels.MemberModel;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberRepository extends BaseRepository<MemberModel> {
    public MemberRepository() {
        super(new MemberDAO());
    }

    @Override
    public ArrayList<MemberModel> searchName(String name) throws TransformException, SQLException {
        return modelDAO.selectAll(builder -> {
            var tableName = modelDAO.tableName();
            var query = "LOWER(" + tableName + ".full_name) LIKE" + "'%" + name.toLowerCase() + "%'";
            builder.where(query);
        });
    }
}
