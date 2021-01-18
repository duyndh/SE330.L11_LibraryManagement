package com.java.scenes;

import com.java.panels.Data;
import com.java.panels.Header;
import com.java.panels.Home;
import com.java.project.Utils;

public class ActivitiesScene extends AbstractScene{

    private String _name = "ACTIVITIES";

    private Header _header = new Header(_name, Home.SceneEnum.ACTIVITIES);
    private Data _data = new Data();

    public ActivitiesScene() {
        Utils.log("ActivitiesScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());
    }

    @Override
    public void loadData() {
        //
    }

    @Override
    public void displayCreationPopup() {
        //
    }

    @Override
    public void displayUpdationPopup(int id) {

    }
}
