package com.bookdepository.repo;

import com.bookdepository.model.BooksDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksDescriptionRepo extends JpaRepository<BooksDescription, Long> {
}
