package UI.Models.DomainModels;

import utils.DB.TableModel;

@TableModel.Table(tableName="author")
public class AuthorModel extends BaseModel {

    public AuthorModel() {  }

    @TableModel.Column(columnName = "id")
    private int id;

    @TableModel.Column(columnName = "name")
    private String name;

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

}