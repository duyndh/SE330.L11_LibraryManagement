package data.DAOs;

import data.Models.BookModel;

public class BookDAO extends BaseDAO<BookModel> {
    public BookDAO() {
        super(BookModel.class);
    }
}
