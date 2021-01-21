package UIComponents.TextFieldWithTableSearch;


import UI.Models.DomainModels.BaseModel;
import UI.Models.TableViewItemModel.AuthorRowItem;
import UIComponents.TableView.TableView;
import UIComponents.TableView.TableViewRowItem;
import data.Repositories.BaseRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TextFieldWithTableSearch<T extends TableViewRowItem> extends JPanel {

    private JTextField textField = new JTextField();
    private TableSearchDialog tableSearch;
    private final BaseRepository repo;
    private Class<? extends TableViewRowItem> itemCls;

    public JTextField getBackedTextField() { return textField; }

    public TextFieldWithTableSearch(BaseRepository repo,
                                    Class<? extends TableViewRowItem> itemCls) {

        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        this.repo = repo;
        setLayout(new GridLayout());

        textField.setEnabled(false);
        add(textField);

        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                tableSearch = new TableSearchDialog<T>(repo, itemCls);
                tableSearch.pack();
                var rs = tableSearch.showDialog();
                textField.setText(String.valueOf(rs));
            }
        });
    }

}
