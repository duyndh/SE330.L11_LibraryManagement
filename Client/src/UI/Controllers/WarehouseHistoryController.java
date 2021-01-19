package UI.Controllers;

import UI.Models.WarehouseHistoryModel;
import UI.Models.TableViewItemModel.WarehouseHistoryRowItem;
import UI.Views.BaseScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.WarehouseHistoryRepository;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

public class WarehouseHistoryController extends BaseController implements TableViewDelegate<WarehouseHistoryRowItem> {

    private final WarehouseHistoryRepository repository = new WarehouseHistoryRepository();
    private final ArrayList<WarehouseHistoryRowItem> rowItems = new ArrayList();
    private HashSet<WarehouseHistoryRowItem> selectedObjects = new HashSet<>();

    public WarehouseHistoryController(BaseScene scene) {
        super(scene);
        this.scene.setTableViewDelegate(this);
    }


    @Override
    public void onAppear() {
        this.reloadData();
    }

    @Override
    public void onDisappear() {
        // Empty
    }

    @Override
    public void reloadData() {
        this.rowItems.clear();
        try {
            this.rowItems.addAll(this.repository
                    .getAll()
                    .stream()
                    .map(WarehouseHistoryRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new WarehouseHistoryRowItem();
            rowItems.add(item);
        }

        this.scene.reloadTableData();
    }


    @Override
    void onCreateTapped() {
        InfoEntry[] infos = {
                new InfoEntry("STAFF ID", Integer.class),
                new InfoEntry("BOOK ITEM ID", Integer.class),
                new InfoEntry("CRATED AT", Date.class),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var item = new WarehouseHistoryModel();
            item.setStaffId((Integer) res.get(0));
            item.setBookItemId((Integer)res.get(1));
            item.setCreatedAt((Date)res.get(2));
            try {
                this.repository.create(item);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError();
            }
        });

        reloadData();
    }

    @Override
    void onUpdatedTapped() {
        if (this.selectedObjects.size() <= 0) return;
        var o = this.selectedObjects.stream().findFirst().get().getModel();

        InfoEntry[] infos = {
                new InfoEntry("STAFF ID", o.getStaffId()),
                new InfoEntry("BOOK ITEM ID", o.getBookItemId()),
                new InfoEntry("CREATED AT", o.getCreatedAt()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            var item = new WarehouseHistoryModel();
            item.setStaffId((Integer) res.get(0));
            item.setBookItemId((Integer)res.get(1));
            item.setCreatedAt((Date)res.get(2));
            try {
                this.repository.update(item);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError();
            }
        });

        reloadData();
    }

    @Override
    void onDeleteTapped() {
        for (var o: this.selectedObjects) {
            var id = o.getId();
            try {
                this.repository.delete(id);
            } catch (SQLException throwables) {
                Utils.showError();
            }
        }
        this.reloadData();
    }


    //
    // Implement table delegates
    //
    @Override
    public int getRowCount() {
        return this.rowItems.size();
    }

    @Override
    public Class<?> rowItemClass() {
        return WarehouseHistoryRowItem.class;
    }

    @Override
    public WarehouseHistoryRowItem itemAt(int row) {
        return this.rowItems.get(row);
    }

    @Override
    public void tableViewDidSelectRow(int[] rows) {
        if (this.rowItems.size() > 0 && rows.length > 0) {
            this.selectedObjects.clear();
            for (var i: rows) {
                this.selectedObjects.add(this.rowItems.get(i));
            }
        }
    }

    @Override
    void onSearchButtonTapped(String searchText) {
        this.rowItems.clear();
        try {
            this.rowItems.addAll(this.repository
                    .searchName(searchText)
                    .stream()
                    .map(WarehouseHistoryRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new WarehouseHistoryRowItem();
            rowItems.add(item);
        }

        this.scene.reloadTableData();
    }

    @Override
    void onClearButtonTapped() {
        this.reloadData();
        super.onClearButtonTapped();
    }
}