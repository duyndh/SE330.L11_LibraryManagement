package data.DAOs;


import com.mysql.cj.xdevapi.Table;
import data.Models.BaseModel;
import utils.ConnectionFactory;
import utils.DBOTransformer;
import utils.TableModel;
import utils.TransformException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

interface DAO<T> {
    public T create(T model);
    public T read(int id);
    public T update(T model);
    public boolean delete(int id);
}

public class BaseDAO<T extends BaseModel> implements DAO<T> {

    private final DBOTransformer transformer = new DBOTransformer();
    private Class<T> cls;

    public BaseDAO(Class<T> modelClass) {
        this.cls = modelClass;
    }
    public BaseDAO() { }


    @Override
    public T read(int id) {
        T obj = null;
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            String query = this.transformer.selectQuerySerialize(this.cls, () -> "id = " + id);
            var rs = stm.executeQuery(query);
            if (rs.next()) {
                obj = this.transformer.objectFor(cls, rs);
            }
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    @Override
    public T create(T model) {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var query = this.transformer.insertQuerySerialize(model);
            return performAndReturnUpdatedObject(stm, query);
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public T update(T model) {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var query = this.transformer.updateQuerySerialize(model);
            return performAndReturnUpdatedObject(stm, query);
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean delete(int id) {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var tableName = this.cls.getAnnotation(TableModel.Table.class).tableName();
            var pattern = "DELETE FROM %s WHERE id=%d";
            var query = String.format(pattern, tableName, id);
            var affectedRows = stm.executeUpdate(query);
            return affectedRows != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    // HELPERS
    private T performAndReturnUpdatedObject(Statement stm, String query) throws SQLException {
        var affectedRows = stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        var id = 0;

        if (affectedRows == 0) {
            throw new SQLException("No row affected.");
        }

        try (var genKeys = stm.getGeneratedKeys()) {
            if (genKeys.next()) {
                id = genKeys.getInt(1);
            }
        }

        return read(id);
    }
}
