package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity @Table(name="books", uniqueConstraints=@UniqueConstraint(name="uk_book_isbn", columnNames="isbn"))
public class Book extends AuditableEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=255) private String title;
    @Column(nullable=false,unique=true,length=20) private String isbn;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal price;
    @Column(nullable=false) private Integer stock;
    private LocalDate publishedDate;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="author_id",nullable=false) private Author author;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="category_id",nullable=false) private Category category;
    protected Book() {}
    public Book(String title,String isbn,BigDecimal price,Integer stock,LocalDate publishedDate,Author author,Category category){this.title=title;this.isbn=isbn;this.price=price;this.stock=stock;this.publishedDate=publishedDate;this.author=author;this.category=category;}
    public Long getId(){return id;} public String getTitle(){return title;} public String getIsbn(){return isbn;} public BigDecimal getPrice(){return price;} public Integer getStock(){return stock;} public LocalDate getPublishedDate(){return publishedDate;} public Author getAuthor(){return author;} public Category getCategory(){return category;}
    public void update(String title,String isbn,BigDecimal price,Integer stock,LocalDate publishedDate,Author author,Category category){this.title=title;this.isbn=isbn;this.price=price;this.stock=stock;this.publishedDate=publishedDate;this.author=author;this.category=category;}
}
