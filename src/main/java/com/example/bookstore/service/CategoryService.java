package com.example.bookstore.service;
import com.example.bookstore.dto.*; import com.example.bookstore.entity.Category; import com.example.bookstore.exception.*; import com.example.bookstore.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class CategoryService { private final CategoryRepository repo; private final BookRepository books; public CategoryService(CategoryRepository repo,BookRepository books){this.repo=repo;this.books=books;}
 @Transactional public CategoryResponse create(CategoryRequest r){if(repo.existsByNameIgnoreCase(r.name()))throw new ConflictException("Category with name '"+r.name()+"' already exists");return CategoryResponse.from(repo.save(new Category(r.name(),r.description())));}
 @Transactional(readOnly=true) public List<CategoryResponse> findAll(){return repo.findAll().stream().map(CategoryResponse::from).toList();}
 @Transactional(readOnly=true) public Category get(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Category with id "+id+" not found"));}
 @Transactional public CategoryResponse update(Long id,CategoryRequest r){Category c=get(id);if(repo.existsByNameIgnoreCaseAndIdNot(r.name(),id))throw new ConflictException("Category with name '"+r.name()+"' already exists");c.update(r.name(),r.description());return CategoryResponse.from(c);}
 @Transactional public void delete(Long id){get(id);if(books.existsByCategoryId(id))throw new ConflictException("Cannot delete category because books are still associated with this category.");repo.deleteById(id);}
}
