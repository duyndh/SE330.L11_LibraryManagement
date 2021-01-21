package data.Repositories;

import data.DAOs.BookDAO;
import data.DAOs.BookItemDAO;
import data.DAOs.BorrowHistoryDAO;
import UI.Models.DomainModels.BorrowHistoryModel;
import utils.DB.TransformException;

import java.awt.print.Book;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class BorrowHistoryRepository extends BaseRepository<BorrowHistoryModel> {
    public BorrowHistoryRepository() {
        super(new BorrowHistoryDAO());
    }
    private BookItemDAO bookItemDAO = new BookItemDAO();
    private BookRepository bookRepository = new BookRepository();

    @Override
    public ArrayList<BorrowHistoryModel> getAll() throws TransformException, SQLException {
        var items = super.getAll();
        for (var i: items) {
            var b = bookItemDAO.read(i.getBookItem().getId());
            i.setBookItem(b);
        }
        return items;
    }

    public ArrayList<BorrowHistoryModel> searchById(String id) {
        var res = new ArrayList<BorrowHistoryModel>();
        try {
            var i = Integer.parseInt(id);
            var item = read(i);
            var b = bookItemDAO.read(item.getBookItem().getId());
            item.setBookItem(b);
            res.add(item);
        } catch (NumberFormatException | TransformException | SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<BorrowHistoryModel> searchByBookName(String bookName) {
        var res = new ArrayList<BorrowHistoryModel>();
        try {
            var booksByName = bookRepository.searchName(bookName).stream().map(b -> b.getName()).collect(Collectors.toList());
            var names = String.join(" ", booksByName);
            var brs = getAll().stream().filter(b -> {
                var n = b.getBookItem().getBook().getName();
                return names.contains(n);
            }).collect(Collectors.toList());

            for (var i: brs) {
                var b = bookItemDAO.read(i.getBookItem().getId());
                i.setBookItem(b);
            }

            res.addAll(brs);

        } catch (NumberFormatException | TransformException | SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<BorrowHistoryModel> searchByMemberId(String memberId) {
        var res = new ArrayList<BorrowHistoryModel>();
        try {
            var i = Integer.parseInt(memberId);
            var brs = this.modelDAO.selectAll(builder -> {
                builder.where("borrow_history.member_id = " + memberId);
            });

            for (var ii: brs) {
                var b = bookItemDAO.read(ii.getBookItem().getId());
                ii.setBookItem(b);
            }

            res.addAll(brs);
        } catch (NumberFormatException | TransformException | SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}