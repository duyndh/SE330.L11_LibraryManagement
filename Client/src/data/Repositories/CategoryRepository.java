package data.Repositories;

import data.DAOs.CategoryDAO;
import UI.Models.DomainModels.CategoryModel;

public class CategoryRepository extends BaseRepository<CategoryModel> {
    public CategoryRepository() {
        super(new CategoryDAO());
    }
}
