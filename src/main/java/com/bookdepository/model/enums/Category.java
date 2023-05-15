package com.bookdepository.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {
    CHILDREN("0-14"),
    TEENAGER("15-19"),
    ADULT("20-30"),
    ADULT3("31-40"),
    ADULT4("40+"),
    ;
    private final String name;
}

