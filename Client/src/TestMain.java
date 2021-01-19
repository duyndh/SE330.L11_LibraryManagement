import data.DAOs.AuthorDAO;
import utils.DB.Operator;
import utils.DB.TransformException;


import java.sql.SQLException;

public class TestMain {
    public static void main(String[] args) throws SQLException, TransformException {
        var list = new AuthorDAO().selectAll(builder -> {
            builder.where("name", Operator.Equal, "Test Insert")
                    .limit(2);
        });
        System.out.println("DONE");
    }
}
