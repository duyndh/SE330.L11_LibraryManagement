package UI.Controllers;

import UI.Models.DomainModels.StaffModel;
import UI.Models.TableViewItemModel.StaffRowItem;
import UI.Views.BaseScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.StaffRepository;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

public class StaffController extends BaseController implements TableViewDelegate<StaffRowItem> {

    private final StaffRepository repository = new StaffRepository();
    private final ArrayList<StaffRowItem> rowItems = new ArrayList();
    private final HashSet<StaffRowItem> selectedObjects = new HashSet<>();

    public StaffController(BaseScene scene) {
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
                    .map(StaffRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new StaffRowItem();
            rowItems.add(item);
        }

        this.scene.reloadTableData();
    }


    @Override
    void onCreateTapped() {
        InfoEntry[] infos = {
                new InfoEntry("FULLNAME", String.class),
                new InfoEntry("PHONE", String.class),
                new InfoEntry("EMAIL", String.class)
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var item = new StaffModel();
            item.setFullName((String)res.get(0));
            item.setPhone((String)res.get(1));
            item.setEmail((String)res.get(2));
            try {
                this.repository.create(item);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError(e.getMessage());
            }
        });

        reloadData();
    }

    @Override
    void onUpdatedTapped() {
        if (this.selectedObjects.size() <= 0) return;
        var o = this.selectedObjects.stream().findFirst().get().getModel();

        InfoEntry[] infos = {
                new InfoEntry("MEMBER NAME", o.getFullName()),
                new InfoEntry("MEMBER PHONE", o.getPhone()),
                new InfoEntry("MEMBER EMAIL", o.getEmail()),
                new InfoEntry("CREATED AT", o.getCreatedAt()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            o.setFullName((String)res.get(0));
            o.setPhone((String)res.get(1));
            o.setEmail((String)res.get(2));
            o.setCreatedAt((Date)res.get(3));
            try {
                this.repository.update(o);
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError(e.getMessage());
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
                Utils.showError(throwables.getMessage());
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
        return StaffRowItem.class;
    }

    @Override
    public StaffRowItem itemAt(int row) {
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
                    .map(StaffRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new StaffRowItem();
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