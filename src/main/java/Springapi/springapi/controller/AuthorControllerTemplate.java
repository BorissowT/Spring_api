package Springapi.springapi.controller;


import Springapi.springapi.entity.AuthorModel;
import Springapi.springapi.repository.AuthorRepository;
import Springapi.springapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import Springapi.springapi.entity.BookModel;

import java.util.List;

@Controller
public class AuthorControllerTemplate {

    @Autowired
    private AuthorRepository authorRepository;

    @RequestMapping(value = "/authors")
    public String getAllBooks(Model model)
    {
        List<AuthorModel> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        return "authors";
    }
}

