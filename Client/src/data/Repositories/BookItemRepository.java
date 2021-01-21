package data.Repositories;

import UI.Models.DomainModels.BorrowHistoryModel;
import data.DAOs.BookDAO;
import data.DAOs.BookItemDAO;
import UI.Models.DomainModels.BookItemModel;
import data.DAOs.BorrowHistoryDAO;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookItemRepository extends BaseRepository<BookItemModel> {
    public BookItemRepository() {
        super(new BookItemDAO());
    }

    private BookDAO bookDAO = new BookDAO();
    private BorrowHistoryDAO borrowHistoryDAO = new BorrowHistoryDAO();

    @Override
    public ArrayList<BookItemModel> getAll() throws TransformException, SQLException {
        var items = super.getAll();
        for (var i: items) {
            var b = bookDAO.read(i.getBook_id());
            var br = getBorrowHistoryById(i.getId());
            i.setBook(b);
            i.setBorrowHistory(br);
        }
        return items;
    }

    @Override
    public ArrayList<BookItemModel> searchName(String name) throws TransformException, SQLException {
        var items = modelDAO.selectAll(builder -> {
            var tableName = modelDAO.tableName();
            var query = "LOWER(book.name) LIKE " + "'%" + name.toLowerCase() + "%'";
            builder.where(query);
        });
        for (var i: items) {
            var b = bookDAO.read(i.getBook_id());
            i.setBook(b);
        }
        return items;
    }


    private BorrowHistoryModel getBorrowHistoryById(int id) {
        try {
            var histories = this.borrowHistoryDAO.selectAll(builder -> {
                builder.where("book_item_id=" + id)
                        .limit(1);
            });

            if (histories.size() > 0) {
                return histories.get(0);
            }

        } catch (SQLException | TransformException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
