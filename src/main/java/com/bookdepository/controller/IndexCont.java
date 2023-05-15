package com.bookdepository.controller;

import com.bookdepository.controller.main.Attributes;
import com.bookdepository.model.enums.Category;
import com.bookdepository.model.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesCatalog(model);
        return "catalog";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam Category category, @RequestParam Genre genre) {
        AddAttributesCatalogSearch(model, category, genre);
        return "catalog";
    }
}
