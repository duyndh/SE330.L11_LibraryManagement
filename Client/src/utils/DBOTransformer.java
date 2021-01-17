package utils;

import data.Models.BaseModel;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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


    // ==========================
    // SERIALIZE TO UPDATE QUERY
    // ==========================
    public <T extends BaseModel> String updateQuerySerialize(T targetObject) throws TransformException {
        try {
            var cls = targetObject.getClass();
            var fieldMap = getFieldMap(targetObject);
            var tableName = cls.getAnnotation(TableModel.Table.class).tableName();

            var updateStatements = fieldMap.entrySet().stream().map(entry -> {
                var valueEntry = entry.getValue();
                var valueExpression = parseToSqlValueExpression(valueEntry.getValue(), valueEntry.getKey());
                return String.format("%s=%s", entry.getValue(), valueExpression);
            }).collect(Collectors.toList());

            var pattern = " UPDATE %s SET %s WHERE %s;";
            return String.format(pattern, tableName, String.join(",", updateStatements));

        } catch (Exception ex) {
            throw new TransformException("Cannot serialize.");
        }
    }


    // ==========================
    // SERIALIZE TO SELECT QUERY
    // ==========================
    //
    // Only get nested object.
    // No list nested object available.
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


    // ==========================
    // SERIALIZE TO INSERT QUERY
    // ==========================
    public <T extends BaseModel> String insertQuerySerialize(T targetObject) throws TransformException{
        try {
            var cls = targetObject.getClass();
            var fieldMap = getFieldMap(targetObject);
            var tableName = cls.getAnnotation(TableModel.Table.class).tableName();

            // Query string
            var keys = String.join(",", fieldMap.keySet());
            var values = fieldMap.values().stream().map(entry -> {
                return parseToSqlValueExpression(entry.getValue(), entry.getKey());
            }).collect(Collectors.toList());

            return new StringBuilder().append("INSERT INTO ")
                    .append(tableName)
                    .append(" (")
                    .append(String.join(",", keys))
                    .append(" ) VALUES (")
                    .append(String.join(",", values))
                    .append(");")
            .toString();

        } catch (Exception ex) {
            throw new TransformException("Cannot serialize.");
        }
    }


    private <T> HashMap<String, Map.Entry<Class<?>, Object>> getFieldMap(T targetObject) throws IllegalAccessException {
        var cls = targetObject.getClass();
        var fieldMap = new HashMap<String, Map.Entry<Class<?>, Object>>();
        // Insert forein object first
        for (var field: cls.getDeclaredFields()) {
            field.setAccessible(true);
            for (var anno: field.getAnnotations()) {
                // Just ignore NestedModel
                // Serialize field
                if (anno instanceof TableModel.Column) {
                    var fieldType = field.getType();
                    var fieldValue = field.get(targetObject);
                    var fieldName = field.getName();
                    var entry = new FieldEntry(fieldType, fieldValue);
                    if (!fieldName.equals("id")) {
                        fieldMap.put(fieldName, entry);
                    }
                }
            }
        }
        return fieldMap;
    }


    // Try to insert with unexisted nested model.
    // Not resolved yet
    /*
    public <T extends BaseModel> void insertQuerySerialize(T targetObject, ArrayList<String> insertQueries) throws TransformException{
        try {
            var cls = targetObject.getClass();
            var fieldMap = new HashMap<String, Map.Entry<Class<?>, Object>>();
            var tableName = cls.getAnnotation(TableModel.Table.class).tableName();

            var foreignKeys = new ArrayList<String>();

            // Insert forein object first
            for (var field: cls.getDeclaredFields()) {
                field.setAccessible(true);
                for (var anno: field.getAnnotations()) {

                    // Recursive add insert queries for nested object.
                    if (anno instanceof TableModel.NestedModel) {
                        // Check if need inserted.
                        var nestedObject = (BaseModel)field.get(targetObject);
                        if (nestedObject != null) {
                            var nestedIDField = nestedObject.getClass().getField("id");
                            nestedIDField.setAccessible(true);
                            var nestedId = nestedIDField.getInt(nestedObject);

                            if (nestedId != 0) {
                                insertQuerySerialize(nestedObject, insertQueries);
                            }

                            var key = ((TableModel.NestedModel)anno).refColumn();
                            foreignKeys.add(key);
                        }
                    }

                    // Fields
                    var fieldType = field.getType();
                    var fieldValue = field.get(targetObject);
                    var fieldName = field.getName();
                    var entry = new FieldEntry(fieldType, fieldValue);
                    if (foreignKeys.contains(fieldName)) {
                        fieldMap.put(fieldName, new FieldEntry(String.class, QUESTION_MARK));
                    } else {
                        fieldMap.put(fieldName, entry);
                    }

                }
            }

            // Query string
            var builder = new StringBuilder();
            var keys = String.join(",", fieldMap.keySet());
            var values = fieldMap.values().stream().map(entry -> {
                return parseToSqlValueExpression(entry.getValue(), entry.getKey());
            }).collect(Collectors.toList());

            builder.append("INSERT INTO ")
                    .append(tableName)
                    .append(" (")
                    .append(String.join(",", keys))
                    .append(" ) VALUES (")
                    .append(String.join(",", values))
                    .append(");");

            var query = builder.toString();
            insertQueries.add(query);
        } catch (Exception ex) {
            throw new TransformException("Cannot serialize.");
        }
    }
     */

    private String parseToSqlValueExpression(Object value, Class<?> cls) {
        if (cls == String.class) {
            return "\"" + value.toString() + "\"";
        }

        if (cls == Boolean.class) {
            var v = (Boolean)value;
            return v ? "true" : "false";
        }

        if (cls == int.class) {
            var v = (int)value;
            return "" + v;
        }

        if (cls == Date.class) {
            var pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            var v = (Date)value;
            return simpleDateFormat.format(v);
        }

        return null;
    }
}


final class FieldEntry<K, V> implements Map.Entry<K, V> {
    private final K key;
    private V value;

    public FieldEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }
}