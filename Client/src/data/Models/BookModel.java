package data.Models;

import utils.DB.TableModel;

@TableModel.Table(tableName = "book")
public class BookModel extends BaseModel {

    public BookModel() { }

    @TableModel.Column(columnName="id")
    private int id;

    @TableModel.Column(columnName="name")
    private String name;

    @TableModel.Column(columnName="author_id")
    private int authorId;

    @TableModel.NestedModel(refColumn = "author_id")
    private AuthorModel author;

    @TableModel.Column(columnName="category_id")
    private int categoryId;

    @TableModel.NestedModel(refColumn = "category_id")
    private CategoryModel category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public AuthorModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorModel author) {
        this.author = author;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }
}

