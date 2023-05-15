package com.bookdepository.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Genre {
    FANTASY("Фантастика"),
    ACTION("Боевик"),
    ROMANCE("Романтика"),
    WESTERN("Вестерн"),
    DRAMA("Драма"),
    ;
    private final String name;
}

