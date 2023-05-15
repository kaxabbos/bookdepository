package com.bookdepository.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Books {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String date;
    private String[] photos;
    private boolean free;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BooksDescription description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Statistics statistics;
    @ManyToOne(fetch = FetchType.LAZY)
    private Addresses address;

    public Books(String name, String date, boolean free, String[] photos, Addresses address) {
        this.name = name;
        this.date = date;
        this.free = free;
        this.photos = photos;
        this.address = address;
    }
}
