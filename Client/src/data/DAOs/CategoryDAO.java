package data.DAOs;

import UI.Models.DomainModels.CategoryModel;

public class CategoryDAO extends BaseDAO<CategoryModel> {
    public CategoryDAO() {
        super(CategoryModel.class);
    }
}
