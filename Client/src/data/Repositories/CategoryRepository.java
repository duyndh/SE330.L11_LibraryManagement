package data.Repositories;

import data.DAOs.CategoryDAO;
import UI.Models.CategoryModel;

public class CategoryRepository extends BaseRepository<CategoryModel> {
    public CategoryRepository() {
        super(new CategoryDAO());
    }
}
