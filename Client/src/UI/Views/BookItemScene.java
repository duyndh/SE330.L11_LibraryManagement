package UI.Views;


import com.java.project.Utils;
import com.java.view_models.BookViewModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookItemScene extends BaseScene {

    public JButton getBorrowBtn() {
        return borrowBtn;
    }

    private JButton borrowBtn;

    public BookItemScene() {
        super();
        this.getTitleLb().setText("Book Item Management");

        this.borrowBtn = new JButton();
        borrowBtn.setPreferredSize(new Dimension(10, 10));
        borrowBtn.setLabel("Borrow");
        getMoreButtonPanel().add(borrowBtn);
    }
}