package com.bookdepository.repo;

import com.bookdepository.model.Books;
import com.bookdepository.model.enums.Category;
import com.bookdepository.model.enums.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepo extends JpaRepository<Books, Long> {
    List<Books> findAllByDescription_CategoryAndDescription_GenreOrderByFreeDesc( Category category, Genre genre);
    List<Books> findAllByOrderByFreeDesc();
}
