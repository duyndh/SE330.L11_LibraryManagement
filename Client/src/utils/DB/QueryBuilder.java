package utils.DB;

import UI.Models.BaseModel;

import java.util.ArrayList;


public abstract class QueryBuilder<T extends BaseModel> {
    protected Class<? extends BaseModel> cls;
    public QueryBuilder(Class<BaseModel> cls) {
        this.cls = cls;
    }
    protected final ArrayList<String> whereStatements = new ArrayList<String>();

    public abstract String build() throws TransformException;

    protected QueryBuilder<T> where(String column, Operator operator, Object value) {
        var pattern = "%s.%s %s " + DBUtils.parseToSqlValueExpression(value);
        var stm = String.format(pattern, this.tableName(), column, operator.getSym());
        this.whereStatements.add(stm);
        return this;
    }

    public String tableName() {
        return ((TableModel.Table)this.cls.getAnnotation(TableModel.Table.class)).tableName();
    }
}



