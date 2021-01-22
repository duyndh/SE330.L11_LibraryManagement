package UI.Controllers;

import UI.Models.DomainModels.*;
import UI.Models.TableViewItemModel.BookItemRowItem;
import UI.Views.BaseScene;
import UI.Views.BookItemScene;
import UIComponents.TableView.TableViewDelegate;
import com.java.project.InfoEntry;
import com.java.project.Utils;
import data.Repositories.BookItemRepository;
import data.Repositories.BorrowHistoryRepository;
import utils.DB.TransformException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class BookItemControlller extends BaseController implements TableViewDelegate<BookItemRowItem> {

    // private final BookItemScene booksScene;
    private final BookItemRepository bookItemRepository = new BookItemRepository();
    private final BorrowHistoryRepository borrowHistoryRepository = new BorrowHistoryRepository();
    private final ArrayList<BookItemRowItem> bookItemRowItems = new ArrayList();
    // private BookItemRowItem selectedObject = null;
    private HashSet<BookItemRowItem> selectedObjects = new HashSet<BookItemRowItem>();

    public BookItemControlller(BaseScene scene) {
        super(scene);
        this.scene.setTableViewDelegate(this);

        BookItemScene bScene = (BookItemScene)scene;
        if (bScene != null) {
            bScene.getBorrowBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onBorrowButtonTapped();
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
        this.bookItemRowItems.clear();
        try {
            this.bookItemRowItems.addAll(this.bookItemRepository
                    .getAll()
                    .stream()
                    .map(BookItemRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (bookItemRowItems.size() == 0) {
            var item = new BookItemRowItem();
            bookItemRowItems.add(item);
        }

        this.scene.reloadTableData();
    }


    @Override
    void onCreateTapped() {
        InfoEntry[] infos = {
                new InfoEntry("BOOK ID", BookModel.class),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var item = new BookItemModel();
            item.setBook_id((int)res.get(0));
            try {
                this.bookItemRepository.create(item);
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
                new InfoEntry("BOOK ID", BookModel.class, o.getBook_id()),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.updatePopup(arr, res -> {
            o.setBook_id((int)res.get(0));
            try {
                this.bookItemRepository.update(o);
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
                this.bookItemRepository.delete(id);
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
        return this.bookItemRowItems.size();
    }

    @Override
    public Class<?> rowItemClass() {
        return BookItemRowItem.class;
    }

    @Override
    public BookItemRowItem itemAt(int row) {
        return this.bookItemRowItems.get(row);
    }

    @Override
    public void tableViewDidSelectRow(int[] rows) {
        if (this.bookItemRowItems.size() > 0 && rows.length > 0) {
            this.selectedObjects.clear();
            for (var i: rows) {
                this.selectedObjects.add(this.bookItemRowItems.get(i));
            }
        }
    }

    @Override
    void onSearchButtonTapped(String searchText) {
        this.bookItemRowItems.clear();
        try {
            this.bookItemRowItems.addAll(this.bookItemRepository
                    .searchName(searchText)
                    .stream()
                    .map(BookItemRowItem::new)
                    .collect(Collectors.toList()));
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
            Utils.showError();
        }

        if (bookItemRowItems.size() == 0) {
            var item = new BookItemRowItem();
            bookItemRowItems.add(item);
        }

        this.scene.reloadTableData();
    }

    private void onBorrowButtonTapped() {
        if (this.selectedObjects.size() <= 0) return;
        var o = this.selectedObjects.stream().findFirst().get().getModel();

        if (!o.isAvailable()) {
            Utils.showInfo("This book has been borrowed.", "BORROWED");
            return;
        }

        InfoEntry[] infos = {
                new InfoEntry("BOOK ITEM ID", BookItemModel.class, o.getId()),
                new InfoEntry("MEMBER ID", MemberModel.class),
                new InfoEntry("STAFF ID", StaffModel.class),
        };
        var arr = new ArrayList<InfoEntry>(Arrays.asList(infos));

        Utils.createPopup(arr, res -> {
            var br = new BorrowHistoryModel();
            br.setBookItemId((int)res.get(0));
            br.setMemberId((int)res.get(1));
            br.setStaffId((int)res.get(2));
            try {
                br = this.borrowHistoryRepository.create(br);
                if (br.getId() != 0) {
                    Utils.showInfo("Borrow success.", "SUCCESS");
                }
            } catch (TransformException | SQLException e) {
                e.printStackTrace();
                Utils.showError();
            }
        });
        this.reloadData();
    }

    @Override
    void onClearButtonTapped() {
        this.reloadData();
        super.onClearButtonTapped();
    }
}