package com.java.scenes;

import com.java.panels.Content;
import com.java.panels.Data;
import com.java.panels.Header;
import com.java.project.Utils;

public class ReadersScene {

    private Content _content = new Content();

    private Header _header = new Header("READERS");
    private Data _data = new Data();

    public Content get_content() { return _content; }

    public ReadersScene() {
        Utils.Log("ReadersScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());
    }

    public void loadData() {

        String[] columnNames = {
                "ID",
                "Full Name",
                "Gender",
                "Birthday",
                "Email",
                "Phone",
                "JoinedDate"
        };

        Object[][] data = {
                {
                        1,
                        "Đoàn Nhật Tiến",
                        "Male",
                        "1995/08/25",
                        "herschel.fisher@fox.com",
                        "0355564800",
                        "2020/03/31"
                },
                {
                        2,
                        "Huỳnh Mai Chi",
                        "Female",
                        "1997/06/18",
                        "lula.kramer@bee.com",
                        "0355535009",
                        "2018/11/09"
                },
                {
                        3,
                        "Hoàng Việt Chính",
                        "Tiếng Việt, Lịch Sử, Lịch Sử Việt Nam",
                        "2001/05/08",
                        "loren.macias@horse.com",
                        "0955579201",
                        "2019/08/21"
                }
        };

        Utils.BindDataToTable(columnNames, data, _data.get_dataTable());

    }
}
