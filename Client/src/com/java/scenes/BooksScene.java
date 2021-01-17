package com.java.scenes;

import com.java.panels.Content;
import com.java.panels.Data;
import com.java.panels.Header;
import com.java.project.Utils;
import com.java.view_models.BookViewModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BooksScene {

    private Content _content = new Content();

    private Header _header = new Header("BOOKS");
    private Data _data = new Data();

    public Content get_content() { return _content; }

    public BooksScene() {
        Utils.Log("BooksScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());
    }

    public void loadData() {

        String[] columnNames = {
                "ID",
                "Title",
                "Categories",
                "Authors",
                "Publisher",
                "Published Date",
                "Price"
        };

        Object[][] data = {
                {
                        1,
                        "Your Name",
                        "Tiếng Việt, Văn Học, Tiểu Thuyết",
                        "Shinkai Makoto",
                        "Nhà Xuất Bản Văn Học",
                        "2017/05",
                        75000
                },
                {
                        2,
                        "Những Anh Hùng Của Lịch Sử",
                        "Tiếng Việt, Chính Trị - Pháp Lý, Lý Luận Chính Trị",
                        "Will Durant",
                        "Nhà Xuất Bản Thế Giới",
                        "2020/08",
                        265000
                },
                {
                        3,
                        "Việt Nam Sử Lược - Trần Trọng Kim (Bìa Cứng)",
                        "Tiếng Việt, Lịch Sử, Lịch Sử Việt Nam",
                        "Trần Trọng Kim",
                        "Nhà Xuất Bản Văn Học",
                        "2020/01",
                        390000
                },
                {
                        4,
                        "Cuộc đời kỳ lạ của Nikola Tesla (tái bản 2018)",
                        "Tiếng Việt, Kinh Tế, Doanh Nhân",
                        "",
                        "Nhà Xuất Bản Kinh Tế TPHCM",
                        "2018/06",
                        75000
                },
                {
                        5,
                        "Lâu Đài Bay Của Pháp Sư Howl (Tái Bản 2020)",
                        "Tiếng Việt, Văn Học, Giả Tưởng - Huyền Bí - Phiêu Lưu",
                        "Diana Wynne Jones",
                        "Nhà Xuất Bản Hội Nhà Văn",
                        "2020/03",
                        106000
                }
        };

        Utils.BindDataToTable(columnNames, data, _data.get_dataTable());
    }
}
