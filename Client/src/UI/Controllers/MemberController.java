package UI.Controllers;

import UI.Models.DomainModels.MemberModel;
import UI.Models.TableViewItemModel.MemberRowItem;
import UI.Views.BaseScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import com.java.utils.AppUtils;
import data.Repositories.MemberRepository;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MemberController extends BaseController implements TableViewDelegate<MemberRowItem> {

    private final MemberRepository repository = new MemberRepository();
    private final ArrayList<MemberRowItem> rowItems = new ArrayList();
    private final HashSet<MemberRowItem> selectedObjects = new HashSet<>();

    public MemberController(BaseScene scene) {
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
                    .map(MemberRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new MemberRowItem();
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

            // Validate
            var phone = (String)res.get(1);
            if (!AppUtils.validatePhoneNumber(phone)) {
                Utils.showError("Invalid phone number.");
                onCreateTapped();
                return;
            }
            var email = (String)res.get(2);
            if (!AppUtils.validateEmail(email)) {
                Utils.showError("Invalid email number.");
                onCreateTapped();
                return;
            }


            //
            //
            //
            var item = new MemberModel();
            item.setFullName((String)res.get(0));
            item.setPhone(phone);
            item.setEmail((String)res.get(2));
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
                new InfoEntry("MEMBER NAME", String.class, o.getFullName()),
                new InfoEntry("MEMBER PHONE", String.class, o.getPhone()),
                new InfoEntry("MEMBER EMAIL", String.class, o.getEmail()),
                new InfoEntry("CREATED AT", Date.class, o.getCreatedAt()),
                new InfoEntry("EXPIRED AT", Date.class, o.getExpiredAt()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {

            // Validate
            var phone = (String)res.get(1);
            if (!AppUtils.validatePhoneNumber(phone)) {
                Utils.showError("Invalid phone number.");
                onUpdatedTapped();
                return;
            }
            var email = (String)res.get(2);
            if (!AppUtils.validateEmail(email)) {
                Utils.showError("Invalid email number.");
                onUpdatedTapped();
                return;
            }


            //
            //
            o.setFullName((String)res.get(0));
            o.setPhone((String)res.get(1));
            o.setEmail((String)res.get(2));
            o.setCreatedAt((Date)res.get(3));
            o.setExpiredAt((Date) res.get(4));
            try {
                this.repository.update(o);
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
        return MemberRowItem.class;
    }

    @Override
    public MemberRowItem itemAt(int row) {
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
                    .map(MemberRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (rowItems.size() == 0) {
            var item = new MemberRowItem();
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