package data.DAOs;

import data.Models.BookItemModel;

public class BookItemDAO extends BaseDAO<BookItemModel> {
    public BookItemDAO() {
        super(BookItemModel.class);
    }
}
