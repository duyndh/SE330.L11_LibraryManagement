package com.java.project;

import com.java.panels.Home;

import javax.swing.*;

public class Main {

    private static Home _home = new Home();
    public static Home get_home() { return _home; }

    public static void main(String[] args) {

        var frame = new JFrame("Library Management");
        frame.setContentPane(_home.get_homePanel());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1366, 768);
        //frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
