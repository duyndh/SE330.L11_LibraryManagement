package data.Repositories;

import data.DAOs.BookDAO;
import data.DAOs.BookItemDAO;
import UI.Models.BookItemModel;

import java.util.ArrayList;

public class BookItemRepository extends BaseRepository<BookItemModel> {
    public BookItemRepository() {
        super(new BookItemDAO());
    }

    private BookDAO bookDAO = new BookDAO();

    @Override
    public ArrayList<BookItemModel> getAll() {
        var items = super.getAll();
        for (var i: items) {
            var b = bookDAO.read(i.getBook_id());
            i.setBook(b);
        }
        return items;
    }
}
