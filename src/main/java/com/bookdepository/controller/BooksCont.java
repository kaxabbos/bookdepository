package com.bookdepository.controller;

import com.bookdepository.controller.main.Attributes;
import com.bookdepository.model.Books;
import com.bookdepository.model.BooksDescription;
import com.bookdepository.model.Statistics;
import com.bookdepository.model.Users;
import com.bookdepository.model.enums.Category;
import com.bookdepository.model.enums.Genre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/books")
public class BooksCont extends Attributes {

    @GetMapping("/{id}")
    public String Book(Model model, @PathVariable Long id) {
        AddAttributesBook(model, id);
        return "book";
    }

    @GetMapping("/add")
    public String BookAdd(Model model) {
        AddAttributesBookAdd(model);
        return "book_add";
    }

    @GetMapping("/edit/{id}")
    public String BookEdit(Model model, @PathVariable Long id) {
        AddAttributesBookEdit(model, id);
        return "book_edit";
    }

    @GetMapping("/delete/{id}")
    public String BookDelete(@PathVariable Long id) {
        booksRepo.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/my")
    public String BookMy(Model model) {
        AddAttributesBooksMy(model);
        return "my_books";
    }

    @GetMapping("/return/{id}")
    public String BookReturn(@PathVariable Long id) {
        Users user = getUser();
        Books book = booksRepo.getReferenceById(id);
        book.setFree(true);
        user.removeBook(book);
        usersRepo.save(user);
        return "redirect:/books/my";
    }

    @PostMapping("/rent/{id}")
    public String BookRent(@PathVariable Long id, @RequestParam int days) {
        Books book = booksRepo.getReferenceById(id);

        Users user = getUser();
        user.addBook(book);
        usersRepo.save(user);

        book.setFree(false);
        book.getStatistics().setDays(book.getStatistics().getDays() + days);

        booksRepo.save(book);

        return "redirect:/books/{id}";
    }

    @PostMapping("/add")
    public String BookAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam String date, @RequestParam int quantity, @RequestParam Long idAddress, @RequestParam String description, @RequestParam Category category, @RequestParam Genre genre, @RequestParam String author) {
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String[] result_photos;
                String result_screenshot;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_screenshot = "books/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
                Books book = new Books(name, date, true, result_photos, addressesRepo.getReferenceById(idAddress));
                book.setStatistics(new Statistics(book));
                book.setDescription(new BooksDescription(category, genre, quantity, description, author));
                booksRepo.save(book);
            } catch (Exception e) {
                AddAttributesBookAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "book_add";
            }
        } else {
            AddAttributesBookAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "book_add";
        }
        return "redirect:/books/add";
    }

    @PostMapping("/edit/{id}")
    public String BookEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam String date, @RequestParam int quantity, @RequestParam Long idAddress, @RequestParam String description, @RequestParam Category category, @RequestParam Genre genre, @RequestParam String author, @PathVariable Long id) {
        Books book = booksRepo.getReferenceById(id);
        String[] result_photos;
        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String result_photo;
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    result_photo = "books/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                book.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesBookAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "book_edit";
            }
        }

        book.setName(name);
        book.setDate(date);
        book.setAddress(addressesRepo.getReferenceById(idAddress));
        booksRepo.save(book);

        BooksDescription booksDescription = book.getDescription();
        booksDescription.setDescription(description);
        booksDescription.setCategory(category);
        booksDescription.setGenre(genre);
        booksDescription.setQuantity(quantity);
        booksDescription.setAuthor(author);
        booksDescriptionRepo.save(booksDescription);

        return "redirect:/";
    }
}
