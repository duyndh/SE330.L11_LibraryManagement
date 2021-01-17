package data.Models;


import utils.TableModel;


@TableModel.Table(tableName="author")
public class AuthorModel extends BaseModel {

    public AuthorModel() {  }

    @TableModel.Column
    public int id;

    @TableModel.Column
    public String name;

}