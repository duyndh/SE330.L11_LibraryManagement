package com.java.scenes;

import com.java.panels.Data;
import com.java.panels.Header;
import com.java.panels.Home;
import com.java.project.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReadersScene extends AbstractScene{

    private String _name = "READERS";

    private Header _header = new Header(_name, Home.SceneEnum.READERS);
    private Data _data = new Data();

    private final String[] COLUMN_NAMES = {
            "ID",
            "Full Name",
            "Gender",
            "Birthday",
            "Email",
            "Phone",
            "JoinedDate"
    };
    private List<Object[]> _tableData = new ArrayList<Object[]>();

    public ReadersScene() {
        Utils.log("ReadersScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());

        fillTestData();
    }

    public void fillTestData() {

        _tableData.add(new Object[]{
                1,
                "Đoàn Nhật Tiến",
                "Male",
                "1995/08/25",
                "herschel.fisher@fox.com",
                "0355564800",
                "2020/03/31"
        });
        _tableData.add(new Object[]{
                2,
                "Huỳnh Mai Chi",
                "Female",
                "1997/06/18",
                "lula.kramer@bee.com",
                "0355535009",
                "2018/11/09"
        });
        _tableData.add(new Object[]{
                3,
                "Hoàng Việt Chính",
                "Tiếng Việt, Lịch Sử, Lịch Sử Việt Nam",
                "2001/05/08",
                "loren.macias@horse.com",
                "0955579201",
                "2019/08/21"
        });
    }

    @Override
    public void loadData() {
        var objects = new Object[_tableData.size()][];
        for (var iObj = 0; iObj < objects.length; iObj++) {
            objects[iObj] = _tableData.get(iObj);
        }

        Utils.bindDataToTable(COLUMN_NAMES, objects, _data.get_dataTable());
    }

    @Override
    public void displayCreationPopup() {
        //
    }

    @Override
    public void displayUpdationPopup(int id) {

    }
}
