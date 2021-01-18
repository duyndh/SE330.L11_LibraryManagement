package com.java.view_models;

import java.util.Date;

public class BookViewModel {
    public int id;
    public String title;
    public String categories;
    public String authors;
    public String publisher;
    public String publishedDate;
    public int price;

    public BookViewModel(int id_, String title_, String categories_, String authors_, String publisher_, String publishedDate_, int price_) {
        id = id_;
        title = title_;
        categories = categories_;
        authors = authors_;
        publisher = publisher_;
        publishedDate = publishedDate_;
        price = price_;
    }
}
