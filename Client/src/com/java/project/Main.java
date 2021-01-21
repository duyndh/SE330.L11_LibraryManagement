package com.java.project;

import com.java.panels.Home;
import com.java.panels.LoginPanel;
import com.java.utils.AppUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main {

    private static Home _home = new Home();
    public static Home get_home() { return _home; }

    public static void main(String[] args) {

        boolean isEmail = AppUtils.validateEmail("aaaaa@gmail.com");
        boolean isPhone = AppUtils.validatePhoneNumber("0966534123");
        return;

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
