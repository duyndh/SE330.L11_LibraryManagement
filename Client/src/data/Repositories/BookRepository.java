package data.Repositories;
import data.DAOs.*;
import data.Models.*;


public class BookRepository extends BaseRepository<BookModel> {
    public BookRepository() {
        super(new BookDAO());
    }
}

