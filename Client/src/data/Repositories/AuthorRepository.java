package data.Repositories;

import data.DAOs.AuthorDAO;
import UI.Models.AuthorModel;

public class AuthorRepository extends BaseRepository<AuthorModel> {
    public AuthorRepository() {
        super(new AuthorDAO());
    }
}
