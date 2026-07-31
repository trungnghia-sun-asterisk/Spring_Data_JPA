package com.example.bookstore.dto;
import com.example.bookstore.entity.Author;
public record AuthorResponse(Long id,String name,String email,String biography) { public static AuthorResponse from(Author a){return new AuthorResponse(a.getId(),a.getName(),a.getEmail(),a.getBiography());} }
