package data.DAOs;

import UI.Models.BookModel;

public class BookDAO extends BaseDAO<BookModel> {
    public BookDAO() {
        super(BookModel.class);
    }
}
