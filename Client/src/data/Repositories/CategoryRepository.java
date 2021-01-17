package data.Repositories;

import data.DAOs.CategoryDAO;
import data.Models.CategoryModel;

public class CategoryRepository extends BaseRepository<CategoryModel> {
    public CategoryRepository() {
        super(new CategoryDAO());
    }
}
