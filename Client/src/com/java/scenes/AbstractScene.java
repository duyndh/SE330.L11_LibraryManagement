package com.java.scenes;

import com.java.panels.Content;

public abstract class AbstractScene {
    protected Content _content = new Content();
    public Content get_content() { return _content; }

    protected String _name;
    public String get_name() { return _name; }

    public abstract void loadData();

    public abstract void displayCreationPopup();
    public abstract void displayUpdationPopup(int id);
}
