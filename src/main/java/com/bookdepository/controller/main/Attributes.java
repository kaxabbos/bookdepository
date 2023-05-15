package com.bookdepository.controller.main;

import com.bookdepository.model.Statistics;
import com.bookdepository.model.enums.Category;
import com.bookdepository.model.enums.Role;
import com.bookdepository.model.enums.Genre;
import org.springframework.ui.Model;

import java.util.List;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("categorys", Category.values());
        model.addAttribute("genres", Genre.values());
        model.addAttribute("addresses", addressesRepo.findAll());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesBook(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("book", booksRepo.getReferenceById(id));
    }

    protected void AddAttributesBooksMy(Model model) {
        AddAttributes(model);
        model.addAttribute("books", getUser().getBooks());
    }

    protected void AddAttributesAddresses(Model model) {
        AddAttributes(model);
        model.addAttribute("addresses", addressesRepo.findAll());
    }

    protected void AddAttributesBookAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesBookEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("book", booksRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("books", booksRepo.findAllByOrderByFreeDesc());
    }

    protected void AddAttributesCatalogSearch(Model model, Category category, Genre genre) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("fSelect", category);
        model.addAttribute("tSelect", genre);
        model.addAttribute("books", booksRepo.findAllByDescription_CategoryAndDescription_GenreOrderByFreeDesc( category, genre));
    }

    protected void AddAttributesStatistics(Model model) {
        AddAttributes(model);
        List<Statistics> statistics = statisticsRepo.findAll();
        int income = statistics.stream().reduce(0, (i, s) -> i + (s.getDays()), Integer::sum);
        model.addAttribute("statistics", statistics);
        model.addAttribute("income", income);
    }
}
