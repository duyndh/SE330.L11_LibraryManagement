package data.DAOs;


import data.Models.BaseModel;
import utils.DB.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Consumer;

interface DAO<T> {
    public T create(T model);
    public T read(int id);
    public T update(T model);
    public boolean delete(int id);
}

public class BaseDAO<T extends BaseModel> implements DAO<T> {

    private Class<T> cls;

    public BaseDAO() { }
    public BaseDAO(Class<T> modelClass) {
        this.cls = modelClass;
    }


    @Override
    public T read(int id) {
        var res = this.selectAll(builder -> builder.where("id", Operator.Equal, id).limit(1));
        if (res.size() == 0) return null;
        return res.get(0);
    }


    @Override
    public T create(T model) {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var query = DBUtils.insertQuerySerialize(model);
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
            var query = DBUtils.updateQuerySerialize(model);
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



    // QUERY BUILDER
    public ArrayList<T> selectAll(Consumer<SelectBuilder<T>> comsumeSelectBuilder) {
        var result = new ArrayList<T>();
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var builder = new SelectBuilder<T>(this.cls);
            comsumeSelectBuilder.accept(builder);

            var query = builder.build();
            var rs = stm.executeQuery(query);
            while (rs.next()) {
                result.add(DBUtils.objectFor(cls, rs));
            }
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }
        return result;
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
