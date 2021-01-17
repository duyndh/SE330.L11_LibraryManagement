package com.java.scenes;

import com.java.panels.Content;
import com.java.panels.Data;
import com.java.panels.Header;
import com.java.project.Utils;
import jdk.jshell.execution.Util;

public class DashboardScene {

    private Content _content = new Content();

    public Content get_content() { return _content; }

    public DashboardScene() {
        Utils.Log("DashboardScene()");
        //
    }
}
