package data.Repositories;

import data.DAOs.BookDAO;
import data.DAOs.BookItemDAO;
import UI.Models.BookItemModel;
import utils.DB.TransformException;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookItemRepository extends BaseRepository<BookItemModel> {
    public BookItemRepository() {
        super(new BookItemDAO());
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public ArrayList<BookItemModel> getAll() throws TransformException, SQLException {
        var items = super.getAll();
        for (var i: items) {
            var b = bookDAO.read(i.getBook_id());
            i.setBook(b);
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
}
