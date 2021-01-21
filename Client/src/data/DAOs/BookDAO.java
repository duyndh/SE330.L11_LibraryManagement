package data.DAOs;

import UI.Models.DomainModels.BookModel;

public class BookDAO extends BaseDAO<BookModel> {
    public BookDAO() {
        super(BookModel.class);
    }
}
