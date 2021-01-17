package com.java.scenes;

import com.java.panels.Content;
import com.java.panels.Data;
import com.java.panels.Header;
import com.java.project.Utils;

public class ActivitiesScene {

    private Content _content = new Content();

    private Header _header = new Header("ACTIVITIES");
    private Data _data = new Data();

    public Content get_content() { return _content; }

    public ActivitiesScene() {
        Utils.Log("ActivitiesScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());
    }
}
