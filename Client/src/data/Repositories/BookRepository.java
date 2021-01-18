package data.Repositories;

import UI.Models.BookModel;
import data.DAOs.BookDAO;

public class BookRepository extends BaseRepository<BookModel> {
    public BookRepository() {
        super(new BookDAO());
    }
}

