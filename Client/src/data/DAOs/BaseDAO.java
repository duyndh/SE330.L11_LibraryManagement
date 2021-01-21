package data.DAOs;


import UI.Models.DomainModels.BaseModel;
import utils.DB.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.function.Consumer;

interface DAO<T> {
    public T create(T model) throws SQLException, TransformException;
    public T read(int id) throws TransformException, SQLException;
    public T update(T model) throws SQLException, TransformException;
    public boolean delete(int id) throws SQLException;
}

public class BaseDAO<T extends BaseModel> implements DAO<T> {

    private Class<T> cls;
    public Class<T> getCls() { return cls; }

    public BaseDAO() { }
    public BaseDAO(Class<T> modelClass) {
        this.cls = modelClass;
    }

    public String tableName() {
        return this.cls.getAnnotation(TableModel.Table.class).tableName();
    }


    @Override
    public T read(int id) throws TransformException, SQLException {
        var res = this.selectAll(builder -> builder.where("id", Operator.Equal, id).limit(1));
        if (res.size() == 0) return null;
        return res.get(0);
    }


    @Override
    public T create(T model) throws SQLException, TransformException {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var query = DBUtils.insertQuerySerialize(model);
            return performAndReturnUpdatedObject(stm, query);
        }
    }


    @Override
    public T update(T model) throws SQLException, TransformException {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var query = DBUtils.updateQuerySerialize(model);
            return performAndReturnUpdatedObject(stm, query);
        }
    }


    @Override
    public boolean delete(int id) throws SQLException {
        try (
                var conn = ConnectionFactory.getConnection();
                var stm = conn.createStatement();
        ) {
            var tableName = this.cls.getAnnotation(TableModel.Table.class).tableName();
            var pattern = "DELETE FROM %s WHERE id=%d";
            var query = String.format(pattern, tableName, id);
            var affectedRows = stm.executeUpdate(query);
            return affectedRows != 0;
        }
    }



    // QUERY BUILDER
    public ArrayList<T> selectAll(Consumer<SelectBuilder<T>> comsumeSelectBuilder) throws SQLException, TransformException {
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
        }
        return result;
    }


    // HELPERS
    private T performAndReturnUpdatedObject(Statement stm, String query) throws SQLException, TransformException {
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
