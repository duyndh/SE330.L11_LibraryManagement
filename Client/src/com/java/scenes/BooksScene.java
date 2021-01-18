package com.java.scenes;

import com.java.panels.Data;
import com.java.panels.Header;
import com.java.panels.Home;
import com.java.project.Utils;
import com.java.view_models.BookViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BooksScene extends AbstractScene {

    private String _name = "BOOKS";

    private Header _header = new Header(_name, Home.SceneEnum.BOOKS);
    private Data _data = new Data();

    private final String[] COLUMN_NAMES = {
            "ID",
            "Title",
            "Categories",
            "Authors",
            "Publisher",
            "Published Date",
            "Price"
    };
    private List<Object[]> _tableData = new ArrayList<Object[]>();

    public BooksScene() {
        Utils.log("BooksScene()");

        Utils.setChildPanel(_content.get_headerPanel(), _header.get_headerPanel());
        Utils.setChildPanel(_content.get_dataPanel(), _data.get_dataPanel());

        fillTestData();
    }

    public void fillTestData() {

        create(new BookViewModel(
                1,
                "Your Name",
                "Tiếng Việt, Văn Học, Tiểu Thuyết",
                "Shinkai Makoto",
                "Nhà Xuất Bản Văn Học",
                "2017/05",
                75000
        ));
        create(new BookViewModel(
                2,
                "Những Anh Hùng Của Lịch Sử",
                "Tiếng Việt, Chính Trị - Pháp Lý, Lý Luận Chính Trị",
                "Will Durant",
                "Nhà Xuất Bản Thế Giới",
                "2020/08",
                265000
        ));
        create(new BookViewModel(
                3,
                "Việt Nam Sử Lược - Trần Trọng Kim (Bìa Cứng)",
                "Tiếng Việt, Lịch Sử, Lịch Sử Việt Nam",
                "Trần Trọng Kim",
                "Nhà Xuất Bản Văn Học",
                "2020/01",
                390000
        ));
        create(new BookViewModel(
                4,
                "Cuộc đời kỳ lạ của Nikola Tesla (tái bản 2018)",
                "Tiếng Việt, Kinh Tế, Doanh Nhân",
                "",
                "Nhà Xuất Bản Kinh Tế TPHCM",
                "2018/06",
                75000
        ));
        create(new BookViewModel(
                5,
                "Lâu Đài Bay Của Pháp Sư Howl (Tái Bản 2020)",
                "Tiếng Việt, Văn Học, Giả Tưởng - Huyền Bí - Phiêu Lưu",
                "Diana Wynne Jones",
                "Nhà Xuất Bản Hội Nhà Văn",
                "2020/03",
                106000
        ));
    }

    @Override
    public void loadData() {
        var objects = new Object[_tableData.size()][];
        for (var iObj = 0; iObj < objects.length; iObj++) {
            objects[iObj] = _tableData.get(iObj);
        }

        Utils.bindDataToTable(COLUMN_NAMES, objects, _data.get_dataTable());
    }

    public void create(BookViewModel model) {
        _tableData.add(new Object[] {
                model.id,
                model.title,
                model.categories,
                model.authors,
                model.publisher,
                model.publishedDate,
                model.price
        });
    }

    public void update(BookViewModel model) {
        //_tableData
    }

    @Override
    public void displayCreationPopup() {
//        String[] items = {"One", "Two", "Three", "Four", "Five"};
//        JComboBox<String> combo = new JComboBox<>(items);

        var panel = new JPanel(new GridLayout(0, 1));

        var titleTextField = Utils.addLabelAndTextField(panel, "Title", "");
        var categoriesTextField = Utils.addLabelAndTextField(panel, "Categories", "");
        var authorsTextField = Utils.addLabelAndTextField(panel, "Authors", "");
        var publisherTextField = Utils.addLabelAndTextField(panel, "Publisher", "");
        var publishedDateTextField = Utils.addLabelAndTextField(panel, "PublishedDate", "");

        // Price
        panel.add(new JLabel("Price"));
        var priceSpinner = new JSpinner();
        panel.add(priceSpinner);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Add book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            create(new BookViewModel(
                    0,
                    titleTextField.getText(),
                    categoriesTextField.getText(),
                    authorsTextField.getText(),
                    publisherTextField.getText(),
                    publishedDateTextField.getText(),
                    (int)priceSpinner.getValue()
            ));
        }

        // Reload data table
        loadData();
    }

    @Override
    public void displayUpdationPopup(int id) {

        var panel = new JPanel(new GridLayout(0, 1));

        var obj = _tableData.get(id);
        var model = new BookViewModel((int)obj[0], (String)obj[1], (String)obj[2], (String)obj[3], (String)obj[4], (String)obj[5], (int)obj[6]);

        var titleTextField = Utils.addLabelAndTextField(panel, "Title", model.title);
        var categoriesTextField = Utils.addLabelAndTextField(panel, "Categories", model.categories);
        var authorsTextField = Utils.addLabelAndTextField(panel, "Authors", model.authors);
        var publisherTextField = Utils.addLabelAndTextField(panel, "Publisher", model.publisher);
        var publishedDateTextField = Utils.addLabelAndTextField(panel, "PublishedDate", model.publishedDate);

        // Price
        panel.add(new JLabel("Price"));
        var priceSpinner = new JSpinner();
        priceSpinner.setValue(model.price);
        panel.add(priceSpinner);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Update book",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

//            create(new BookViewModel(
//                    0,
//                    titleTextField.getText(),
//                    categoriesTextField.getText(),
//                    authorsTextField.getText(),
//                    publisherTextField.getText(),
//                    publishedDateTextField.getText(),
//                    (int)priceSpinner.getValue()
//            ));
        }

        // Reload data table
        loadData();
    }
}
