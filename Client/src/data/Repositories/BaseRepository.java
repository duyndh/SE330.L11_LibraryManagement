package data.Repositories;

import data.DAOs.BaseDAO;
import UI.Models.BaseModel;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;

abstract class BaseRepository<T extends BaseModel> {
    protected BaseDAO<T> modelDAO;

    public BaseRepository(BaseDAO<T> modelDAO) {
        this.modelDAO = modelDAO;
    }

    public T create(T model) throws TransformException, SQLException {
        return modelDAO.create(model);
    }

    public T read(int id) throws TransformException, SQLException {
        return modelDAO.read(id);
    }

    public T update(T model) throws TransformException, SQLException {
        return modelDAO.update(model);
    }

    public boolean delete(int id) throws SQLException {
        return modelDAO.delete(id);
    }

    public ArrayList<T> getAll() throws TransformException, SQLException {
        return getAll(-1);
    }

    public ArrayList<T> getAll(int limit) throws TransformException, SQLException {
        return modelDAO.selectAll(builder -> {
            builder.limit(limit);
        });
    }

    public ArrayList<T> searchName(String name) throws TransformException, SQLException {
        return modelDAO.selectAll(builder -> {
            var tableName = modelDAO.tableName();
            var query = "LOWER(" + tableName + ".name) LIKE" + "'%" + name.toLowerCase() + "%'";
            builder.where(query);
        });
    }


}