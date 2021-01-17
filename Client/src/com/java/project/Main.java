package com.java.project;

import com.java.panels.Home;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        var home = new Home();
        var frame = new JFrame("Library Management");
        frame.setContentPane(home.get_homePanel());
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1366, 768);
        //frame.setSize(600, 400);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
