package utils.DB;

import data.Models.BaseModel;

import java.util.ArrayList;

public class UpdateBuilder<T extends BaseModel> extends QueryBuilder<T> {
    protected final ArrayList<String> setStatements = new ArrayList<String>();

    public UpdateBuilder(Class<BaseModel> cls) {
        super(cls);
    }

    @Override
    public String build() throws TransformException {
        var pattern = "UPDATE %s SET %s WHERE %s;";
        return String.format(
                pattern,
                this.tableName(),
                String.join(",", this.setStatements),
                String.join(",", this.whereStatements)
        );
    }

    public UpdateBuilder<T> where(String column, Operator operator, Object value) {
        return (UpdateBuilder<T>) super.where(column, operator, value);
    }

    public UpdateBuilder<T> set(String column, Object value) {
        var pattern = "%s.%s=" + DBUtils.parseToSqlValueExpression(value);
        var stm = String.format(pattern, this.tableName(), column);
        this.setStatements.add(stm);
        return this;
    }
}
