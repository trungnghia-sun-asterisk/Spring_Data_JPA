package com.example.bookstore.service;
import com.example.bookstore.dto.*; import com.example.bookstore.entity.Author; import com.example.bookstore.exception.*; import com.example.bookstore.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class AuthorService { private final AuthorRepository repo; private final BookRepository books; public AuthorService(AuthorRepository repo,BookRepository books){this.repo=repo;this.books=books;}
 @Transactional public AuthorResponse create(AuthorRequest r){if(repo.existsByEmailIgnoreCase(r.email()))throw new ConflictException("Author with email '"+r.email()+"' already exists");return AuthorResponse.from(repo.save(new Author(r.name(),r.email(),r.biography())));}
 @Transactional(readOnly=true) public List<AuthorResponse> findAll(){return repo.findAll().stream().map(AuthorResponse::from).toList();}
 @Transactional(readOnly=true) public Author get(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Author with id "+id+" not found"));}
 @Transactional public AuthorResponse update(Long id,AuthorRequest r){Author a=get(id);if(repo.existsByEmailIgnoreCaseAndIdNot(r.email(),id))throw new ConflictException("Author with email '"+r.email()+"' already exists");a.update(r.name(),r.email(),r.biography());return AuthorResponse.from(a);}
 @Transactional public void delete(Long id){get(id);if(books.existsByAuthorId(id))throw new ConflictException("Cannot delete author because books are still associated with this author.");repo.deleteById(id);}
}
