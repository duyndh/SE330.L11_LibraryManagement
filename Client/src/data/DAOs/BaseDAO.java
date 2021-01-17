package data.DAOs;


import data.Models.BaseModel;
import utils.ConnectionFactory;
import utils.DBOTransformer;
import utils.TransformException;

import java.sql.SQLException;

interface DAO<T> {
    public T create(T model);
    public T read(int id);
    public T update(T model);
    public boolean delete(int id);
}

public class BaseDAO<T extends BaseModel> implements DAO<T> {

    private final DBOTransformer transformer = new DBOTransformer();
    private final Class<T> cls;

    public BaseDAO(Class<T> modelClass) {
        this.cls = modelClass;
    }

    @Override
    public T create(T model) {
        return null;
    }

    @Override
    public T read(int id) {
        T obj = null;
        try (var conn = ConnectionFactory.getConnection()) {
            try (var stm = conn.createStatement()) {
                String query = this.transformer.selectQuerySerialize(this.cls, () -> "id = " + id);
                var rs = stm.executeQuery(query);
                if (rs.next()) {
                    obj = this.transformer.objectFor(cls, rs);
                }
            }
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public T update(T model) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

}
