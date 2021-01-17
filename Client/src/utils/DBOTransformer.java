package utils;

import data.Models.BaseModel;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;


public class DBOTransformer {

    // =======================
    // PARSING OBJECT
    // =======================
    public <T extends BaseModel> T objectFor(Class<? extends BaseModel> cls,
                                                    ResultSet rs) throws TransformException {
        return objectFor(cls, rs, "", true);
    }

    private <T extends BaseModel> T objectFor(Class<? extends BaseModel> cls,
                                                     ResultSet rs,
                                                     String prefix_db_label,
                                                     boolean parseNested) throws TransformException {
        try {
            // Create instance
            Constructor<?> constructor = cls.getConstructor();
            T obj = (T) constructor.newInstance();

            for (var field : cls.getDeclaredFields()) {
                for (var anno : field.getAnnotations()) {
                    if (anno instanceof TableModel.Column) {

                        field.setAccessible(true);
                        // Assigns value
                        String field_label = ((TableModel.Column) anno).columnName();
                        if (field_label.equals("")) {
                            field_label = field.getName();
                        }

                        setValueForField(field, obj, (prefix_db_label + field_label), rs);
                    }

                    if (anno instanceof TableModel.NestedModel && parseNested) {
                        field.setAccessible(true);
                        // Assigns nested
                        var tableModel = (Class<BaseModel>)field.getType();
                        var nestedTableName = (tableModel.getAnnotation(TableModel.Table.class)).tableName();

                        Object nestedObj = objectFor(tableModel, rs, nestedTableName + "__", false);
                        field.set(obj, nestedObj);
                    }
                }
            }

            return obj;

        } catch (Exception ex) {
            throw new TransformException("Cannot parse.");
        }
    }

    private void setValueForField(Field field,
                                         Object targetObject,
                                         String field_label,
                                         ResultSet rs) throws TransformException {
        Class<?> fieldType = field.getType();
        try {
            if (fieldType == String.class) {
                field.set(targetObject, rs.getString(field_label));
                return;
            }

            if (fieldType == Array.class) {
                field.set(targetObject, rs.getArray(field_label));
                return;
            }

            if (fieldType == Boolean.class) {
                field.setBoolean(targetObject, rs.getBoolean(field_label));
                return;
            }

            if (fieldType == int.class) {
                field.setInt(targetObject, rs.getInt(field_label));
                return;
            }

            if (fieldType == float.class) {
                field.setFloat(targetObject, rs.getFloat(field_label));
                return;
            }

            if (fieldType == Date.class) {
                field.set(targetObject, rs.getDate(field_label));
            }

        } catch (Exception ex) {
            throw new TransformException("Cannot parse.");
        }
    }


    // =======================
    // PARSING OBJECT
    // =======================
    public <T extends BaseModel> String selectQuerySerialize(Class<T> cls) throws TransformException {
        return selectQuerySerialize(cls, () -> "");
    }

    public <T extends BaseModel> String selectQuerySerialize(Class<T> cls, Supplier<String> predicate) throws TransformException {
        try {
            var strBuilder = new StringBuilder();
            var queryTableName = cls.getAnnotation(TableModel.Table.class).tableName();
            strBuilder.append("SELECT ");

            // key is tableName, value is foreign key.
            var tableDict = new HashMap<String, String>();
            var selectKeys = selectKeys(cls, "", true);
            strBuilder
                    .append(String.join(",", selectKeys))
                    .append(" FROM ")
                    .append(queryTableName)
                    .append(" ");

            // get join tables
            for (var field : cls.getDeclaredFields()) {
                var ann = field.getAnnotation(TableModel.NestedModel.class);
                if (ann != null) {
                    var table = (Class<BaseModel>)field.getType();
                    var tableName = (table.getAnnotation(TableModel.Table.class)).tableName();
                    tableDict.put(tableName, ann.refColumn());
                }
            }

            // join query
            var joinQuery = tableDict.entrySet()
                    .stream()
                    .map(entry -> {
                        return String.format("LEFT JOIN %s ON %s.%s = %s.id ",
                                entry.getKey(),
                                queryTableName,
                                entry.getValue(),
                                entry.getKey());
            }).collect(Collectors.toList());
            strBuilder.append(String.join(" ", joinQuery));

            // predicate
            var pred = predicate.get();
            if (!pred.equals("")) {
                strBuilder
                        .append(" WHERE ")
                        .append(pred);
            }

            // end query
            strBuilder.append(";");
            return strBuilder.toString();

        } catch (Exception ex) {
            throw new TransformException("Cannot serialize query from " + cls.getName());
        }
    }

    private <T extends BaseModel> ArrayList<String> selectKeys(Class<T> cls,
                                                                      String prefix_db_label,
                                                                      boolean parseNested) throws TransformException {
        var selectKeys = new HashSet<String>();
        var tableName = cls.getAnnotation(TableModel.Table.class).tableName();

        for (var field : cls.getDeclaredFields()) {
            for (var anno : field.getAnnotations()) {

                if (anno instanceof TableModel.Column) {
                    var ann = (TableModel.Column)anno;
                    var columnName = ann.columnName();
                    if (columnName.equals("")) {
                        columnName = field.getName();
                    }

                    var querySelectKey = prefix_db_label + columnName;
                    var query = String.format("%s.%s as %s", tableName, columnName, querySelectKey);
                    selectKeys.add(query);
                }

                // Create dict for join later
                if (anno instanceof TableModel.NestedModel && parseNested) {
                    var nestedTableModel = (Class<BaseModel>)field.getType();
                    var nestedTableName = (nestedTableModel.getAnnotation(TableModel.Table.class)).tableName();

                    var nestedKeys = selectKeys(nestedTableModel, nestedTableName + "__", false);
                    selectKeys.addAll(nestedKeys);
                }
            }
        }

        return new ArrayList<String>(selectKeys);
    }
}

