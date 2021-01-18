package data.DAOs;

import UI.Models.CategoryModel;

public class CategoryDAO extends BaseDAO<CategoryModel> {
    public CategoryDAO() {
        super(CategoryModel.class);
    }
}
