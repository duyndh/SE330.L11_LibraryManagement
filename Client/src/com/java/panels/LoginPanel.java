package com.java.panels;

import javax.swing.*;
import java.awt.*;

public class LoginPanel {

    private JPanel _panel;

    public LoginPanel() {

        _panel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 10;
        constraints.weighty = 10;
        constraints.insets = new Insets( 2, 10, 2, 10 );
        constraints.fill = GridBagConstraints.HORIZONTAL;

        var usernameLabel = new JLabel("Username");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;
        _panel.add(usernameLabel, constraints);

        var usernameTextField = new JTextField();
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 0;
        _panel.add(usernameTextField, constraints);

        var passwordLabel = new JLabel("Password");
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        _panel.add(passwordLabel, constraints);

        var passwordTextField = new JTextField();
        constraints.gridwidth = 1;
        constraints.gridx = 1;
        constraints.gridy = 1;
        _panel.add(passwordTextField, constraints);

        var loginButton = new JButton("Login");
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 2;
        _panel.add(loginButton, constraints);
    }

    public JPanel get_panel() {
        return _panel;
    }
}
