package com.java.project;

import com.java.panels.Home;
import com.java.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    private static Home _home = new Home();
    public static Home get_home() { return _home; }

    public static void main(String[] args) {

        //var loginPanel = new Login();
        //loginPanel

        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginPanel().get_panel());
        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return;

//        var p = new GridBagLayout();
//
//        var frame = new JFrame("Library Management");
//        frame.setContentPane(_home.get_homePanel());
//        //frame.setContentPane(loginPanel.get_loginPanel());
//        frame.pack();
//        frame.setVisible(true);
//        //frame.setSize(1366, 768);
//        frame.setSize(600, 400);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
