import data.Models.BookModel;
import utils.DBOTransformer;
import utils.TransformException;
import utils.*;

import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) {
        try {
            String query = new DBOTransformer().selectQuerySerialize(BookModel.class, () -> "book.id = 1");
            var conn = ConnectionFactory.getConnection();
            var stm = conn.createStatement();
            var rs = stm.executeQuery(query);
            while (rs.next()) {
                BookModel a = new DBOTransformer().objectFor(BookModel.class, rs);
                System.out.println("DONE.");
            }
        } catch (TransformException | SQLException e) {
            e.printStackTrace();
        }
    }
}
