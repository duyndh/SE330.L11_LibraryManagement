package utils.DB;

import data.Models.BaseModel;
import utils.DB.TransformException;

import java.util.ArrayList;


public class SelectBuilder<T extends BaseModel> extends QueryBuilder<T> {

    private final ArrayList<String> orderStatements = new ArrayList<String>();
    private int limit = -1;
    private int offset = -1;

    public SelectBuilder(Class cls) {
        super(cls);
    }

    private SelectBuilder<T> orderBy(String column, Order mode) {
        var tableName = ((TableModel.Table)this.cls.getAnnotation(TableModel.Table.class)).tableName();
        var pattern = "%s.%s %s";
        var stm = String.format(pattern, tableName, column, mode.getLabel());
        this.orderStatements.add(stm);
        return this;
    }

    public SelectBuilder<T> where(String column, Operator operator, Object value) {
        return (SelectBuilder<T>)super.where(column, operator, value);
    }

    public SelectBuilder<T> limit(int limit) {
        this.limit = limit;
        return this;
    }

    public SelectBuilder<T> offset(int offset) {
        this.offset = offset;
        return this;
    }

    public String build() throws TransformException {
        return DBUtils.selectQuerySerialize(
                this.cls,
                () -> String.join(",", this.whereStatements),
                () -> String.join(",", this.orderStatements),
                this.limit,
                this.offset
        );
    }
}