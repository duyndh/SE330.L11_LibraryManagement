package data.Repositories;

import data.DAOs.BookItemDAO;
import data.Models.BookItemModel;

public class BookItemRepository extends BaseRepository<BookItemModel> {
    public BookItemRepository() {
        super(new BookItemDAO());
    }
}
