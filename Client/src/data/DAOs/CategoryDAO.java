package data.DAOs;

import data.Models.CategoryModel;

public class CategoryDAO extends BaseDAO<CategoryModel> {
    public CategoryDAO() {
        super(CategoryModel.class);
    }
}
