package UIComponents;

import UI.Models.TableViewItemModel.BookItemRowItem;
import UI.Models.TableViewItemModel.BookRowItem;
import UIComponents.TextFieldWithTableSearch.TextFieldWithTableSearch;
import data.Repositories.BookRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestMainUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                var mainFrame = new JFrame();
                mainFrame.setSize(new Dimension(800, 600));

                var button = new JButton();
                button.setSize(80, 30);
                mainFrame.add(button);

                mainFrame.pack();
                mainFrame.setVisible(true);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        var p = new TextFieldWithTableSearch<BookRowItem>(new BookRepository(), BookRowItem.class);
                        JOptionPane.showConfirmDialog(null, p, "Test", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    }
                });
            }
        } );
    }

}
