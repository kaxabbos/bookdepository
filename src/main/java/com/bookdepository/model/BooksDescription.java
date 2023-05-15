package com.bookdepository.model;

import com.bookdepository.model.enums.Category;
import com.bookdepository.model.enums.Genre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BooksDescription {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private int quantity;
    private String description;
    private String author;

    public BooksDescription(Category category, Genre genre, int quantity, String description, String author) {
        this.category = category;
        this.genre = genre;
        this.quantity = quantity;
        this.description = description;
        this.author = author;
    }
}
