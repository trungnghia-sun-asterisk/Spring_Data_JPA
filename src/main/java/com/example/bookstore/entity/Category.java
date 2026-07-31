package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="categories", uniqueConstraints=@UniqueConstraint(name="uk_category_name", columnNames="name"))
public class Category extends AuditableEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=100) private String name;
    @Column(length=500) private String description;
    @OneToMany(mappedBy="category", fetch=FetchType.LAZY) private List<Book> books = new ArrayList<>();
    protected Category() {}
    public Category(String name,String description){this.name=name;this.description=description;}
    public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;}
    public void update(String name,String description){this.name=name;this.description=description;}
}
