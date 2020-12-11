package Springapi.springapi.controller;


import Springapi.springapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Springapi.springapi.entity.BookModel;

import java.util.List;

@Controller
public class BookControllerTemplate {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/books")
    public String getAllBooks(Model model)
    {
        List<BookModel> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "books";
    }
}

