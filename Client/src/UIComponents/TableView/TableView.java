package UIComponents.TableView;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class TableView extends JPanel {

    private final JTable table = new JTable();
    private final JScrollPane scrollPane;
    private final CustomTableViewModel tableModel;

    public TableViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(TableViewDelegate delegate) {
        this.delegate = delegate;
    }

    private TableViewDelegate delegate = null;

    public TableView() {
        super(new BorderLayout());
        setLayout(new BorderLayout());

        // Init sroll view.
        this.scrollPane = new JScrollPane(this.table);
        this.scrollPane.setPreferredSize(new Dimension(640, 450));
        this.table.setPreferredSize(new Dimension(640, 450));
        this.tableModel = new CustomTableViewModel(() -> {
            return this.delegate;
        });
        this.table.setModel(this.tableModel);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public void reloadData() {
        this.tableModel.reloadData();
    }

}


class CustomTableViewModel extends DefaultTableModel {

    final private Supplier<TableViewDelegate> tableViewDelegateSupplier;
    private TableViewDelegate delegate;

    public CustomTableViewModel(Supplier<TableViewDelegate> supplier) {
        this.tableViewDelegateSupplier = supplier;
    }

    void reloadData() {
        this.delegate = this.tableViewDelegateSupplier.get();

        this.columnFieldsFromClass = this.getColumnFieldsFromClass(delegate.rowItemClass());

        this.columnNamesFromClass = (ArrayList<String>)this.columnFieldsFromClass.stream().map(field -> {
            return field.getAnnotation(TableViewRowItemColumn.class).columnName();
        }).collect(Collectors.toList());

        this.fireTableStructureChanged();
    }

    private ArrayList<Field> getColumnFieldsFromClass(Class<?> cls) {
        return (ArrayList<Field>) Arrays.stream(delegate.rowItemClass().getDeclaredFields())
                .filter(field -> {
                    return field.isAnnotationPresent(TableViewRowItemColumn.class);
                })
                .collect(Collectors.toList());
    }

    private Object getValueAt(int column, Object targetObject) {
        var value = new Object();
        var field = this.columnFieldsFromClass.get(column);
        field.setAccessible(true);
        try {
            value = field.get(targetObject);
        } catch (IllegalAccessException e) {
            value = null;
        }
        return value;
    }

    private ArrayList<Field> columnFieldsFromClass;
    private ArrayList<String> columnNamesFromClass;

    @Override
    public int getRowCount() {
        if (delegate == null) return 0;
        return delegate.getRowCount();
    }

    @Override
    public int getColumnCount() {
        if (delegate == null) return 0;
        return this.columnNamesFromClass.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        if (delegate == null) return "";
        return this.columnNamesFromClass.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (delegate == null) return int.class;
        return this.columnFieldsFromClass.get(columnIndex).getType();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (delegate == null) return new Object();
        var obj = delegate.itemAt(rowIndex);
        return getValueAt(columnIndex, obj);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        super.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        super.removeTableModelListener(l);
    }
}

