package UI.Controllers;

import UI.Views.BaseScene;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BaseController {

    public BaseScene scene = null;

    public abstract void onAppear();

    public abstract void onDisappear();

    public abstract void reloadData();

    public BaseController(BaseScene scene) {
        this.scene = scene;
        scene.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCreateTapped();
            }
        });

        scene.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onUpdatedTapped();
            }
        });

        scene.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDeleteTapped();
            }
        });

        scene.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onSearchButtonTapped(scene.searchTextField.getText());
            }
        });

        scene.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onClearButtonTapped();
            }
        });
    }

    abstract void onCreateTapped();

    abstract void onUpdatedTapped();

    abstract void onDeleteTapped();

    abstract void onSearchButtonTapped(String searchText);

    void onClearButtonTapped() {
        this.scene.searchTextField.setText("");
    };
}
