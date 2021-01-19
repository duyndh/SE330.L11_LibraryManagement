package UI.Controllers;

import UI.Models.BorrowHistoryModel;
import UI.Models.TableViewItemModel.BorrowHistoryRowItem;
import UI.Views.BaseScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.BorrowHistoryRepository;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BorrowHistoryController extends BaseController implements TableViewDelegate<BorrowHistoryRowItem> {

    private final BorrowHistoryRepository repository = new BorrowHistoryRepository();
    private final ArrayList<BorrowHistoryRowItem> rowItems = new ArrayList();
    private HashSet<BorrowHistoryRowItem> selectedObjects = new HashSet<>();

    public BorrowHistoryController(BaseScene scene) {
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
                    .map(BorrowHistoryRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new BorrowHistoryRowItem();
            rowItems.add(item);
        }

        this.scene.reloadTableData();
    }


    @Override
    void onCreateTapped() {
        if (this.selectedObjects.size() <= 0) return;
        var o = this.selectedObjects.stream().findFirst().get().getModel();

        InfoEntry[] infos = {
                new InfoEntry("MEMBER ID", Integer.class),
                new InfoEntry("STAFF ID", Integer.class),
                new InfoEntry("BOOK ITEM ID", Integer.class),
                new InfoEntry("BORROWED AT", Date.class),
                new InfoEntry("RETURNED AT", Date.class),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var item = new BorrowHistoryModel();
            item.setMemberId((Integer)res.get(0));
            item.setStaffId((Integer)res.get(1));
            item.setBookItemId((Integer)res.get(2));
            item.setBorrowedAt((Date)res.get(3));
            item.setReturnedAt((Date)res.get(4));
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
                new InfoEntry("MEMBER ID", o.getMemberId()),
                new InfoEntry("STAFF ID", o.getStaffId()),
                new InfoEntry("BOOK ITEM ID", o.getBookItemId()),
                new InfoEntry("BORROWED AT", o.getBorrowedAt()),
                new InfoEntry("RETURNED AT", o.getReturnedAt()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            var item = new BorrowHistoryModel();
            item.setMemberId((Integer)res.get(0));
            item.setStaffId((Integer)res.get(1));
            item.setBookItemId((Integer)res.get(2));
            item.setBorrowedAt((Date)res.get(3));
            item.setReturnedAt((Date)res.get(4));
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
        return BorrowHistoryRowItem.class;
    }

    @Override
    public BorrowHistoryRowItem itemAt(int row) {
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
                    .map(BorrowHistoryRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new BorrowHistoryRowItem();
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