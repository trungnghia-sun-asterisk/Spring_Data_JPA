package com.example.bookstore.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name="authors", uniqueConstraints=@UniqueConstraint(name="uk_author_email", columnNames="email"))
public class Author extends AuditableEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=150) private String name;
    @Column(nullable=false, unique=true, length=255) private String email;
    @Column(columnDefinition="TEXT") private String biography;
    @OneToMany(mappedBy="author", fetch=FetchType.LAZY) private List<Book> books = new ArrayList<>();
    protected Author() {}
    public Author(String name, String email, String biography) { this.name=name; this.email=email; this.biography=biography; }
    public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public String getBiography(){return biography;}
    public void update(String name,String email,String biography){this.name=name;this.email=email;this.biography=biography;}
}
