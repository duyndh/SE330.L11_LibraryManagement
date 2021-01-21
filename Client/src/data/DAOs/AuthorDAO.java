package data.DAOs;
import UI.Models.DomainModels.AuthorModel;

public class AuthorDAO extends BaseDAO<AuthorModel> {
    public AuthorDAO() {
        super(AuthorModel.class);
    }
}

