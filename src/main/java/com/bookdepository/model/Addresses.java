package com.bookdepository.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Addresses {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String address;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private List<Books> books;


    public Addresses(String address) {
        this.address = address;
        books = new ArrayList<>();
    }
}
