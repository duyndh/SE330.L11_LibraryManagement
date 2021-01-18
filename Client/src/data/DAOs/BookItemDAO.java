package data.DAOs;

import UI.Models.BookItemModel;

public class BookItemDAO extends BaseDAO<BookItemModel> {
    public BookItemDAO() {
        super(BookItemModel.class);
    }
}
