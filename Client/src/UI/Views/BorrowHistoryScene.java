package UI.Views;


import javax.swing.*;
import java.awt.*;

public class BorrowHistoryScene extends BaseScene {

    public JButton getReturnBtn() {
        return returnBtn;
    }

    private JButton returnBtn;

    public JRadioButton getBorrowRadioBtn() {
        return borrowRadioBtn;
    }

    public JRadioButton getMemberRadioBtn() {
        return memberRadioBtn;
    }

    public JRadioButton getBookNameRadioBtn() {
        return bookNameRadioBtn;
    }

    private JRadioButton borrowRadioBtn;
    private JRadioButton memberRadioBtn;
    private JRadioButton bookNameRadioBtn;
    private ButtonGroup  btnGroup;

    public BorrowHistoryScene() {
        super();
        this.getTitleLb().setText("Borrow History Management");
        setLayout(new GridLayout(0, 1));

        btnGroup = new ButtonGroup();

        // Set up radio button
        borrowRadioBtn = new JRadioButton("Borrow ID", true);
        memberRadioBtn = new JRadioButton("Member ID");
        bookNameRadioBtn = new JRadioButton("Book Name");

        getBelowSearchPanel().add(borrowRadioBtn);
        getBelowSearchPanel().add(memberRadioBtn);
        getBelowSearchPanel().add(bookNameRadioBtn);

        btnGroup.add(borrowRadioBtn);
        btnGroup.add(memberRadioBtn);
        btnGroup.add(bookNameRadioBtn);

        this.returnBtn = new JButton();
        returnBtn.setPreferredSize(new Dimension(10, 10));
        returnBtn.setLabel("Return");
        getMoreButtonPanel().add(returnBtn);
    }
}