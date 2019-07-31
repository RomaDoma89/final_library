package team2.spring.library.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import team2.spring.library.dto.BookDto;
import team2.spring.library.entities.Author;
import team2.spring.library.services.BookService;

import javax.validation.Valid;

/**
 * Controller witch operate all request of book */

@Controller
@AllArgsConstructor
public class BookController {

 private BookService bookService;

  /**
   * @param model set list of book  in  jsp
   * @return
   */
  @GetMapping("/allBooks")
  public String findAllBook(Model model) {
     model.addAttribute("listBook",bookService.findAll());
     return "allBooks";
 }
 
 /**
   * @param model prepare dto for Jsp
   * @return page with form of book title
   * */
  @GetMapping("/availableBookForm")
  public String availableBookForm(Model model) {
    model.addAttribute("bookDto", new BookDto());
    return "booksJsp/availableBookForm";
  }

  /**
   * @param bookDto from page with input title form
   * @param model set dto in jsp
   * @return  page with available book
   */
  @PostMapping("/availableBook")
  public String availableBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, Model model) {
    model.addAttribute("bookDto", bookService.isBookAvailable(bookDto));
    return "booksJsp/availableBook";
  }

  /**
   * @param model prepare author for jsp
   * @return page with form for author name
   */
  @GetMapping("/booksByAuthorForm")
  public String findBooksByAuthorForm(Model model) {
      model.addAttribute("author", new Author());
      return "booksByAuthorForm";
  }
  /** 
   * @param author from page with input name form
   * @param model set list of book in jsp 
   * @return page with list of books
   */
  @PostMapping("/booksByAuthor")
  public String booksByAuthor(@ModelAttribute("author") Author author, Model model) {
      model.addAttribute("author",author);
      model.addAttribute("listBooks",bookService.findBooksByAuthor(author));
       return "booksByAuthor";
   }

  
}
