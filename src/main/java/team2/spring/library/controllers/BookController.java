package team2.spring.library.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Copy;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/availableBookForm")
    public String availableBookForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "availableBookForm";
    }

    @PostMapping("/availableBook")
    public String availableBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, Model model) {

        model.addAttribute("bookDto", bookDto);
        return "availableBook";
    }

}
