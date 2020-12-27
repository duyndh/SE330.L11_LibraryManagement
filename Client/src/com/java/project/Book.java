package com.java.project;

import java.util.Date;

public class Book {
    public int m_id;
    public String m_title;
    public String m_categories;
    public String m_authors;
    public String m_publisher;
    public Date m_publicationDate;
    public String m_description;
    public int m_price;

    public Book(int id, String title, String categories, String authors, String publisher, Date publicationDate, String description, int price) {
        m_id = id;
        m_title = title;
        m_categories = categories;
        m_authors = authors;
        m_publisher = publisher;
        m_publicationDate = publicationDate;
        m_description = description;
        m_price = price;
    }
}
