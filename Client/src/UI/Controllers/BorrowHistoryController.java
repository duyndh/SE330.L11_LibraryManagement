package UI.Controllers;

import UI.Models.DomainModels.BookItemModel;
import UI.Models.DomainModels.BorrowHistoryModel;
import UI.Models.DomainModels.MemberModel;
import UI.Models.DomainModels.StaffModel;
import UI.Models.TableViewItemModel.BorrowHistoryRowItem;
import UI.Views.BaseScene;
import UI.Views.BookItemScene;
import UI.Views.BorrowHistoryScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.BorrowHistoryRepository;
import utils.DB.TransformException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private String searchType;

    private final String searchTypeBorrow = "borrow_id";
    private final String searchTypeMember = "member_id";
    private final String searchTypeBookName = "book_name";

    public BorrowHistoryController(BaseScene scene) {
        super(scene);
        this.scene.setTableViewDelegate(this);
        this.searchType = searchTypeBorrow;

        var b = (BorrowHistoryScene)this.scene;
        b.getBorrowRadioBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchType = searchTypeBorrow;
            }
        });
        b.getMemberRadioBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchType = searchTypeMember;
            }
        });
        b.getBookNameRadioBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchType = searchTypeBookName;
            }
        });
        BorrowHistoryScene bScene = (BorrowHistoryScene)scene;
        if (bScene != null) {
            bScene.getReturnBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onReturnButtonTapped();
                }
            });
        }
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
        InfoEntry[] infos = {
                new InfoEntry("MEMBER ID", MemberModel.class),
                new InfoEntry("STAFF ID", StaffModel.class),
                new InfoEntry("BOOK ITEM ID", BookItemModel.class),
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
                new InfoEntry("MEMBER ID", MemberModel.class, o.getMemberId()),
                new InfoEntry("STAFF ID", StaffModel.class, o.getStaffId()),
                new InfoEntry("BOOK ITEM ID", BookItemModel.class, o.getBookItemId()),
                new InfoEntry("BORROWED AT", Date.class, o.getBorrowedAt()),
                new InfoEntry("RETURNED AT", Date.class, o.getReturnedAt()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            o.setMemberId((Integer)res.get(0));
            o.setStaffId((Integer)res.get(1));
            o.setBookItemId((Integer)res.get(2));
            o.setBorrowedAt((Date)res.get(3));
            o.setReturnedAt((Date)res.get(4));
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
//        this.rowItems.clear();
//        try {
//            this.rowItems.addAll(this.repository
//                    .searchName(searchText)
//                    .stream()
//                    .map(BorrowHistoryRowItem::new)
//                    .collect(Collectors.toList()));
//        } catch (TransformException | SQLException e) {
//            e.printStackTrace();
//            Utils.showError();
//        }
//
//        if (rowItems.size() == 0) {
//            var item = new BorrowHistoryRowItem();
//            rowItems.add(item);
//        }
        doSearchItems();
        this.scene.reloadTableData();
    }

    private void doSearchItems() {
        if (this.searchType.equals(searchTypeBorrow)) {
            var newItems = this.repository
                    .searchById(scene.getSearchTextField().getText())
                    .stream()
                    .map(BorrowHistoryRowItem::new)
                    .collect(Collectors.toList());

            var ids_1 = this.rowItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
            var ids_2 = newItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
            ids_1.retainAll(ids_2);

            newItems.addAll(rowItems);
            var arr = this.rowItems.stream().filter(e -> ids_1.contains(e.getId())).collect(Collectors.toList());
            this.rowItems.clear();
            this.rowItems.addAll(arr);

        } else if (this.searchType.equals(searchTypeMember)) {
            if (this.searchType.equals(searchTypeMember)) {
                var newItems = this.repository
                        .searchByMemberId(scene.getSearchTextField().getText())
                        .stream()
                        .map(BorrowHistoryRowItem::new)
                        .collect(Collectors.toList());

                var ids_1 = this.rowItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
                var ids_2 = newItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
                ids_1.retainAll(ids_2);

                newItems.addAll(rowItems);
                var arr = this.rowItems.stream().filter(e -> ids_1.contains(e.getId())).collect(Collectors.toList());
                this.rowItems.clear();
                this.rowItems.addAll(arr);
            }
        } else if (this.searchType.equals(searchTypeBookName)) {
            if (this.searchType.equals(searchTypeBookName)) {
                var newItems = this.repository
                        .searchByBookName(scene.getSearchTextField().getText())
                        .stream()
                        .map(BorrowHistoryRowItem::new)
                        .collect(Collectors.toList());

                var ids_1 = this.rowItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
                var ids_2 = newItems.stream().map(BorrowHistoryRowItem::getId).collect(Collectors.toCollection(HashSet::new));
                ids_1.retainAll(ids_2);

                newItems.addAll(rowItems);
                var arr = this.rowItems.stream().filter(e -> ids_1.contains(e.getId())).collect(Collectors.toList());
                this.rowItems.clear();
                this.rowItems.addAll(arr);
            }
        }
    }

    @Override
    void onClearButtonTapped() {
        this.reloadData();
        super.onClearButtonTapped();
    }

    void onReturnButtonTapped() {
        if (this.selectedObjects.size() <= 0) return;
        var o = this.selectedObjects.stream().findFirst().get().getModel();

        if (o.getReturnedAt() != null) {
            Utils.showInfo("This book has been returned.", "RETURNED");
            return;
        }

        var res = JOptionPane.showConfirmDialog(null,
                "Do you want to return this book item?",
                "RETURN BOOK",
                JOptionPane.OK_CANCEL_OPTION);

        if (res == JOptionPane.OK_OPTION) {
            try {
                o.setReturnedAt(new Date());
                this.repository.update(o);
                this.reloadData();
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError(e.getMessage());
            }
        }
    }
}