package UI.TableView;

public interface TableViewDelegate<T extends TableViewRowItem> {
    public int getRowCount();

    public Class<?> rowItemClass();

    public T itemAt(int row);
}
